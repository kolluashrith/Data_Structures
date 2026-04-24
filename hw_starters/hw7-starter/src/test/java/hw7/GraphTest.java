package hw7;

import exceptions.InsertionException;
import exceptions.PositionException;
import exceptions.RemovalException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public abstract class GraphTest {

  protected Graph<String, String> graph;

  @BeforeEach
  public void setupGraph() {
    this.graph = createGraph();
  }

  protected abstract Graph<String, String> createGraph();

  @Test
  @DisplayName("insert(v) returns a vertex with given data")
  public void canGetVertexAfterInsert() {
    Vertex<String> v1 = graph.insert("v1");
    assertEquals("v1", v1.get());
  }

  @Test
  @DisplayName("insert(v) throws exception when v is null")
  public void insertNullVertexThrowsException() {
    try {
      Vertex<String> v1 = graph.insert(null);
      fail("InsertionException should have been thrown for null vertex");
    } catch (InsertionException e) {
      return;
    }
  }

  @Test
  @DisplayName("insert(v) throws exception when v is already in graph")
  public void insertDuplicateVertexThrowsException() {
    try {
      Vertex<String> v1 = graph.insert("v1");
      Vertex<String> v1_duplicate = graph.insert("v1");
      fail("InsertionException should have been thrown for duplicate vertex");
    } catch (InsertionException e) {
      return;
    }
  }

  @Test
  @DisplayName("insert(v) handles multiple vertices")
  public void insertMultipleVertices() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Vertex<String> v3 = graph.insert("v3");
    assertEquals("v1", v1.get());
    assertEquals("v2", v2.get());
    assertEquals("v3", v3.get());
  }

  @Test
  @DisplayName("insert(U, V, e) returns an edge with given data")
  public void canGetEdgeAfterInsert() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Edge<String> e1 = graph.insert(v1, v2, "v1-v2");
    assertEquals("v1-v2", e1.get());
  }

  @Test
  @DisplayName("insert(null, V, e) throws exception")
  public void insertEdgeThrowsPositionExceptionWhenFirstVertexIsNull() {
    try {
      Vertex<String> v = graph.insert("v");
      graph.insert(null, v, "e");
      fail("The expected exception was not thrown");
    } catch (PositionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("insert(V, null, e) throws exception")
  public void insertEdgeThrowsPositionExceptionWhenSecondVertexIsNull() {
    try {
      Vertex<String> v = graph.insert("v");
      graph.insert(v, null, "e");
      fail("The expected exception was not thrown");
    } catch (PositionException ex) {
      return;
    }
  }

  @Test
  @DisplayName("insert(V1, V2, null) doesn't throw exception")
  public void insertEdgeDoesNotThrowPositionExceptionWhenEdgeLabelIsNull() {
    try {
      Vertex<String> v1 = graph.insert("v1");
      Vertex<String> v2 = graph.insert("v2");
      graph.insert(v1, v2, null);
      return;
    } catch (PositionException ex) {
      fail("Exception was thrown for null edge when it should not have been");
    }
  }

  @Test
  @DisplayName("insert(V, V, e) throws InsertionException for self-loop")
  public void insertSelfLoopThrowsInsertionException() {
    try {
      Vertex<String> v = graph.insert("v");
      graph.insert(v, v, "e");
      fail("InsertionException should have been thrown for self-loop");
    } catch (InsertionException e) {
      return;
    }
  }

  @Test
  @DisplayName("insert duplicate edge throws InsertionException")
  public void insertDuplicateEdgeThrowsInsertionException() {
    try {
      Vertex<String> v1 = graph.insert("v1");
      Vertex<String> v2 = graph.insert("v2");
      graph.insert(v1, v2, "e1");
      graph.insert(v1, v2, "e2");
      fail("InsertionException should have been thrown for duplicate edge");
    } catch (InsertionException e) {
      return;
    }
  }

  @Test
  @DisplayName("inserting edge in opposite direction doesn't throw InsertionException")
  public void insertOppositeDirectionEdgeDoesNotThrowInsertionException() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    graph.insert(v1, v2, "forward");
    Edge<String> reverse = graph.insert(v2, v1, "backward");
    assertEquals("backward", reverse.get());
  }

  @Test
  @DisplayName("insert edge with vertex from another graph throws PositionException")
  public void insertEdgeWithVertexFromDifferentGraphThrowsPositionException() {
    Graph<String, String> graph2 = createGraph();
    Vertex<String> vertexFrom2 = graph2.insert("v1");
    Vertex<String> vertexFrom1 = graph.insert("v2");
    try {
      graph.insert(vertexFrom2, vertexFrom1, "e");
      fail("PositionException should have been thrown for vertex not belonging to this graph");
    } catch (PositionException e) {
      return;
    }
  }

  @Test
  @DisplayName("remove(v) returns element of removed vertex")
  public void removeVertexReturnsElement() {
    Vertex<String> v1 = graph.insert("v1");
    assertEquals("v1", graph.remove(v1));
  }

  @Test
  @DisplayName("remove(v) fully removes vertex from graph")
  public void removeVertexFullyRemovesIt() {
    Vertex<String> v1 = graph.insert("v1");
    graph.remove(v1);
    Iterable<Vertex<String>> vertices = graph.vertices();
    for (Vertex<String> v : vertices) {
      assertNotEquals("v1", v.get());
    }
  }

  @Test
  @DisplayName("remove(v) throws RemovalException when vertex is source of one or more edges")
  public void removeVertexWithRemainingEdgesThrowsRemovalException() {
    try {
      Vertex<String> v1 = graph.insert("v1");
      Vertex<String> v2 = graph.insert("v2");
      graph.insert(v1, v2, "e");
      graph.remove(v1);
      fail("RemovalException should have been thrown");
    } catch (RemovalException e) {
      return;
    }
  }

  @Test
  @DisplayName("remove(v) throws RemovalException when vertex is the endpoint of an edge")
  public void removeVertexThatIsEdgeEndpointThrowsRemovalException() {
    try {
      Vertex<String> v1 = graph.insert("v1");
      Vertex<String> v2 = graph.insert("v2");
      graph.insert(v1, v2, "e");
      graph.remove(v2);
      fail("RemovalException should have been thrown");
    } catch (RemovalException e) {
      return;
    }
  }

  @Test
  @DisplayName("remove(v) throws PositionException for null vertex")
  public void removeNullVertexThrowsPositionException() {
    try {
      graph.remove((Vertex<String>) null); //Need cast for ambiguous call
      fail("PositionException should have been thrown");
    } catch (PositionException e) {
      return;
    }
  }

  @Test
  @DisplayName("remove(v) throws PositionException for vertex that was already removed")
  public void removeVertexAgainThrowsPositionException() {
    try {
      Vertex<String> v1 = graph.insert("v1");
      graph.remove(v1);
      graph.remove(v1);
      fail("PositionException should have been thrown for trying to remove a previously removed vertex");
    } catch (PositionException e) {
      return;
    }
  }

  @Test
  @DisplayName("remove(v) permits inserting same vertex again after removal")
  public void canReinsertVertexAfterRemoval() {
    Vertex<String> v1 = graph.insert("v1");
    graph.remove(v1);
    Vertex<String> v1_again = graph.insert("v1");
    assertEquals("v1", v1_again.get());
  }

  @Test
  @DisplayName("remove(e) returns element of removed edge")
  public void removeEdgeReturnsElement() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Edge<String> e = graph.insert(v1, v2, "v1-v2");
    assertEquals("v1-v2", graph.remove(e));
  }

  @Test
  @DisplayName("remove(e) fully removes edge from graph")
  public void removeEdgeFullyRemovesIt() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Edge<String> e = graph.insert(v1, v2, "v1-v2");
    graph.remove(e);
    Iterable<Edge<String>> edges = graph.edges();
    for (Edge<String> edge : edges) {
      assertNotEquals("v1-v2", edge.get());
    }
  }

  @Test
  @DisplayName("remove(e) removes edges to allow vertices to be removed")
  public void removeEdgeFreesVertices() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Edge<String> e1 = graph.insert(v1, v2, "e1");
    Edge<String> e2 = graph.insert(v2, v1, "e2");
    graph.remove(e1);
    graph.remove(e2);
    graph.remove(v1);
    graph.remove(v2);

    Iterable<Vertex<String>> vertices = graph.vertices();
    for (Vertex<String> v : vertices) {
      assertNotEquals("v1", v.get());
      assertNotEquals("v2", v.get());
    }
  }

  @Test
  @DisplayName("remove(e) throws PositionException for null edge")
  public void removeNullEdgeThrowsPositionException() {
    try {
      graph.remove((Edge<String>) null); //Need cast to avoid ambiguous call
      fail("PositionException should have been thrown");
    } catch (PositionException e) {
      return;
    }
  }

  @Test
  @DisplayName("remove(e) throws PositionException for previously removed edge")
  public void removePreviouslyRemovedEdgeThrowsPositionException() {
    try {
      Vertex<String> v1 = graph.insert("v1");
      Vertex<String> v2 = graph.insert("v2");
      Edge<String> e = graph.insert(v1, v2, "e");
      graph.remove(e);
      graph.remove(e);
      fail("PositionException should have been thrown for removing same edge again");
    } catch (PositionException e) {
      return;
    }
  }

  @Test
  @DisplayName("remove(e) allows edge to be inserted again after its removal")
  public void canReinsertEdgeAfterRemoval() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Edge<String> e = graph.insert(v1, v2, "e");
    graph.remove(e);
    Edge<String> e_again = graph.insert(v1, v2, "e");
    assertEquals("e", e_again.get());
  }

  @Test
  @DisplayName("vertices() returns empty iterable on new graph")
  public void verticesReturnsEmptyIterableOnNewGraph() {

    Iterable<Vertex<String>> vertices = graph.vertices();

    if (vertices.iterator().hasNext()) {
      fail("There should be no vertices in a new graph");
    }
  }

  @Test
  @DisplayName("vertices() returns iterable with all vertices")
  public void verticesReturnsAllVertices() {
    ArrayList<Vertex<String>> trueVertices = new ArrayList<>();

    trueVertices.add(graph.insert("v1"));
    trueVertices.add(graph.insert("v2"));
    trueVertices.add(graph.insert("v3"));

    Iterable<Vertex<String>> vertices = graph.vertices();

    for (Vertex<String> v : vertices) {
      if (!trueVertices.remove(v)) {
        fail("Vertex in iterable was not found in graph");
      }
    }

    if (!trueVertices.isEmpty()) {
      fail("Incorrect number of vertices in the graph or incorrect vertices present");
      //Do not check order because order does not matter
    }
  }

  @Test
  @DisplayName("vertices() returns correct iterable after removal")
  public void verticesWorksAfterVertexRemoval() {
    Vertex<String> v1 = graph.insert("v1");

    ArrayList<Vertex<String>> trueVertices = new ArrayList<>();
    trueVertices.add(graph.insert("v2"));
    trueVertices.add(graph.insert("v3"));

    graph.remove(v1);

    Iterable<Vertex<String>> vertices = graph.vertices();

    for (Vertex<String> v : vertices) {
      if (!trueVertices.remove(v)) {
        fail("Vertex in iterable was not found in graph");
      }
    }

    if (!trueVertices.isEmpty()) {
      fail("Incorrect number of vertices in the graph or incorrect vertices present");
      //Do not check order because order does not matter
    }
  }

  @Test
  @DisplayName("edges() returns empty iterable on new graph")
  public void edgesReturnsEmptyIterableOnNewGraph() {

    Iterable<Edge<String>> edges = graph.edges();

    if (edges.iterator().hasNext()) {
      fail("There should be no edges in a new graph");
    }
  }

  @Test
  @DisplayName("edges() returns iterable with all edges")
  public void edgesReturnsAllEdges() {
    ArrayList<Edge<String>> trueEdges = new ArrayList<>();

    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Vertex<String> v3 = graph.insert("v3");
    trueEdges.add(graph.insert(v1, v2, "v1-v2"));
    trueEdges.add(graph.insert(v2, v3, "v2-v3"));
    trueEdges.add(graph.insert(v3, v1, "v3-v1"));

    Iterable<Edge<String>> edges = graph.edges();

    for (Edge<String> e : edges) {
      if (!trueEdges.remove(e)) {
        fail("Edge in iterable was not present in the graph");
      }
    }

    if (!trueEdges.isEmpty()) {
      fail("Incorrect number of edges in the graph or incorrect edges present");
    }
  }

  @Test
  @DisplayName("edges() returns iterable with all edges even when edge data is null")
  public void edgesReturnsAllEdgesWhenEIsNull() {
    ArrayList<Edge<String>> trueEdges = new ArrayList<>();

    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Vertex<String> v3 = graph.insert("v3");
    trueEdges.add(graph.insert(v1, v2, null));
    trueEdges.add(graph.insert(v2, v3, null));
    trueEdges.add(graph.insert(v3, v1, null));

    Iterable<Edge<String>> edges = graph.edges();
    for (Edge<String> e : edges) {
      if (!trueEdges.remove(e)) {
        fail("Edge in iterable was not present in the graph");
      }
    }

    if (!trueEdges.isEmpty()) {
      fail("Incorrect number of edges in the graph or incorrect edges present");
    }
  }

  @Test
  @DisplayName("edges() returns correct iterable after removal")
  public void edgesWorksAfterEdgeRemoval() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Vertex<String> v3 = graph.insert("v3");

    ArrayList<Edge<String>> trueEdges = new ArrayList<>();
    Edge<String> e1 = graph.insert(v1, v2, "v1-v2");
    trueEdges.add(graph.insert(v2, v3, "v2-v3"));

    graph.remove(e1);

    Iterable<Edge<String>> edges = graph.edges();

    for (Edge <String> e : edges) {
      if (!trueEdges.remove(e)) {
        fail("Edge in iterable was not found in graph");
      }
    }

    if (!trueEdges.isEmpty()) {
      fail("Incorrect number of edges in the graph or incorrect edges present");
      //Do not check order because order does not matter
    }
  }

  @Test
  @DisplayName("outgoing(v) throws PositionException for null v")
  public void outgoingThrowsPositionExceptionForNullV() {
    try {
      graph.outgoing(null);
      fail("PositionException should have been thrown for null v");
    } catch (PositionException e) {
      return;
    }
  }

  @Test
  @DisplayName("outgoing(v) returns empty iterable for isolated vertex")
  public void outgoingEmptyForIsolatedVertex() {
    Vertex<String> v = graph.insert("v");
    Iterable<Edge<String>> edges = graph.outgoing(v);
    if (edges.iterator().hasNext()) {
      fail("There should be no outgoing edges in the graph for an isolated vertex");
    }
  }

  @Test
  @DisplayName("outgoing(v) returns correct edges")
  public void outgoingReturnsCorrectEdges() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Vertex<String> v3 = graph.insert("v3");

    ArrayList<Edge<String>> trueEdges = new ArrayList<>();
    trueEdges.add(graph.insert(v1, v2, "v1-v2"));
    trueEdges.add(graph.insert(v1, v3, "v1-v3"));

    graph.insert(v2, v3, "v2-v3");

    Iterable<Edge<String>> edges = graph.outgoing(v1);
    for (Edge<String> e : edges) {
      if (!trueEdges.remove(e)) {
        fail("Edge in iterable was not present in the graph");
      }
    }
    if (!trueEdges.isEmpty()) {
      fail("Incorrect number of edges in the graph or incorrect edges present");
    }
  }

  @Test
  @DisplayName("outgoing(v) returns correct iterable after edge removal")
  public void outgoingReturnCorrectIterableAfterEdgeRemoval() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Vertex<String> v3 = graph.insert("v3");

    ArrayList<Edge<String>> trueEdges = new ArrayList<>();
    trueEdges.add(graph.insert(v1, v2, "v1-v2"));
    Edge<String> toRemove = graph.insert(v1, v3, "v1-v3");

    graph.remove(toRemove);

    Iterable<Edge<String>> edges = graph.outgoing(v1);
    for (Edge<String> e : edges) {
      if (!trueEdges.remove(e)) {
        fail("Edge in iterable was not present in the graph");
      }
    }
    if (!trueEdges.isEmpty()) {
      fail("Incorrect number of edges in the graph or incorrect edges present");
    }
  }

  @Test
  @DisplayName("incoming(v) throws PositionException for null v")
  public void incomingThrowsPositionExceptionForNullV() {
    try {
      graph.incoming(null);
      fail("PositionException should have been thrown for null v");
    } catch (PositionException e) {
      return;
    }
  }

  @Test
  @DisplayName("incoming(v) returns empty iterable for isolated vertex")
  public void incomingEmptyForIsolatedVertex() {
    Vertex<String> v = graph.insert("v");
    Iterable<Edge<String>> edges = graph.incoming(v);
    if (edges.iterator().hasNext()) {
      fail("There should be no incoming edges in the graph for an isolated vertex");
    }
  }

  @Test
  @DisplayName("incoming(v) returns correct edges")
  public void incomingReturnsCorrectEdges() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Vertex<String> v3 = graph.insert("v3");

    ArrayList<Edge<String>> trueEdges = new ArrayList<>();
    trueEdges.add(graph.insert(v1, v3, "v1-v3"));
    trueEdges.add(graph.insert(v2, v3, "v2-v3"));

    graph.insert(v1, v2, "v1-v2");

    Iterable<Edge<String>> edges = graph.incoming(v3);
    for (Edge<String> e : edges) {
      if (!trueEdges.remove(e)) {
        fail("Edge in iterable was not present in the graph");
      }
    }
    if (!trueEdges.isEmpty()) {
      fail("Incorrect number of edges in the graph or incorrect edges present");
    }
  }

  @Test
  @DisplayName("incoming(v) returns correct edges")
  public void incomingReturnsCorrectIterableAfterEdgeRemoval() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Vertex<String> v3 = graph.insert("v3");

    ArrayList<Edge<String>> trueEdges = new ArrayList<>();
    trueEdges.add(graph.insert(v1, v3, "v1-v3"));
    Edge<String> toRemove = graph.insert(v2, v3, "v2-v3");

    graph.remove(toRemove);

    Iterable<Edge<String>> edges = graph.incoming(v3);
    for (Edge<String> e : edges) {
      if (!trueEdges.remove(e)) {
        fail("Edge in iterable was not present in the graph");
      }
    }
    if (!trueEdges.isEmpty()) {
      fail("Incorrect number of edges in the graph or incorrect edges present");
    }
  }

  //Need Tests for from/to and down

  @Test
  @DisplayName("from() returns source vertex of edge")
  public void fromReturnsSourceVertex() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Edge<String> e = graph.insert(v1, v2, "v1-v2");

    //This only works because the inner class had a equals() method defined; it checks both data and owner
    assertEquals(v1, graph.from(e));
  }

  @Test
  @DisplayName("to(e) returns the destination vertex of an edge")
  public void toReturnsDestinationVertex() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Edge<String> e = graph.insert(v1, v2, "v1-v2");

    //This only works because the inner class had a equals() method defined; it checks both data and owner
    assertEquals(v2, graph.to(e));
  }

  @Test
  @DisplayName("from() throws PositionException with null input")
  public void fromThrowsPositionExceptionWithNullInput() {
    try {
      graph.from(null);
      fail("PositionException should have been thrown");
    } catch (PositionException e) {
      return;
    }
  }

  @Test
  @DisplayName("from() throws PositionException with removed edge input")
  public void fromRemovedEdgeThrowsPositionException() {
    try {
      Vertex<String> v1 = graph.insert("v1");
      Vertex<String> v2 = graph.insert("v2");
      Edge<String> e = graph.insert(v1, v2, "v1-v2");
      graph.remove(e);
      graph.from(e);
      fail("PositionException should have been thrown");
    } catch (PositionException e) {
      return;
    }
  }

  @Test
  @DisplayName("to() throws PositionException with null input")
  public void toThrowsPositionExceptionWithNullInput() {
    try {
      graph.to(null);
      fail("PositionException should have been thrown");
    } catch (PositionException e) {
      return;
    }
  }

  @Test
  @DisplayName("to() throws PositionException for removed edge input")
  public void toRemovedEdgeThrowsPositionException() {
    try {
      Vertex<String> v1 = graph.insert("v1");
      Vertex<String> v2 = graph.insert("v2");
      Edge<String> e = graph.insert(v1, v2, "v1-v2");
      graph.remove(e);
      graph.to(e);
      fail("PositionException should have been thrown");
    } catch (PositionException e) {
      return;
    }
  }

  @Test
  @DisplayName("label() correctly sets and gets vertex label")
  public void labelSetsAndGetsVertexLabel() {
    Vertex<String> v1 = graph.insert("v1");
    graph.label(v1, "labeled");
    assertEquals("labeled", graph.label(v1));
  }

  @Test
  @DisplayName("label() throws PositionException for null vertex when setting label")
  public void labelThrowsPositionExceptionWhenSettingToNullVertex() {
    try {
      graph.insert("v1");
      //Cast to test vertex labeling specifically
      graph.label((Vertex<String>) null, "labeled");
      fail("PositionException should have been thrown");
    } catch (PositionException e) {
      return;
    }
  }

  @Test
  @DisplayName("label() throws PositionException for labeling a removed vertex")
  public void labelingARemovedVertexThrowsPositionException() {
    try {
      Vertex<String> v1 = graph.insert("v1");
      Vertex<String> v2 = graph.insert("v2");
      graph.remove(v1);

      graph.label(v1, "removed");

      fail("PositionException should have been thrown");
    }  catch (PositionException e) {
      return;
    }
  }

  @Test
  @DisplayName("label() correctly sets and gets edge label")
  public void labelSetsAndGetsEdgeLabel() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Edge<String> e = graph.insert(v1, v2, "v1-v2");
    graph.label(e, "labeled");
    assertEquals("labeled", graph.label(e));
  }

  @Test
  @DisplayName("label() throws PositionException for null edge when setting label")
  public void labelThrowsPositionExceptionWhenSettingToNullEdge() {
    try {
      graph.insert("v1");
      graph.label((Edge<String>) null, "labeled");
      fail("PositionException should have been thrown");
    } catch (PositionException e) {
      return;
    }
  }

  @Test
  @DisplayName("label() throws PositionException for labeling a removed edge")
  public void labelingARemovedEdgeThrowsPositionException() {
    try {
      Vertex<String> v1 = graph.insert("v1");
      Vertex<String> v2 = graph.insert("v2");
      Edge<String> e = graph.insert(v1, v2, "v1-v2");

      graph.remove(e);

      graph.label(e, "removed");

      fail("PositionException should have been thrown");
    }  catch (PositionException e) {
      return;
    }
  }

  @Test
  @DisplayName("label() returns null when querying a vertex with no label set")
  public void labelReturnsNullWhenNoVertexLabelIsSet() {
    Vertex<String> v = graph.insert("v");
    assertNull(graph.label(v));
  }

  @Test
  @DisplayName("label() returns null when querying a vertex with no label set")
  public void labelReturnsNullWhenNoEdgeLabelIsSet() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Edge<String> e = graph.insert(v1, v2, "v1-v2");

    assertNull(graph.label(e));
  }

  @Test
  @DisplayName("label() throws PositionException when querying null vertex")
  public void labelThrowsPositionExceptionQueryingNullVertex() {
    try {
      graph.label((Vertex<String>) null);
      fail("PositionException should have been thrown");
    } catch (PositionException e) {
      return;
    }
  }

  @Test
  @DisplayName("label() throws PositionException when querying null edge")
  public void labelThrowsPositionExceptionQueryingNullEdge() {
    try {
      graph.label((Edge<String>) null);
      fail("PositionException should have been thrown");
    } catch (PositionException e) {
      return;
    }
  }

  @Test
  @DisplayName("label() throws PositionException for querying a removed vertex")
  public void labelThrowsPositionExceptionWhenQueryingARemovedVertex() {
    try {
      Vertex<String> v1 = graph.insert("v1");
      Vertex<String> v2 = graph.insert("v2");
      graph.label(v1, "removed");

      graph.remove(v1);

      graph.label(v1);
      fail("PositionException should have been thrown");
    }  catch (PositionException e) {
      return;
    }
  }

  @Test
  @DisplayName("label() throws PositionException for querying a removed edge")
  public void labelThrowsPositionExceptionWhenQueryingARemovedEdge() {
    try {
      Vertex<String> v1 = graph.insert("v1");
      Vertex<String> v2 = graph.insert("v2");
      Edge<String> e = graph.insert(v1, v2, "v1-v2");
      graph.label(e, "removed");

      graph.remove(e);

      graph.label(e);
      fail("PositionException should have been thrown");
    }  catch (PositionException e) {
      return;
    }
  }

  @Test
  @DisplayName("clearLabels() resets all vertex labels")
  public void clearResetsVertexLabels() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    graph.label(v1, "labeled1");
    graph.label(v2, "labeled2");
    graph.clearLabels();
    assertNull(graph.label(v1));
    assertNull(graph.label(v2));
  }

  @Test
  @DisplayName("clearLabels() resets all edge labels")
  public void clearResetsEdgeLabels() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Edge<String> e1 = graph.insert(v1, v2, "e1");
    Edge<String> e2 = graph.insert(v2, v1, "e2");
    graph.label(e1, "labeled1");
    graph.label(e2, "labeled2");
    graph.clearLabels();
    assertNull(graph.label(e1));
    assertNull(graph.label(e2));
  }

  @Test
  @DisplayName("clearLabels() on empty graph does not result in errors")
  public void clearHandlesEmptyGraph() {
    graph.clearLabels();
  }

  @Test
  @DisplayName("labels can be set after clearing")
  public void clearDoesNotBreakLabelingFunctionality() {
    Vertex<String> v1 = graph.insert("v1");
    Vertex<String> v2 = graph.insert("v2");
    Edge<String> e = graph.insert(v1, v2, "v1-v2");

    graph.label(v1, "labeledVertex");
    graph.label(e, "labeledEdge");
    graph.clearLabels();
    graph.label(v1, "labeledVertex2");
    graph.label(e, "labeledEdge2");

    assertEquals("labeledVertex2", graph.label(v1));
    assertEquals("labeledEdge2", graph.label(e));
  }
}
