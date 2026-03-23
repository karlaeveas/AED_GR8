public class Main {
    public static void main(String[] args) {
        String[] arr = {"A", "B", "C", "D"};

        Utilidades.swap(arr, 1, 3);

        for (String s : arr) {
            System.out.print(s + " ");
        }
    }
}