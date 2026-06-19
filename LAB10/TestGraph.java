package listlinked; 
 
import graph.GraphLink; 
 
public class TestGraph { 
   public static void main(String[] args) { 
       GraphLink<String> g = new GraphLink<>(); 
 
       // Insertar vértices 
       g.insertVertex("A"); 
       g.insertVertex("B"); 
       g.insertVertex("C"); 
       g.insertVertex("D"); 
 
       // Insertar aristas 
       g.insertEdge("A", "B"); 
       g.insertEdge("A", "C"); 
       g.insertEdge("B", "D"); 
 
       System.out.println(g); 
   } 
} 
