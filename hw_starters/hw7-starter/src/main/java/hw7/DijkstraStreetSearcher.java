package hw7;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraStreetSearcher extends StreetSearcher {

  /**
   * Creates a StreetSearcher object.
   *
   * @param graph an implementation of Graph ADT.
   */
  public DijkstraStreetSearcher(Graph<String, String> graph) {
    super(graph);
  }

  @Override
  public void findShortestPath(String startName, String endName) {
    final Vertex<String> SV = vertices.get(startName);
    final Vertex<String> EV = vertices.get(endName);

    //Check endpoints
    if (!goodEndpoints(startName, endName)) {
      return;
    }

    PriorityQueue<VertexWrapper> pq = new PriorityQueue<>(new CompareVertices());

    //Map stored as <Edge, Previous Edge>
    HashMap<Vertex<String>, Vertex<String>> prevMap = new HashMap<>();

    //Call helper method to do the algorithm; the distance returned by it will be the shortest distance if path exists
    //Otherwise, we need to overwrite it.
    double totalDist = dijkstraAlgo(SV, EV, pq, prevMap);

    if (!prevMap.containsKey(EV)) {
      totalDist = -1;
    }

    // These method calls will create and print the path for you
    List<Edge<String>> path = getPath(EV, SV);
    if (VERBOSE) {
      printPath(path, totalDist);
    }
  }

  //Helper method to check validity of endpoints, print invalid endpoint
  private boolean goodEndpoints(String startName, String endName) {
    Vertex<String> start = vertices.get(startName);
    Vertex<String> end = vertices.get(endName);

    try {
      checkValidEndpoint(startName);
      checkValidEndpoint(endName);
      return true;
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      return false;
    }
  }

  //Helper method to actually run the algorithm
  private double dijkstraAlgo(Vertex<String> startVertex, Vertex<String> endVertex, PriorityQueue<VertexWrapper> pq,
                              HashMap<Vertex<String>, Vertex<String>> prevMap) {
    double totalDist = -1;

    //Seeds pq with starting vertex
    pq.add(new VertexWrapper(startVertex, (Double) 0.0, null, null));
    graph.label(startVertex, null);

    while (!prevMap.containsKey(endVertex) && !pq.isEmpty()) {
      VertexWrapper curr = pq.remove();

      //Prevent updating values if a shorter path was already found
      if (prevMap.containsKey(curr.vertex)) {
        continue;
      }

      //If removed, this is the shortest distance to the unexplored vertex
      prevMap.put(curr.vertex, curr.prevVertex);
      graph.label(curr.vertex, curr.road);
      totalDist = curr.distance;

      for (Edge<String> edge : graph.outgoing(curr.vertex)) {
        Vertex<String> nextVertex = graph.to(edge);
        pq.add(new VertexWrapper(nextVertex, curr.distance + (Double) graph.label(edge), curr.vertex, edge));
      }
    }

    return totalDist;
  }

  //Comparator class to change ordering of edges to follow the distances on the labels
  private static class CompareVertices implements Comparator<VertexWrapper> {

    @Override
    public int compare(VertexWrapper o1, VertexWrapper o2) {
      return o1.distance.compareTo(o2.distance);
    }
  }

  //Wrapper class to load edges into priority queue on basis of distance
  private static class VertexWrapper {
    Vertex<String> vertex;
    Double distance;
    Vertex<String> prevVertex;
    Edge<String> road;

    VertexWrapper(Vertex<String> vertex, Double distance, Vertex<String> prevVertex, Edge<String> road) {
      this.vertex = vertex;
      this.distance = distance;
      this.prevVertex = prevVertex;
      this.road = road;
    }
  }
}
