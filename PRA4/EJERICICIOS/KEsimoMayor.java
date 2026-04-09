package ejercicios;

import java.util.Scanner;

public class KEsimoMayor {

    public static int quickSelect(int[] arr, int left, int right, int k) {

        if (left == right) return arr[left];

        int pivotIndex = partition(arr, left, right);

        // Cantidad de elementos mayores o iguales al pivote
        int count = pivotIndex - left + 1;

        if (k == count) {
            return arr[pivotIndex];
        } else if (k < count) {
            return quickSelect(arr, left, pivotIndex - 1, k);
        } else {
            return quickSelect(arr, pivotIndex + 1, right, k - count);
        }
    }

    private static int partition(int[] arr, int left, int right) {

        int pivot = arr[right];
        int i = left;

        for (int j = left; j < right; j++) {
            // Orden descendente (mayores primero)
            if (arr[j] > pivot) {
                swap(arr, i, j);
                i++;
            }
        }

        swap(arr, i, right);
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese el arreglo:");
        String input = sc.nextLine();
        String[] datos = input.split(" ");

        int[] arr = new int[datos.length];
        for (int i = 0; i < datos.length; i++) {
            arr[i] = Integer.parseInt(datos[i]);
        }

        System.out.print("Ingrese k: ");
        int k = sc.nextInt();

        int resultado = quickSelect(arr, 0, arr.length - 1, k);

        System.out.println("El " + k + "-ésimo elemento más grande es: " + resultado);

        sc.close();
    }
}