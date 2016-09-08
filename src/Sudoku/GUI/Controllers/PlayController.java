package Sudoku.GUI.Controllers;

import Sudoku.GUI.Views.SolveView;
import Sudoku.Launch;
import javafx.stage.Stage;

/*
 * The SolveController is meant to host all
 * functionality of the SolveView.
 */
public class PlayController extends MainController {

    public PlayController(Stage primaryStage) {
        super(primaryStage);
    }

    public void switchToSolveView() throws NoSuchMethodException {
        Launch launch = new Launch();
        launch.setStage(primaryStage, new SolveView(primaryStage).getScene(), "Solve Sudoku");
    }


}