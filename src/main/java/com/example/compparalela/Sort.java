package com.example.compparalela;

import java.util.Arrays;

public class Sort {

    private int threadCount;

    public Sort(int threadCount) {
        this.threadCount = threadCount;
    }

    
    public void bubbleSort(int[] array, boolean parallel) {
        if (parallel) {
            int segmentSize = (int) Math.ceil((double) array.length / threadCount);
            Thread[] threads = new Thread[threadCount];

            for (int i = 0; i < threadCount; i++) {
                int start = i * segmentSize;
                int end = Math.min(start + segmentSize, array.length); 

                threads[i] = new Thread(() -> bubbleSortSegment(array, start, end));
                threads[i].start();
            }

            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            mergeSortedParts(array, segmentSize);
        } else {
            bubbleSortSegment(array, 0, array.length);
        }
    }

    private void bubbleSortSegment(int[] array, int start, int end) {
        for (int i = start; i < end - 1; i++) {
            for (int j = start; j < end - 1; j++) {
                if (array[j] > array[j + 1]) {
                    
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    
    public void quickSort(int[] array, boolean parallel) {
        if (parallel) {
            int segmentSize = (int) Math.ceil((double) array.length / threadCount);
            Thread[] threads = new Thread[threadCount];

            for (int i = 0; i < threadCount; i++) {
                int start = i * segmentSize;
                int end = Math.min(start + segmentSize - 1, array.length - 1); 

                threads[i] = new Thread(() -> quickSort(array, start, end));
                threads[i].start();
            }

            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            
            mergeSortedParts(array, segmentSize);
        } else {
            quickSort(array, 0, array.length - 1);
        }
    }

    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    
    public void mergeSort(int[] array, boolean parallel) {
        if (parallel) {
            int segmentSize = (int) Math.ceil((double) array.length / threadCount);
            Thread[] threads = new Thread[threadCount];

            for (int i = 0; i < threadCount; i++) {
                int start = i * segmentSize;
                int end = Math.min(start + segmentSize - 1, array.length - 1); 

                threads[i] = new Thread(() -> mergeSort(array, start, end));
                threads[i].start();
            }

            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            
            mergeSortedParts(array, segmentSize);
        } else {
            mergeSort(array, 0, array.length - 1);
        }
    }

    private void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);
            merge(array, left, middle, right);
        }
    }

    private void merge(int[] array, int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = array[left + i];
        for (int j = 0; j < n2; ++j)
            R[j] = array[middle + 1 + j];

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
        }
    }

    
    public void selectionSort(int[] array, boolean parallel) {
        if (parallel) {
            int segmentSize = (int) Math.ceil((double) array.length / threadCount);
            Thread[] threads = new Thread[threadCount];

            for (int i = 0; i < threadCount; i++) {
                int start = i * segmentSize;
                int end = Math.min(start + segmentSize, array.length); 

                threads[i] = new Thread(() -> selectionSortSegment(array, start, end));
                threads[i].start();
            }

            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            
        } else {
            selectionSortSegment(array, 0, array.length);
        }
    }

    private void selectionSortSegment(int[] array, int start, int end) {
        for (int i = start; i < end - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < end; j++) {
                if (array[j] < array[min_idx]) {
                    min_idx = j;
                }
            }
            
            int temp = array[min_idx];
            array[min_idx] = array[i];
            array[i] = temp;
        }
    }

    
    private void mergeSortedParts(int[] array, int segmentSize) {
        int segments = (int) Math.ceil((double) array.length / segmentSize);
        for (int i = 0; i < segments - 1; i++) {
            int left = i * segmentSize;
            int right = Math.min((i + 1) * segmentSize - 1, array.length - 1);
            int nextLeft = Math.min((i + 2) * segmentSize - 1, array.length - 1);
            if (nextLeft < array.length) {
                merge(array, left, right, nextLeft);
            }
        }
    }
}
