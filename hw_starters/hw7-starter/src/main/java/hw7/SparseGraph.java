package hw7;

import exceptions.InsertionException;
import exceptions.PositionException;
import exceptions.RemovalException;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

/**
 * An implementation of Graph ADT using incidence lists
 * for sparse graphs where most nodes aren't connected.
 *
 * @param <V> Vertex element type.
 * @param <E> Edge element type.
 */
@SuppressWarnings("unchecked")
public class SparseGraph<V, E> implements Graph<V, E> {

  HashSet<VertexNode<V>> vertexHash;
  HashSet<EdgeNode<E>> edgeHash;

  //Counter of edges for unique id's for hashset
  private int edgeCount;

  /**
   * Constructor to initialize collection of vertices for graph.
   */
  public SparseGraph() {
    vertexHash = new HashSet<>();
    edgeHash = new HashSet<>();
    edgeCount = 0;
  }
  // TODO You may need to add fields/constructor here!

  // Converts the vertex back to a VertexNode to use internally
  private VertexNode<V> convert(Vertex<V> v) throws PositionException {
    try {
      VertexNode<V> gv = (VertexNode<V>) v;
      if (gv.owner != this) {
        throw new PositionException();
      }
      return gv;
    } catch (NullPointerException | ClassCastException ex) {
      throw new PositionException();
    }
  }

  // Converts and edge back to a EdgeNode to use internally
  private EdgeNode<E> convert(Edge<E> e) throws PositionException {
    try {
      EdgeNode<E> ge = (EdgeNode<E>) e;
      if (ge.owner != this) {
        throw new PositionException();
      }
      return ge;
    } catch (NullPointerException | ClassCastException ex) {
      throw new PositionException();
    }
  }

  @Override
  public Vertex<V> insert(V v) throws InsertionException {
    if (v == null) {
      throw new InsertionException();
    }

    VertexNode<V> insertedVertex = new VertexNode<>(v, this);

    if (!vertexHash.add(insertedVertex)) {
      //false return indicates that the element was found in the hash set
      throw new InsertionException();
    }

    return insertedVertex;
  }

  @Override
  public Edge<E> insert(Vertex<V> from, Vertex<V> to, E e)
      throws PositionException, InsertionException {

    //Handles null, wrong owner, and wrong type
    VertexNode<V> fromNode = convert(from);
    VertexNode<V> toNode = convert(to);

    //Check self-loop
    if (fromNode.equals(toNode)) {
      throw new InsertionException();
    }

    EdgeNode<E> insertedEdge = new EdgeNode<>(fromNode, toNode, e, this);

    //putIfAbsent returns null if key was unmapped before
    if (fromNode.outgoingEdges.putIfAbsent(toNode, insertedEdge) != null) {
      throw new InsertionException();
    }

    //Redundant check for safety
    if (toNode.incomingEdges.putIfAbsent(fromNode, insertedEdge) != null) {
      throw new InsertionException();
    }

    edgeHash.add(insertedEdge);

    return insertedEdge;
  }

  @Override
  public V remove(Vertex<V> v) throws PositionException, RemovalException {

    //Throws all necessary PositionExceptions
    VertexNode<V> removedVertex = convert(v);

    V toReturn = removedVertex.data;
    if (!removedVertex.incomingEdges.isEmpty() ||  !removedVertex.outgoingEdges.isEmpty()) {
      throw new RemovalException();
    }

    if (!vertexHash.remove(removedVertex)) {
      //Removal failed, vertex must not be in the set
      throw new PositionException();
    }

    return toReturn;
  }

  @Override
  public E remove(Edge<E> e) throws PositionException {

    //Handles all position exceptions
    EdgeNode<E> removedEdge = convert(e);
    VertexNode<V> outgoingRemoval = removedEdge.from;
    VertexNode<V> incomingRemoval = removedEdge.to;

    //Remove from incoming and outgoing vertex HashMaps
    outgoingRemoval.outgoingEdges.remove(incomingRemoval);
    incomingRemoval.incomingEdges.remove(outgoingRemoval);

    if (!edgeHash.remove(removedEdge)) {
      //Removal failed, edge must not be in the set
      throw new PositionException();
    }

    return removedEdge.data;
  }

  @Override
  public Iterable<Vertex<V>> vertices() {
    return (Iterable<Vertex<V>>) (Iterable<?>) Collections.unmodifiableSet(vertexHash);
  }

  @Override
  public Iterable<Edge<E>> edges() {
    return (Iterable<Edge<E>>) (Iterable<?>) Collections.unmodifiableSet(edgeHash);
  }

  @Override
  public Iterable<Edge<E>> outgoing(Vertex<V> v) throws PositionException {

    VertexNode<V> vertex = convert(v);

    return (Iterable<Edge<E>>) (Iterable<?>) Collections.unmodifiableCollection(vertex.outgoingEdges.values());
  }

