package Task2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class RottenOranges {

    public static void userInput(Scanner scanner, int[][] grid) {
        System.out.println("Please enter  the values of the oranges ");
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }//on this method 1 am getting user input for the rows and columns they would like
        //to place their oranges in, i and j representing the grid height and width
        // using scanner is reading the integers from the user and fills in the grid
    }

    private static int ReadGrid(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        int fresh = 0;// this keeps track of the oranges the user puts
        // l am using queue because it will store the position of each orange and if the fresh one gets rotten see if the next orange gets rotten


        for (int i = 0; i < grid.length; i++) { // this loops around the grid to detect the fresh ones
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) queue.offer(new int[]{i, j});    // Initialize the queue with rotten oranges and count fresh ones
                if (grid[i][j] == 1) fresh++;
            }
        }



    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};// this for the four possible directions

    int time = 0;
        while (!queue.isEmpty() && fresh > 0) {
        int size = queue.size();
        //time++;// keeping the track of the secons or minutes that have passed by
        //System.out.println("Time: " + time);
            // this is the while loop that keeps track of how many oranges have rot and how long did it take ti rot them
            // until they are no more to rot
        showFreshOranges(grid);

        while (size-- > 0) {// runs how many oranges are rotten  size is counting down how many rotten oranges in the time span
            int[] curr = queue.poll();
            for (int[] d : dirs) {
                int x = curr[0] + d[0], y = curr[1] + d[1];// this is for directions adding the next cells
                if (x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] == 1) {// this checks if  the grid is not out of dimensions
                    grid[x][y] = 2;//fresh orange gets rotten
                    queue.offer(new int[]{x, y}); // this adds to the queue structure so that they can begin with next cells
                    fresh--;
                    System.out.println("Orange at (" + x + ", " + y + ") got rotten.");
                }
            }
        }
    }

        return fresh == 0 ? time : -1;
        // this is checking if the fresh oranges are rotten or not with ?starts another else statement
        //and the total time it took to rot oranges
}

    private static void showFreshOranges(int[][] grid) {
        System.out.println("Fresh oranges remaining:");
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    System.out.println("(" + i + ", " + j + ")");
                }
            }
        }// this loops checks if we still have isolated 1 which is fresh oranges and then prints out
    }




    private static void displayResults(int time, int[][] grid) {
        if (time != -1) {
            int seconds = time % 60;
            System.out.println("All oranges have rotted in : " + seconds+ "seconds ");
        } else {
            System.out.println("Fresh oranges remaining at:");
            System.out.println("Not all oranges can rot.");

            showFreshOranges(grid);
        }// this one l know
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Enter the number of columns: ");
        int cols = scanner.nextInt();

        int[][] grid = new int[rows][cols];

        userInput(scanner, grid);// calling the method

        int time = ReadGrid(grid);//calling the function

        displayResults(time, grid);//calling method

        scanner.close();// this avoids the resource leaks
    }

}
