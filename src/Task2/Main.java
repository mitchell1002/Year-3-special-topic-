import java.util.Scanner;

import static Task2.RottenOranges.*;

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