package listlinked; 

import graph.Edge; 
import graph.Vertex; 
 
import java.util.LinkedList; 
 
public class AdjList<E> { 
   private Vertex<E> vertex; 
   private LinkedList<Edge<E>> edges; 
 
   public AdjList(Vertex<E> vertex) { 
       this.vertex = vertex; 
       this.edges = new LinkedList<>(); 
   } 
 
   public Vertex<E> getVertex() { 
       return vertex; 
   } 
 
   public LinkedList<Edge<E>> getEdges() { 
       return edges; 
   } 
}
