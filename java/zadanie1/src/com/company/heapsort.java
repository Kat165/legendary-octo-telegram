package com.company;

public class heapsort {
    public static void printArray(int[] arr) {
        for (int a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int arr2[] = {3, 2, 1, 4, 8, 10, 1, 0, 45, 12, 4};
        heapsort hs  = new heapsort();
        hs.sort(arr2);
        printArray(arr2);
    }
    public void sort(int [] arr) {
        //TODO: zaimplementuj algorytm sortowania przez kopcowanie
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            kopcowanie(arr, n, i);

        for (int i = n - 1; i > 0; i--) {
            int tymczasowa = arr[0];
            arr[0] = arr[i];
            arr[i] = tymczasowa;

            kopcowanie(arr, i, 0);

        }
    }
    void kopcowanie(int arr[], int n, int i)
    {
        int najwieksza = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;


        if (l < n && arr[l] > arr[najwieksza])
            najwieksza = l;


        if (r < n && arr[r] > arr[najwieksza])
            najwieksza = r;


        if (najwieksza != i) {
            int zamien = arr[i];
            arr[i] = arr[najwieksza];
            arr[najwieksza] = zamien;


            kopcowanie(arr, n, najwieksza);
        }
    }
}
