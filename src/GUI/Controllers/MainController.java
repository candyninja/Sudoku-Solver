package GUI.Controllers;

import Solver.Methods.Solver;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;


/*
 * The MainController is meant to host all
 * functionality of the MainView.
 */
public class MainController {

    private TextField[][] boardField = new TextField[9][9];
    private Solver solver = new Solver();

    /* Automatically adds TextFields to the board when they're created. They should be
    * dynamic and used with the getText method. */
    public void addBoardField(int pos1, int pos2, TextField textField) {
        boardField[pos1][pos2] = textField;
    }

    /* Ensures each TextField only contains 1 character, and that is is a number. */
    private void limitInput(TextField textField, int maxLength) {
        textField.textProperty().addListener((ov, oldValue, newValue) -> {
            if (textField.getText().length() > maxLength) {
                String s = textField.getText().substring(0, maxLength);
                textField.setText(s);
            }
            try {
                Integer.parseInt(textField.getText());
            } catch (NumberFormatException | NullPointerException e) {
                textField.setText("");
            }
        });
    }

    /* Creates a TextField for the board. */
    public TextField createTextField() {
        TextField textField = new TextField();
        limitInput(textField, 1);
        return textField;
    }

    /* Styles textFields to fit a specific format. Useful for generating a large amount of identical nodes. */
    public void formatTextField(TextField textField) {
        textField.setMaxSize(25, 10);
        textField.setMinWidth(30);
        textField.setAlignment(Pos.CENTER);
        textField.setPadding(new Insets(5.5, 15, 5, 5));
        textField.getStyleClass().add("textField");
    }

    /* Iterates all textFields and resets the text. */
    public void clearBoard() {
        for (TextField[] textRow : boardField) {
            for (TextField textField : textRow) {
                textField.setText("");
            }
        }
    }

    //Gets input from the boardField array and solves the sudoku and puts the solution on the boardField
    public void solveSudoku() {
        int[][] sudokuInput = new int[9][9];
        int[][] solutionOutput;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (boardField[i][j].getText().length() == 0) {
                    sudokuInput[i][j] = 0;
                } else {
                    sudokuInput[i][j] = Integer.parseInt(boardField[i][j].getText());
                }
            }
        }

        solver.setSudoku(sudokuInput);
        solver.solve();
        solutionOutput = solver.getSolution();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (boardField[i][j].getText().length() == 0) {
                    boardField[i][j].setText("" + solutionOutput[i][j]);
                    disableTextField(boardField[i][j]);
                }
            }
        }

    }

    /* Used for textFields that are displayed as a solution */
    private void disableTextField(TextField textField) {
        textField.setEditable(false);
    }

}