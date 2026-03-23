public class Utilidades {

    public static <T> void swap(T[] arreglo, int i, int j) {

        if (arreglo == null) {
            throw new IllegalArgumentException("El arreglo no puede ser null");
        }


        if (i < 0 || j < 0 || i >= arreglo.length || j >= arreglo.length) {
            throw new IndexOutOfBoundsException("Índices fuera de rango");
        }


        T temp = arreglo[i];
        arreglo[i] = arreglo[j];
        arreglo[j] = temp;
    }
}