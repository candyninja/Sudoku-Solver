package Solver.Methods;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Code for testing algorithms
 */
public class TestSolver {

    private void getSudoku(String fileName, int[][] sudoku) {
        String input;
        String[] token;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader);

            for (int j = 0; j < 9; j++) {
                input = reader.readLine();
                token = input.split(" ");
                for (int k = 0; k < 9; k++) {
                    sudoku[j][k] = Integer.parseInt(token[k]);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void displaySudoku(int[][] sudoku) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudoku[i][j] == 0) {
                    System.out.print("_ ");
                } else {
                    System.out.print(sudoku[i][j] + " ");
                }
            }
            System.out.println("");
        }
    }

    public void testSudoku() {
        int[][] sudoku = new int[9][9];
        this.getSudoku("Sudoku.txt", sudoku);
        this.displaySudoku(sudoku);
    }
}
