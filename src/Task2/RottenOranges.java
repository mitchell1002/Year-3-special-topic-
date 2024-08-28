package Task2;

import java.util.Scanner;

class Queue {
    Node head, tail;
    int size;

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            next = null;
        }
    }

    public Queue() {
        this.tail = null;
        this.head = null;
        this.size = 0; // Initialize size to 0
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void enqueue(int item) {
        Node node = new Node(item);
        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++; // Increment size when enqueueing
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return Integer.MIN_VALUE; // Return a sentinel value if the queue is empty
        }
        int item = head.data;
        if (head != tail) {
            head = head.next;
        } else {
            head = null; // If there was only one node, set head and tail to null
            tail = null;
        }
        size--; // Decrement size when dequeueing
        return item;
    }

    public int getSize() {
        return size; // Return the current size of the queue
    }
}

class RottenOranges {
    public static void userInput(Scanner scanner, int[][] grid) {
        System.out.println("Please enter the values of the oranges: ");
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = scanner.nextInt(); // Read the integers from the user and fills in the grid
            }
        } // On this method, I am getting user input for the rows and columns they would like
        // to place their oranges in, i and j representing the grid height and width
        // using scanner is reading the integers from the user and fills in the grid
    }

    private static int readGrid(int[][] grid) {
        Queue queue = new Queue(); // Create an instance of the Queue
        int fresh = 0; // This keeps track of the oranges the user puts
        // I am using queue because it will store the position of each orange and if the fresh one gets rotten see if the next orange gets rotten

        // Enqueue rotten oranges and count fresh oranges
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) { // Enqueue
                if (grid[i][j] == 2) {
                    queue.enqueue(i * grid[0].length + j); // Encode coordinates into a single integer
                }
                if (grid[i][j] == 1) fresh++;
            }
        }

        int time = 0;

        // Process rotten oranges until none are left or all are rotten
        while (!queue.isEmpty() && fresh > 0) {
            int size = queue.getSize(); // Get the current size of the queue
            // Time++; // Keeping track of the seconds or minutes that have passed by
            // System.out.println("Time: " + time);
            // This is the while loop that keeps track of how many oranges have rotted and how long did it take to rot them
            // until they are no more to rot

            while (size-- > 0) { // Runs how many oranges are rotten. Size is counting down how many rotten oranges in the time span
                int curr = queue.dequeue(); // Get the current rotten orange
                int x = curr / grid[0].length; // Decode coordinates
                int y = curr % grid[0].length;

                int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Directions
                for (int[] d : dirs) {
                    int newX = x + d[0], newY = y + d[1];
                    if (newX >= 0 && newY >= 0 && newX < grid.length && newY < grid[0].length && grid[newX][newY] == 1) {
                        grid[newX][newY] = 2; // Mark the orange as rotten
                        queue.enqueue(newX * grid[0].length + newY); // Enqueue the new rotten orange
                        fresh--;
                        System.out.println("Orange at (" + newX + ", " + newY + ") got rotten.");
                    }
                }
            }
            time++; // Increment time after processing all rotten oranges in this round
        }

        return fresh == 0 ? time : -1; // This is checking if the fresh oranges are rotten or not. With ? starts another else statement
        // and the total time it took to rot oranges
    }

    private static void showFreshOranges(int[][] grid) {
        System.out.println("Fresh oranges remaining:");
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    System.out.println("(" + i + ", " + j + ")");
                }
            }
        } // This loops checks if we still have isolated 1 which is fresh oranges and then prints out
    }

    private static void displayResults(int time, int[][] grid) {
        if (time != -1) {
            System.out.println("All oranges have rotted in: " + time + " seconds.");
        } else {
            System.out.println("Fresh oranges remaining at:");
            System.out.println("Not all oranges can rot.");
            showFreshOranges(grid);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Enter the number of columns: ");
        int cols = scanner.nextInt();

        int[][] grid = new int[rows][cols];

        userInput(scanner, grid);

        int time = readGrid(grid);

        displayResults(time, grid);

        scanner.close();
    }
}
