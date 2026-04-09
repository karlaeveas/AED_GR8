import java.util.Arrays;

public class MergeSort {

    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];

        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public static void imprimirArreglo(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {

        int[] arr5 = {8, 3, 5, 2, 1};
        int[] arr8 = {10, 7, 8, 9, 1, 5, 3, 2};
        int[] arr10 = {12, 11, 13, 5, 6, 7, 2, 9, 1, 4};

        System.out.println("Arreglo de 5 elementos:");
        imprimirArreglo(arr5);
        mergeSort(arr5, 0, arr5.length - 1);
        System.out.println("Ordenado:");
        imprimirArreglo(arr5);

        System.out.println("\nArreglo de 8 elementos:");
        imprimirArreglo(arr8);
        mergeSort(arr8, 0, arr8.length - 1);
        System.out.println("Ordenado:");
        imprimirArreglo(arr8);

        System.out.println("\nArreglo de 10 elementos:");
        imprimirArreglo(arr10);
        mergeSort(arr10, 0, arr10.length - 1);
        System.out.println("Ordenado:");
        imprimirArreglo(arr10);
    }
}