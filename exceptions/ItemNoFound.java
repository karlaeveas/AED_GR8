package exceptions;

public class ItemNoFound extends Exception {
    public ItemNoFound(String mensaje) {
        super(mensaje);
    }
}