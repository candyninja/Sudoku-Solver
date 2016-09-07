package Sudoku;

import Sudoku.GUI.Views.SolveView;
import Sudoku.Solver.Methods.TestSolver;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/*
* This class does should not contain any methods or functionality.
* This will only call the JavaFX Application to show.
* */

public class Launch extends Application {
    public static void main(String[] args) {
        TestSolver testSudoku = new TestSolver();
        testSudoku.doTest();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        setStage(primaryStage,new SolveView(primaryStage).getScene(), "Solve Sudoku");
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    public void setStage(final Stage primaryStage, Scene newScene, String title) {
            primaryStage.setTitle(title);
            primaryStage.getIcons().add(new Image("Sudoku/GUI/Resources/cube.png"));
            primaryStage.setScene(newScene);
    }
}
