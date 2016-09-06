import GUI.Views.MainView;
import Solver.Methods.TestSolver;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/*
* This class does should not contain any methods or functionality.
* This will only call the JavaFX Application to show.
* */
public class Launch extends Application {
    public static void main(String[] args) {
        TestSolver test = new TestSolver();
        test.doTest();
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage = new MainView().getStage();
        primaryStage.setTitle("Sudoku Solver");
        primaryStage.getIcons().add(new Image("GUI/Resources/cube.png"));
        primaryStage.show();
    }
}