  @Override
  public Iterable<Edge<E>> incoming(Vertex<V> v) throws PositionException {
    VertexNode<V> vertex = convert(v);

    return (Iterable<Edge<E>>) (Iterable<?>) Collections.unmodifiableCollection(vertex.incomingEdges.values());
  }

  @Override
  public Vertex<V> from(Edge<E> e) throws PositionException {

    //Handles null, not in class, and wrong owner
    EdgeNode<E> edgeNode = convert(e);

    //Handles not in set
    if (!edgeHash.contains(edgeNode)) {
      throw new PositionException();
    } else {
      return edgeNode.from;
    }
  }

  @Override
  public Vertex<V> to(Edge<E> e) throws PositionException {

    //Handles null, not in class, and wrong owner
    EdgeNode<E> edgeNode = convert(e);

    //Handles not in set
    if (!edgeHash.contains(edgeNode)) {
      throw new PositionException();
    } else {
      return edgeNode.to;
    }
  }

  @Override
  public void label(Vertex<V> v, Object l) throws PositionException {

    //Handles null, not in class, and wrong owner
    VertexNode<V> vertexNode = convert(v);

    //Handles not in set
    if (!vertexHash.contains(vertexNode)) {
      throw new PositionException();
    } else {
      vertexNode.setLabel(l);
    }
    return;
  }

  @Override
  public void label(Edge<E> e, Object l) throws PositionException {

    //Handles null, not in class, and wrong owner
    EdgeNode<E> edgeNode = convert(e);

    //Handles not in set
    if (!edgeHash.contains(edgeNode)) {
      throw new PositionException();
    } else {
      edgeNode.setLabel(l);
    }
    return;
  }

  @Override
  public Object label(Vertex<V> v) throws PositionException {

    //Handles null, not in class, and wrong owner
    VertexNode<V> vertexNode = convert(v);

    //Handles not in set
    if (!vertexHash.contains(vertexNode)) {
      throw new PositionException();
    } else {
      return vertexNode.label;
    }
  }

  @Override
  public Object label(Edge<E> e) throws PositionException {

    //Handles null, not in class, and wrong owner
    EdgeNode<E> edgeNode = convert(e);

    //Handles not in set
    if (!edgeHash.contains(edgeNode)) {
      throw new PositionException();
    } else {
      return edgeNode.label;
    }
  }

  @Override
  public void clearLabels() {
    for  (VertexNode<V> vertexNode : vertexHash) {
      vertexNode.label = null;
    }
    for  (EdgeNode<E> edgeNode : edgeHash) {
      edgeNode.label = null;
    }
  }

  @Override
  public String toString() {
    GraphPrinter<V, E> gp = new GraphPrinter<>(this);
    return gp.toString();
  }

  // Class for a vertex of type V
  private final class VertexNode<V> implements Vertex<V> {
    V data;
    Graph<V, E> owner;
    Object label;
    HashMap<VertexNode<V>, EdgeNode<E>> outgoingEdges;
    HashMap<VertexNode<V>, EdgeNode<E>> incomingEdges;

    VertexNode(V v,  Graph<V, E> g) {
      this.data = v;
      this.label = null;
      this.owner = g;
      this.outgoingEdges = new HashMap<>();
      this.incomingEdges = new HashMap<>();
    }

    @Override
    public V get() {
      return this.data;
    }

    public void setLabel(Object l) {
      this.label = l;
      return;
    }

    //Use for HashSet
    @Override
    public int hashCode() {
      return data.hashCode();
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      } else if (!(o instanceof VertexNode)) {
        return false;
      } else {
        VertexNode<?> vertexNode = (VertexNode<?>) o;
        return (this.data.equals(vertexNode.data) && this.owner == vertexNode.owner);
      }
    }
  }

  //Class for an edge of type E
  private final class EdgeNode<E> implements Edge<E> {
    E data;
    Graph<V, E> owner;
    VertexNode<V> from;
    VertexNode<V> to;
    Object label;

    int uniqueID;

    // Constructor for a new edge
    EdgeNode(VertexNode<V> f, VertexNode<V> t, E e, Graph<V, E> g) {
      this.from = f;
      this.to = t;
      this.data = e;
      this.label = null;
      this.owner = g;

      this.uniqueID = edgeCount;
      edgeCount++;
    }


    @Override
    public E get() {
      return this.data;
    }

    public void setLabel(Object l) {
      this.label = l;
      return;
    }

    @Override
    public int hashCode() {
      return uniqueID;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      } else if (!(o instanceof EdgeNode)) {
        return false;
      } else {
        EdgeNode<?> edgeNode = (EdgeNode<?>) o;
        return edgeNode.uniqueID == uniqueID;
      }
    }
  }
}
