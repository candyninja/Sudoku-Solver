package Sudoku.GUI.Controllers;

import Sudoku.GUI.Views.PlayView;
import Sudoku.Launch;
import Sudoku.Solver.Methods.Solver;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Set;


/*
 * The SolveController is meant to host all
 * functionality of the SolveView.
 */
public class SolveController {

    private TextField[][] boardField = new TextField[9][9];
    private Solver solver = new Solver();
    private final Stage primaryStage;

    public SolveController(Stage primaryStage){
        this.primaryStage = primaryStage;
    }
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

    public void formatBoard(int i, int j,TextField textField){
        if (j % 3 == 0) {
            GridPane.setMargin(textField, new Insets(0, 10, 0, 0));
        }
        if (i % 3 == 0) {
            GridPane.setMargin(textField, new Insets(0, 0, 10, 0));
        }
    }

    /* Iterates all textFields and resets the text. */
    public void clearBoard() {
        for (TextField[] textRow : boardField) {
            for (TextField textField : textRow) {
                textField.setText("");
                enableTextField(textField);
                textField.getStyleClass().remove("solved");
            }
        }
    }
    public static <T> boolean hasDuplicate(Iterable<T> all) {
        Set<T> set = new HashSet<T>();
        for (T each: all) if (!set.add(each)) return true;
        return false;
    }

    //TODO change textField border color to red if invalid
    //TODO try to style alert dialog
    private void showInputWarning() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setContentText("Check to ensure that the input is a valid sudoku game.");
        alert.showAndWait();
    }

    //TODO verify both rows and columns
    //TODO BUG! It says any 2 numbers are wrong
    private boolean inputCheck() {
            /*Set<String> textColumnSet = new HashSet<>();
            int inputs = 0;
        for (TextField[] textColumn : boardField){
            for (TextField textField : textColumn){
                if(!textField.getText().equals("")) {
                    inputs++;
                    textColumnSet.add(textField.getText());
                }
            }
            if(textColumnSet.size() != inputs ) {return false;}
        }*/
        return true;
    }

    //TODO ensure check for valid input - crashes otherwise!
    //Gets input from the boardField array and solves the sudoku and puts the solution on the boardField
    public void solveSudoku() {
        if (inputCheck()) {
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
                        setSolved(boardField[i][j]);
                    }
                }
            }
        } else {
            showInputWarning();
        }
    }

    public void switchToPlayView() throws NoSuchMethodException {
        Launch launch = new Launch();
        launch.setStage(primaryStage,new PlayView(primaryStage).getScene(),"Play Sudoku");
    }
    /* Used for textFields that are displayed as a solution */
    private void disableTextField(TextField textField) {
        textField.setEditable(false);
    }
    private void enableTextField(TextField textField) {
        textField.setEditable(true);
    }
    private void setSolved(TextField textField){textField.getStyleClass().add("solved");}

}