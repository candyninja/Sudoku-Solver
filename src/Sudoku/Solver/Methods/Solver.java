package Sudoku.Solver.Methods;

/**
 * All the code that solves a sudoku board.
 */
//TODO try to minimize code -- make more simple, modular, etc.
public class Solver implements Solvable {
    private int[][] sudoku = new int[9][9];
    private int[][] solution = new int[9][9];

    public int[][] getSudoku() {
        return sudoku;
    }

    public void setSudoku(int[][] sudoku) {
        this.sudoku = sudoku;
    }

    public int[][] getSolution() {
        return solution;
    }

    // x is row number, y is column number. Returns true if check is a possible answer at that location
    public boolean checkCandidate(int x, int y, int check, int[][] sudoku) {
        //Checks if any duplicates are present in its row
        for (int k = 0; k < 9; k++) {
            if (k != y) {
                if (sudoku[x][k] == check)
                    return false;
            }
        }

        //Checks if any duplicates are present in its column
        for (int k = 0; k < 9; k++) {
            if (k != x) {
                if (sudoku[k][y] == check)
                    return false;
            }
        }

        //Checks if any duplicates are present in its 3x3 grid
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                if (x > (i - 1) && x < (i + 3) && y > (j - 1) && y < (j + 3)) {
                    for (int k = i; k < (i + 3); k++) {
                        for (int l = j; l < (j + 3); l++) {
                            if (sudoku[k][l] == check)
                                return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public void solve() {
        boolean gotCandidate;

        //Copies Values of sudoku into solution
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                solution[i][j] = sudoku[i][j];
            }
        }

        //Iterates over all grid locations
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                gotCandidate = false;

                //finds possible candidates
                if (sudoku[i][j] == 0) {
                    if (solution[i][j] == 0) {
                        for (int k = 1; k < 10 && !gotCandidate; k++) {
                            if (checkCandidate(i, j, k, solution)) {
                                solution[i][j] = k;
                                gotCandidate = true;
                            }
                        }
                    } else {
                        for (int k = (solution[i][j] + 1); k < 10 && !gotCandidate; k++) {
                            if (checkCandidate(i, j, k, solution)) {
                                solution[i][j] = k;
                                gotCandidate = true;
                            }
                        }
                    }

                    // sets iteration to previous editable cell if no candidates are possible
                    if (!gotCandidate) {
                        solution[i][j] = 0;
                        if (j != 0) {
                            j--;
                        } else {
                            if (i != 0) {
                                i--;
                            }
                            j = 8;
                        }
                        while (sudoku[i][j] != 0) {
                            if (j == 0) {
                                if (i != 0) {
                                    i--;
                                }
                                j = 8;
                            } else {
                                j--;
                            }
                        }
                        if (j != 0) {
                            j--;
                        } else {
                            i--;
                            j = 8;
                        }
                    }
                }
            }
        }
    }
}
