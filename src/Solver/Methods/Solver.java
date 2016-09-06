package Solver.Methods;
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
        boolean possible = true;
        for (int k = 0; k < 9; k++) {
            if (k != y) {
                if (sudoku[x][k] == check) {
                    possible = false;
                }
            }
        }

        for (int k = 0; k < 9; k++) {
            if (k != x) {
                if (sudoku[k][y] == check) {
                    possible = false;
                }
            }
        }

        if (x < 3 && y < 3) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (sudoku[i][j] == check)
                        possible = false;
                }
            }

        } else if (x < 6 && y < 3) {
            for (int i = 3; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    if (sudoku[i][j] == check) {
                        possible = false;
                    }
                }
            }

        } else if (x < 9 && y < 3) {
            for (int i = 6; i < 9; i++) {
                for (int j = 0; j < 3; j++) {
                    if (sudoku[i][j] == check) {
                        possible = false;
                    }
                }
            }

        } else if (x < 3 && y < 6) {
            for (int i = 0; i < 3; i++) {
                for (int j = 3; j < 6; j++) {
                    if (sudoku[i][j] == check) {
                        possible = false;
                    }
                }
            }

        } else if (x < 6 && y < 6) {
            for (int i = 3; i < 6; i++) {
                for (int j = 3; j < 6; j++) {
                    if (sudoku[i][j] == check) {
                        possible = false;
                    }
                }
            }

        } else if (x < 9 && y < 6) {
            for (int i = 6; i < 9; i++) {
                for (int j = 3; j < 6; j++) {
                    if (sudoku[i][j] == check) {
                        possible = false;
                    }
                }
            }

        } else if (x < 3 && y < 9) {
            for (int i = 0; i < 3; i++) {
                for (int j = 6; j < 9; j++) {
                    if (sudoku[i][j] == check) {
                        possible = false;
                    }
                }
            }

        } else if (x < 6 && y < 9) {
            for (int i = 3; i < 6; i++) {
                for (int j = 6; j < 9; j++) {
                    if (sudoku[i][j] == check) {
                        possible = false;
                    }
                }
            }

        } else {
            for (int i = 6; i < 9; i++) {
                for (int j = 6; j < 9; j++) {
                    if (sudoku[i][j] == check) {
                        possible = false;
                    }
                }
            }
        }
        return possible;
    }

    public void solve() {
        boolean gotCandidate;

        //Copies Values of sudoku into solution
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                solution[i][j] = sudoku[i][j];
            }
        }

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

                    // sets iteration to previous cell if no candidates are possible
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

                        //If previous cell is a part of the original sudoku puzzle then sets iteration to previous cell again
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

                        //sets iteration to previous cell again since for loop will set it to next cell.
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
