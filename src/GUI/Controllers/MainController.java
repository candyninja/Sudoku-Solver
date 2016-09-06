package GUI.Controllers;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;

/*
 * The MainController is meant to host all
 * functionality of the MainView.
 */
public class MainController {

    public TextField createTextField(){
        TextField textField = new TextField();
        limitInput(textField,1);
        return textField;
    }

    private void limitInput( TextField textField, int maxLength) {
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
    /* Overload for multiple textField creations. */
    public TextField[] createTextField(int number){
        TextField[] textFieldArray = new TextField[number];
        for ( int i = 0; i < number ; i++ ) {
            textFieldArray[i] = createTextField();
        }
        return textFieldArray;
    }

    /* Styles textFields to fit a specific format. Useful for generating a large amount of identical nodes. */
    public void formatTextField(TextField textField){
        textField.setMaxSize(25,10);
        textField.setPadding(new Insets(5.5,15,5,5));
        textField.getStyleClass().add("textField");
    }
    /* TODO: Implement method to substring every input to 1 character before solving. Listeners may be too resource heavy? */

    /* Used for textFields that are displayed as a solution */
    public void disableTextField(TextField textField){
        textField.setEditable(false);
    }

}