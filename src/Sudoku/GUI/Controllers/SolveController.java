package Sudoku.GUI.Controllers;

import Sudoku.GUI.Views.PlayView;
import Sudoku.Launch;
import javafx.stage.Stage;

/*
 * The SolveController is meant to host all
 * functionality of the SolveView.
 */
public class SolveController extends MainController {

    public SolveController(Stage primaryStage) {
        super(primaryStage);
    }

    public void switchToPlayView() throws NoSuchMethodException {
        Launch launch = new Launch();
        launch.setStage(primaryStage, new PlayView(primaryStage).getScene(), "Play Sudoku");
    }


}