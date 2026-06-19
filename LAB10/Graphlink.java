package graph; 
 
import listlinked.AdjList; 
 
import java.util.LinkedList; 
 
public class GraphLink<E> { 
   private LinkedList<AdjList<E>> graph; 
 
   public GraphLink() { 
       this.graph = new LinkedList<>(); 
   } 
 
   // 6. Método insertar vértice 
   public void insertVertex(E data) { 
       Vertex<E> vertex = new Vertex<>(data); 
       graph.addLast(new AdjList<>(vertex)); 
   } 
 
   // 7. Buscar vértice (método auxiliar privado) 
   private AdjList<E> findVertex(E data) { 
       for (int i = 0; i < graph.size(); i++) { 
           AdjList<E> adj = graph.get(i); 
           if (adj.getVertex().getData().equals(data)) { 
               return adj; 
           } 
       } 
       return null; 
   } 
 
   // 8. Insertar arista (Grafo no dirigido) 
   public void insertEdge(E origin, E destination) { 
       AdjList<E> v1 = findVertex(origin); 
       AdjList<E> v2 = findVertex(destination); 
 
       // Si alguno de los vértices no existe, no se hace la conexión 
       if (v1 == null || v2 == null) { 
           return; 
       } 
 
       // Al ser no dirigido, la conexión se agrega en ambos sentidos 
       v1.getEdges().addLast(new Edge<>(v2.getVertex())); 
       v2.getEdges().addLast(new Edge<>(v1.getVertex())); 
   } 
 
   // 9. Mostrar el grafo 
   @Override 
   public String toString() { 
       StringBuilder sb = new StringBuilder(); 
 
       for (int i = 0; i < graph.size(); i++) { 
           AdjList<E> adj = graph.get(i); 
           sb.append(adj.getVertex()).append(" -> "); 
 
           for (int j = 0; j < adj.getEdges().size(); j++) { 
               sb.append(adj.getEdges().get(j)).append(" "); 
           } 
           sb.append("\n"); 
       } 
 
       return sb.toString(); 
   } 
} 
