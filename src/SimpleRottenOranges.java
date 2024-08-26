import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SimpleRottenOranges {
    // Directions for up, down, left, and right
    private static final int[][] DIRECTIONS = {
            {0, 1}, // right
            {1, 0}, // down
            {0, -1}, // left
            {-1, 0} // up
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Enter the number of columns: ");
        int cols = scanner.nextInt();

        int[][] grid = new int[rows][cols];

        System.out.println("Enter the values of the oranges (0 for empty, 1 for fresh, 2 for rotten):");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }

        int timeTaken = rotOranges(grid);
        if (timeTaken != -1) {
            System.out.println("All oranges have rotted in " + timeTaken + " minutes.");
        } else {
            System.out.println("Not all oranges can rot.");
        }

        scanner.close();
    }

    private static int rotOranges(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0;

        // Initialize the queue with rotten oranges and count fresh oranges
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    freshCount++;
                }
            }
        }

        int time = 0;
        // Process the queue until it's empty or no fresh oranges are left
        while (!queue.isEmpty() && freshCount > 0) {
            int size = queue.size(); // Number of rotten oranges in the current minute
            time++;

            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();

                // Rot adjacent fresh oranges
                for (int[] direction : DIRECTIONS) {
                    int newRow = curr[0] + direction[0];
                    int newCol = curr[1] + direction[1];

                    if (newRow >= 0 && newCol >= 0 && newRow < grid.length && newCol < grid[0].length
                            && grid[newRow][newCol] == 1) {
                        grid[newRow][newCol] = 2; // Rot the fresh orange
                        queue.offer(new int[]{newRow, newCol}); // Add to the queue
                        freshCount--; // Decrement fresh count
                    }
                }
            }
        }

        // Return the time taken or -1 if not all oranges can rot
        return freshCount == 0 ? time : -1;
    }
}
