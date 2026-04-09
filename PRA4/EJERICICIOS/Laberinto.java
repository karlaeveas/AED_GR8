package ejercicios;

import java.util.Scanner;

public class Laberinto {

    static int N, M;

    public static boolean resolverLaberinto(int[][] lab, int[][] sol, int x, int y) {

        // Caso base: llegamos al destino
        if (x == N - 1 && y == M - 1 && lab[x][y] == 0) {
            sol[x][y] = 1;
            return true;
        }

        // Verificar si es válido
        if (esValido(lab, sol, x, y)) {

            sol[x][y] = 1; // marcar camino

            // Movimientos: derecha, abajo, izquierda, arriba
            if (resolverLaberinto(lab, sol, x, y + 1)) return true;
            if (resolverLaberinto(lab, sol, x + 1, y)) return true;
            if (resolverLaberinto(lab, sol, x, y - 1)) return true;
            if (resolverLaberinto(lab, sol, x - 1, y)) return true;

            // BACKTRACKING: desmarcar
            sol[x][y] = 0;

            return false;
        }

        return false;
    }

    public static boolean esValido(int[][] lab, int[][] sol, int x, int y) {
        return (x >= 0 && x < N &&
                y >= 0 && y < M &&
                lab[x][y] == 0 &&
                sol[x][y] == 0);
    }

    public static void imprimirSolucion(int[][] sol) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(sol[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Ingrese filas y columnas:");
        N = sc.nextInt();
        M = sc.nextInt();

        int[][] lab = new int[N][M];

        System.out.println("Ingrese el laberinto (0 = camino, 1 = pared):");

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                lab[i][j] = sc.nextInt();
            }
        }

        int[][] sol = new int[N][M];

        if (resolverLaberinto(lab, sol, 0, 0)) {
            System.out.println("true");
            System.out.println("Camino encontrado:");
            imprimirSolucion(sol);
        } else {
            System.out.println("false");
        }

        sc.close();
    }
}