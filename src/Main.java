import Task4.MinimumValidPath;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static Task4.MinimumValidPath.generateRandomGraph;
// Main method
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MinimumValidPath graph;

        System.out.println("Choose option: 1 for generated graph, 2 for custom input:");
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.println("Enter the number of vertices and edges:");
            int vertices = scanner.nextInt();
            int edges = scanner.nextInt();
            graph = generateRandomGraph(vertices, edges);
            System.out.println("Using generated graph.");
        } else {
            System.out.println("Enter the number of vertices:");
            int vertices = scanner.nextInt();
            graph = new MinimumValidPath(vertices);
            System.out.println("Enter the number of edges:");
            int edges = scanner.nextInt();
            System.out.println("Enter each edge as (source, destination, weight):");
            for (int i = 0; i < edges; i++) {
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                int weight = scanner.nextInt();
                graph.addEdge(u, v, weight);
            }
        }

        System.out.println("Enter the number of special vertices:");
        int specialVertexCount = scanner.nextInt();
        Set<Integer> specialVertices = new HashSet<>();
        System.out.println("Enter the indices of the special vertices:");
        for (int i = 0; i < specialVertexCount; i++) {
            specialVertices.add(scanner.nextInt());
        }

        System.out.println("Enter the source and destination vertices:");
        int source = scanner.nextInt() - 1;
        int destination = scanner.nextInt() - 1;

        graph.findMinimumValidPath(source, destination, specialVertices);
        scanner.close();
    }
}