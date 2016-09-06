package Sudoku;

import Sudoku.GUI.Views.MainView;
import Sudoku.Solver.Methods.TestSolver;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/*
* This class does should not contain any methods or functionality.
* This will only call the JavaFX Application to show.
* */
//TODO fix bug where stage change makes new window
public class Launch extends Application {
    Stage oldStage = null;
    public static void main(String[] args) {
        TestSolver testSudoku = new TestSolver();
        testSudoku.doTest();
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        setStage(new MainView().getStage(), "Solve Sudoku");
    }

    public void setStage(Stage newStage, String title) {
        if(oldStage == null){
            oldStage = newStage;
            setStage(newStage,title);
        } else {
            oldStage.hide();
            newStage.setTitle(title);
            newStage.getIcons().add(new Image("Sudoku/GUI/Resources/cube.png"));
            newStage.show();
        }
    }
}
