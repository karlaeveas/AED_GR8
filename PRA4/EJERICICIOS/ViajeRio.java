package ejercicios;

import java.util.Scanner;

public class ViajeRio {

    // Método que calcula la matriz de costos mínimos
    public static int[][] calcularCostoMinimo(int[][] T, int n) {

        int[][] C = new int[n][n];

        // Inicializar diagonal
        for (int i = 0; i < n; i++) {
            C[i][i] = 0;
        }

        // Programación dinámica
        for (int d = 1; d < n; d++) { // distancia entre i y j
            for (int i = 0; i < n - d; i++) {
                int j = i + d;

                C[i][j] = T[i][j]; // costo directo

                for (int k = i + 1; k < j; k++) {
                    C[i][j] = Math.min(C[i][j], T[i][k] + C[k][j]);
                }
            }
        }

        return C;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese el número de embarcaderos (n):");
        int n = sc.nextInt();

        int[][] T = new int[n][n];

        System.out.println("Ingrese la matriz de tarifas (solo triangular superior):");

        // Leer matriz
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                T[i][j] = sc.nextInt();
            }
        }

        int[][] C = calcularCostoMinimo(T, n);

        // Mostrar matriz de costos mínimos
        System.out.println("\nMatriz de costos mínimos:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(C[i][j] + "\t");
            }
            System.out.println();
        }

        sc.close();
    }
}
