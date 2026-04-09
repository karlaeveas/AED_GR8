public class ProgramacionDinamica {
    static int getValue(int[] values, int rodLength) {
        int[] subSoluciones = new int[rodLength + 1];

        for (int i = 1; i <= rodLength; i++) {
            int tmpMax = -1;
            for (int j = 0; j < i; j++) {
                tmpMax = Math.max(tmpMax, values[j] + subSoluciones[i - j - 1]);
            }
            subSoluciones[i] = tmpMax;
        }

        return subSoluciones[rodLength];
    }

    public static void main(String[] args) {
        int[] values = new int[]{3, 7, 1, 3, 9};
        int rodLength = values.length;

        System.out.println("Solución con Programación Dinámica");
        System.out.println("El valor máximo: " + getValue(values, rodLength));
    }
}