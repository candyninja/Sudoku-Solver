package GUI.Controllers;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.TextField;


/*
 * The MainController is meant to host all
 * functionality of the MainView.
 */
public class MainController {

    private TextField[][] boardField = new TextField[9][9];

    /* So this is probably more work than it was worth. The idea is you can
    * add actions to nodes based on both the action type, and the action to be
    * completed. Currently there's a click action (set on mouseClicked), when
    * calling addAction, write "click", and then create a case for the action that
    * is to occur. The "clear" action calls the clearBoard() method. */
    public void addAction(String actionType, Node actionRecipient, String action) {
        switch (actionType) {
            case "click":
                actionRecipient.setOnMouseClicked(event -> {
                    switch (action) {
                        case "clear":
                            clearBoard();
                            break;
                        default:
                            break;
                    }
                });
                break;
            default:
                break;
        }
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

    /* Used for textFields that are displayed as a solution */
    public void disableTextField(TextField textField) {
        textField.setEditable(false);
    }

}