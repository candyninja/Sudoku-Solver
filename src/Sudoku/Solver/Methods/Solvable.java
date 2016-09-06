package Sudoku.Solver.Methods;

/**
 * This interface will have the required method signatures to solve a sudoku board.
 */
interface Solvable {
    int[][] getSudoku();

    void setSudoku(int[][] sudoku);

    int[][] getSolution();

    boolean checkCandidate(int y, int x, int check, int[][] sudoku);

    void solve();
}
