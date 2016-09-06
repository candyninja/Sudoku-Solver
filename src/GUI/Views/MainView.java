package GUI.Views;
/*
* MainView is the first user-facing UI to be displayed.
* Any View classes should not contain functional code,
* the purpose of View classes are to organize methods
* and elements necessary for displaying user-facing
* elements.
 */

import GUI.Controllers.MainController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainView {
    private MainController controller = new MainController();
    private Stage mainStage = new Stage();


    /**
     * This is the main access point for
     * displayable content, all visible
     * elements should be included in the
     * stage.
     */

    /* TODO: Figure out how to add lines to separate each 3x3 square.  */
    public Stage getStage() throws NoSuchMethodException {
        mainStage.resizableProperty().setValue(false);

        BorderPane root = new BorderPane();
        root.getStyleClass().add("root");

        root.setTop(titleBox());
        root.setCenter(mainContainer());
        root.setBottom(buttons());

        Scene mainScene = new Scene(root, 350, 450, Color.TRANSPARENT);
        mainStage.setScene(mainScene);
        mainScene.getStylesheets().add(this.getClass().getResource("/GUI/Resources/MainView.css").toExternalForm());

        return mainStage;
    }

    private HBox titleBox() {
        HBox titleBox = new HBox();
        HBox.setHgrow(titleBox, Priority.ALWAYS);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setSpacing(10);
        titleBox.setPadding(new Insets(15, 0, 15, 0));

        ImageView logo = new ImageView(new Image("GUI/Resources/cube.png"));
        logo.setFitHeight(30);
        logo.setFitWidth(30);
        titleBox.getChildren().add(logo);

        Text title = new Text("Sudoku Solver");
        title.getStyleClass().add("title");
        title.setFill(Color.ALICEBLUE);

        titleBox.getChildren().add(title);
        return titleBox;
    }

    private HBox mainContainer() {
        HBox mainContainer = new HBox();
        HBox.setHgrow(mainContainer, Priority.ALWAYS);
        mainContainer.setAlignment(Pos.CENTER);

        GridPane grid = new GridPane();
        grid.setHgap(4);
        grid.setVgap(4);
        grid.setPadding(new Insets(5, 5, 5, 5));
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                TextField textField = controller.createTextField();
                controller.formatTextField(textField);
                controller.addBoardField(j - 1, i - 1, textField);
                controller.formatBoard(i,j,textField);
                grid.addRow(i, textField);
            }
        }

        mainContainer.getChildren().add(grid);
        return mainContainer;
    }

    private HBox buttons() throws NoSuchMethodException {
        HBox buttons = new HBox();
        HBox.setHgrow(buttons, Priority.ALWAYS);
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(0, 0, 20, 0));
        buttons.setSpacing(50);

        Button clear = new Button("Clear Board");
        clear.setOnAction((ae) -> controller.clearBoard());
        clear.setPrefHeight(35);
        clear.getStyleClass().add("button");

        Button solve = new Button("Solve");
        solve.setPrefHeight(35);
        solve.getStyleClass().add("button");
        solve.setOnAction((ae) -> controller.solveSudoku());
        buttons.getChildren().add(clear);
        buttons.getChildren().add(solve);
        return buttons;
    }
}