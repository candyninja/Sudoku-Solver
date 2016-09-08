package Sudoku.Solver.Methods;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class SolverTest {

    @org.junit.Test(timeout = 2000)
    public void solve() throws Exception {
        Solver solver = new Solver();
        int[][] sudoku = new int[][]{
                {0, 3, 0, 2, 0, 4, 0, 0, 0},
                {0, 0, 6, 0, 0, 0, 0, 2, 0},
                {4, 2, 0, 0, 5, 0, 0, 0, 0},
                {3, 0, 0, 0, 0, 5, 8, 0, 0},
                {6, 0, 0, 0, 0, 0, 0, 0, 9},
                {0, 0, 1, 8, 0, 0, 0, 0, 5},
                {0, 0, 0, 0, 6, 0, 0, 7, 8},
                {0, 9, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 7, 0, 3, 0, 4, 0}
        };
        int[][] solution = new int[][]{
                {5, 3, 7, 2, 8, 4, 6, 9, 1},
                {1, 8, 6, 9, 3, 7, 5, 2, 4},
                {4, 2, 9, 6, 5, 1, 7, 8, 3},
                {3, 4, 2, 1, 9, 5, 8, 6, 7},
                {6, 5, 8, 3, 7, 2, 4, 1, 9},
                {9, 7, 1, 8, 4, 6, 2, 3, 5},
                {2, 1, 4, 5, 6, 9, 3, 7, 8},
                {7, 9, 3, 4, 2, 8, 1, 5, 6},
                {8, 6, 5, 7, 1, 3, 9, 4, 2}
        };
        solver.setSudoku(sudoku);
        solver.solve();

        assertArrayEquals("Solving Algorithm Failed", solution, solver.getSolution());
    }

    @org.junit.Test(timeout = 1000)
    public void checkCandidate() throws Exception {
        Solver solver = new Solver();
        int[][] sudoku = new int[][]{
                {0, 3, 0, 2, 0, 4, 0, 0, 0},
                {0, 0, 6, 0, 0, 0, 0, 2, 0},
                {4, 2, 0, 0, 5, 0, 0, 0, 0},
                {3, 0, 0, 0, 0, 5, 8, 0, 0},
                {6, 0, 0, 0, 0, 0, 0, 0, 9},
                {0, 0, 1, 8, 0, 0, 0, 0, 5},
                {0, 0, 0, 0, 6, 0, 0, 7, 8},
                {0, 9, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 7, 0, 3, 0, 4, 0}
        };
        solver.setSudoku(sudoku);

        assertTrue("Checking Row Failed", solver.checkCandidate(6, 5, 8));
        assertTrue("Checking Column Failed", solver.checkCandidate(1, 7, 4));
        assertTrue("Checking grid Failed", solver.checkCandidate(4, 0, 1));
    }
}