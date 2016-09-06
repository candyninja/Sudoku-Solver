package Solver.Methods;

import java.io.*;

/**
 * Code for testing algorithms
 */
public class TestSolver {

    private static void writeSudoku(int[][] sudoku, String input) {
        // i is for row number, j is for column number
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Solution.txt", true));
            writer.write("\n");
            writer.write(input + "\n");
            for (int i = 0; i < 9; i++) {
                if (i % 3 == 0 && i != 0)
                    writer.write("---------------------- \n");
                for (int j = 0; j < 9; j++) {
                    if (j % 3 == 0 && j != 0)
                        writer.write("| ");
                    writer.write(sudoku[i][j] + " ");
                }
                writer.write("\n");
            }
            writer.close();
        } catch (IOException ex) {
            System.out.println("Error while trying to read File");
        }
    }

    private void getSudoku(String fileName, int[][] sudoku) {
        String input;
        String[] token;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            for (int j = 0; j < 9; j++) {
                input = reader.readLine();
                token = input.split(" ");
                for (int k = 0; k < 9; k++) {
                    sudoku[j][k] = Integer.parseInt(token[k]);
                }
            }
        } catch (IOException ex) {
            System.out.println("Error: Could not find Required File");
        }
    }

    private boolean checkSolution(String fileName, int[][] solution) {
        boolean passed = true;
        try {
            int[][] answer = new int[9][9];
            String input;
            String[] token;
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            reader.skip(171);
            for (int j = 0; j < 9; j++) {
                input = reader.readLine();
                token = input.split(" ");
                for (int k = 0; k < 9; k++) {
                    answer[j][k] = Integer.parseInt(token[k]);
                }
            }

            for (int i = 0; i < 9 && passed; i++) {
                for (int j = 0; j < 9 && passed; j++) {
                    if (answer[i][j] != solution[i][j]) {
                        passed = false;
                    }
                }
            }

            return passed;
        } catch (IOException ex) {
            System.out.println("Error while trying to read File");
            return false;
        }

    }

    public void doTest() {
        Solver solver = new Solver();
        int[][] sudoku = new int[9][9];

        System.out.println("Test Started");
        getSudoku("Sudoku.txt", sudoku);
        solver.setSudoku(sudoku);
        writeSudoku(solver.getSudoku(), "Sudoku");
        solver.solve();

        if (checkSolution("Sudoku.txt", solver.getSolution())) {
            System.out.println("Test Passed");
        } else {
            System.out.println("Test Failed");
        }
        writeSudoku(solver.getSolution(), "Solution");
    }
}
