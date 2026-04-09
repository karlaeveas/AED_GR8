public class Auxiliar {
    int prim, ult;

    public Auxiliar(int prim, int ult) {
        this.prim = prim;
        this.ult = ult;
    }

    public int length() {
        return (ult - prim) + 1;
    }
}