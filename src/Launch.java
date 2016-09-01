import GUI.Views.MainView;
import javafx.application.Application;
import javafx.stage.Stage;

/*
* This class does should not contain any methods or functionality.
* This will only call the JavaFX Application to show.
* */
public class Launch extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage = new MainView().getStage();
        primaryStage.setTitle("Sudoku Solver");
        /*TODO: Set window icon*/
        primaryStage.show();
    }
}
