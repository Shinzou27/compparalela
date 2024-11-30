package com.example.compparalela;

import org.jocl.*;
import java.util.List;
import static org.jocl.CL.*;

public class CounterGPU {

    public int contarPalavrasGPU(List<String> palavras, String palavra) {
        String kernelSource = """
    __kernel void contarPalavras(
        __global const char *texto,
        __global const char *palavra,
        __global int *resultado,
        const int tamanhoTexto,
        const int tamanhoPalavra) 
    {
        int gid = get_global_id(0);
        
        // Verificar se 'gid' está dentro dos limites para a comparação
        if (gid + tamanhoPalavra > tamanhoTexto) return;

        // Verificar se a palavra está delimitada por um espaço ou início/fim do texto
        if (gid > 0 && texto[gid - 1] != ' ' && texto[gid - 1] != '\\n') return;
        if (gid + tamanhoPalavra < tamanhoTexto && texto[gid + tamanhoPalavra] != ' ' && texto[gid + tamanhoPalavra] != '\\n') return;

        // Comparar a palavra no texto com a palavra-alvo
        for (int i = 0; i < tamanhoPalavra; i++) {
            if (texto[gid + i] != palavra[i]) {
                return;
            }
        }
        
        // Incrementar o contador se a palavra for encontrada
        atomic_add(resultado, 1);
    }
    """;


        try {
            // Converter a lista de palavras em um único array de bytes
            String texto = String.join(" ", palavras);
            byte[] textoBytes = texto.getBytes();
            byte[] palavraBytes = palavra.getBytes();

            int tamanhoTexto = textoBytes.length;
            int tamanhoPalavra = palavraBytes.length;

            CL.setExceptionsEnabled(true);
            cl_platform_id[] platforms = new cl_platform_id[1];
            clGetPlatformIDs(1, platforms, null);
            cl_device_id[] devices = new cl_device_id[1];
            clGetDeviceIDs(platforms[0], CL.CL_DEVICE_TYPE_GPU, 1, devices, null);
            cl_context context = clCreateContext(null, 1, devices, null, null, null);
            cl_command_queue queue = clCreateCommandQueueWithProperties(context, devices[0], null, null);

            cl_mem textoBuffer = clCreateBuffer(context, CL.CL_MEM_READ_ONLY | CL.CL_MEM_COPY_HOST_PTR,
                    Sizeof.cl_char * tamanhoTexto, Pointer.to(textoBytes), null);
            cl_mem palavraBuffer = clCreateBuffer(context, CL.CL_MEM_READ_ONLY | CL.CL_MEM_COPY_HOST_PTR,
                    Sizeof.cl_char * tamanhoPalavra, Pointer.to(palavraBytes), null);
            cl_mem resultadoBuffer = clCreateBuffer(context, CL.CL_MEM_READ_WRITE | CL.CL_MEM_COPY_HOST_PTR,
                    Sizeof.cl_int, Pointer.to(new int[]{0}), null);

            cl_program program = clCreateProgramWithSource(context, 1, new String[]{kernelSource}, null, null);
            clBuildProgram(program, 0, null, null, null, null);
            cl_kernel kernel = clCreateKernel(program, "contarPalavras", null);

            clSetKernelArg(kernel, 0, Sizeof.cl_mem, Pointer.to(textoBuffer));
            clSetKernelArg(kernel, 1, Sizeof.cl_mem, Pointer.to(palavraBuffer));
            clSetKernelArg(kernel, 2, Sizeof.cl_mem, Pointer.to(resultadoBuffer));
            clSetKernelArg(kernel, 3, Sizeof.cl_int, Pointer.to(new int[]{tamanhoTexto}));
            clSetKernelArg(kernel, 4, Sizeof.cl_int, Pointer.to(new int[]{tamanhoPalavra}));

            long[] globalWorkSize = new long[]{tamanhoTexto};
            clEnqueueNDRangeKernel(queue, kernel, 1, null, globalWorkSize, null, 0, null, null);

            int[] resultado = new int[1];
            clEnqueueReadBuffer(queue, resultadoBuffer, CL.CL_TRUE, 0, Sizeof.cl_int, Pointer.to(resultado), 0, null, null);

            clReleaseKernel(kernel);
            clReleaseProgram(program);
            clReleaseMemObject(textoBuffer);
            clReleaseMemObject(palavraBuffer);
            clReleaseMemObject(resultadoBuffer);
            clReleaseCommandQueue(queue);
            clReleaseContext(context);

            return resultado[0];
        } catch (Exception e) {
            throw new RuntimeException("Erro durante a execução do kernel: " + e.getMessage());
        }
    }
}
