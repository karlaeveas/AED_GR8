package actividad;

public class Main {
    public static void main(String[] args) {

        GestorDeTareas<Tarea> gestor = new GestorDeTareas<>();

        // 1. Agregar tareas
        Tarea t1 = new Tarea("Diseñar UI", 2, "pendiente");
        Tarea t2 = new Tarea("Corregir bugs", 1, "pendiente");
        Tarea t3 = new Tarea("Documentar código", 3, "completada");
        Tarea t4 = new Tarea("Reunión equipo", 2, "pendiente");
        Tarea t5 = new Tarea("Deploy", 1, "completada");

        gestor.agregarTarea(t1);
        gestor.agregarTarea(t2);
        gestor.agregarTarea(t3);
        gestor.agregarTarea(t4);
        gestor.agregarTarea(t5);

        // 2. Eliminar una tarea
        gestor.eliminarTarea(t3);

        // 3. Imprimir tareas
        System.out.println("Lista de tareas:");
        gestor.imprimirTareas();

        // 4. Buscar tarea
        System.out.println("¿Existe 'Deploy'? " + gestor.contieneTarea(t5));

        // 5. Contar tareas
        System.out.println("Total tareas: " + gestor.contarTareas());

        // 6. Tarea más prioritaria
        System.out.println("Tarea más prioritaria:");
        System.out.println(gestor.obtenerTareaMasPrioritaria());

        // 7. Invertir lista
        gestor.invertirTareas();
        System.out.println("Lista invertida:");
        gestor.imprimirTareas();

        // 8. Lista de completadas
        ListLinked<Tarea> completadas = new ListLinked<>();

        Nodo<Tarea> current = gestor.getHead();

        while (current != null) {
            if (current.dato.getEstado().equals("completada")) {
                completadas.insertLast(current.dato);
            }
            current = current.enlace;
        }

        System.out.println("Tareas completadas:");
        completadas.print();
    }
}