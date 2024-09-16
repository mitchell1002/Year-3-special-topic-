package Task4;

import java.util.*;

public class MinimumValidPath {

    // Edge class representing an edge from one vertex to another with a certain weight
    static class Edge {
        int destination; // Target vertex of the edge
        int weight;      // Weight of the edge

        Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    private final int vertices; // Number of vertices
    private final List<List<Edge>> adjacencyList; // Adjacency list representation of the graph

    public MinimumValidPath(int vertices) {
        this.vertices = vertices;
        adjacencyList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    // Method to add an edge to the graph
    public void addEdge(int source, int destination, int weight) {
        adjacencyList.get(source - 1).add(new Edge(destination - 1, weight));
    }

    // Helper method to find the vertex with minimum distance value
    private int findMinDistanceVertex(int[] distances, boolean[] processedVertices) {
        int minDistance = Integer.MAX_VALUE;
        int minVertexIndex = -1;

        // Search for the unprocessed vertex with the smallest distance
        for (int i = 0; i < vertices; i++) {
            if (!processedVertices[i] && distances[i] <= minDistance) {
                minDistance = distances[i];
                minVertexIndex = i;
            }
        }
        return minVertexIndex;
    }

    // Check if an edge is valid for traversal based on specific conditions
    private boolean isValidEdge(int source, int destination, int weight) {
        return weight > 0; // Modify this condition for more complex validation if needed
    }

    // Dijkstra's algorithm implementation with special vertex constraints
    public void findMinimumValidPath(int source, int destination, Set<Integer> specialVertices) {
        int[] distances = new int[vertices]; // Array to store shortest distance to each vertex
        boolean[] processedVertices = new boolean[vertices]; // Track processed vertices
        int[] specialVertexCount = new int[vertices]; // Track the number of special vertices in the path
        int[] previousVertex = new int[vertices]; // Track previous vertex for path reconstruction

        // Initialize distances to infinity and previousVertex to -1 (unvisited)
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(previousVertex, -1);
        distances[source] = 0; // Distance to source is 0

        // Process the vertices one by one
        for (int i = 0; i < vertices - 1; i++) {
            int u = findMinDistanceVertex(distances, processedVertices);
            if (u == -1) break; // If no more reachable vertices

            processedVertices[u] = true; // Mark the current vertex as processed

            // Traverse through all adjacent vertices of the current vertex
            for (Edge edge : adjacencyList.get(u)) {
                int v = edge.destination;
                int weight = edge.weight;

                // Check if a shorter path exists and the edge is valid
                if (!processedVertices[v] && distances[u] != Integer.MAX_VALUE &&
                        distances[u] + weight < distances[v] && isValidEdge(u, v, weight)) {
                    distances[v] = distances[u] + weight;
                    previousVertex[v] = u;
                    specialVertexCount[v] = specialVertexCount[u] + (specialVertices.contains(v + 1) ? 1 : 0);
                }
            }
        }

        // Check if a valid path with at least one special vertex is found
        if ((specialVertexCount[destination] > 0 || specialVertices.contains(destination + 1)) &&
                distances[destination] != Integer.MAX_VALUE) {
            System.out.println("Minimum cost: " + distances[destination]);
            printPath(previousVertex, source, destination);
        } else {
            System.out.println("No valid path from " + (source + 1) + " to " + (destination + 1)
                    + " with at least one special vertex.");
        }
    }

    // Method to print the path from source to destination
    private void printPath(int[] previousVertex, int source, int destination) {
        List<Integer> path = new ArrayList<>();
        for (int v = destination; v != -1; v = previousVertex[v]) {
            path.add(v + 1); // Convert to 1-based index for printing
        }
        Collections.reverse(path); // Reverse the path to get the correct order
        System.out.println("Path: " + String.join(" -> ", path.stream().map(String::valueOf).toArray(String[]::new)));
    }

    // Generate a random graph with n vertices and m edges for testing
    public static MinimumValidPath generateRandomGraph(int n, int m) {
        MinimumValidPath graph = new MinimumValidPath(n);
        Random rand = new Random();
        for (int i = 0; i < m; i++) {
            int u, v;
            do {
                u = rand.nextInt(n) + 1;
                v = rand.nextInt(n) + 1;
            } while (u == v); // Avoid self-loops
            int weight = rand.nextInt(100) + 1; // Random weight between 1 and 100
            graph.addEdge(u, v, weight);
        }
        return graph;
    }


}
