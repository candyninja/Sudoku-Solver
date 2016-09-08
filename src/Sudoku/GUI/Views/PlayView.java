package Sudoku.GUI.Views;

import Sudoku.GUI.Controllers.PlayController;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class PlayView {
    private Stage primaryStage;
    private PlayController controller;

    public PlayView(Stage primaryStage) {
        this.primaryStage = primaryStage;
        controller = new PlayController(primaryStage);
    }

    /**
     * This is the main access point for
     * displayable content, all visible
     * elements should be included in the
     * stage.
     */

    public Scene getScene() throws NoSuchMethodException {
        primaryStage.resizableProperty().setValue(false);
        BorderPane root = new BorderPane();
        root.getStyleClass().add("root");
        root.setTop(titleBox());
        root.setCenter(mainContainer());
        root.setBottom(buttons());
        Scene mainScene = new Scene(root, 350, 450, Color.TRANSPARENT);
        mainScene.getStylesheets().add(this.getClass().getResource("/Sudoku/GUI/Resources/MainView.css").toExternalForm());
        return mainScene;
    }

    //TODO fix the shift to the right from Solve View
    private HBox titleBox() {
        HBox titleBox = new HBox();
        HBox.setHgrow(titleBox, Priority.NEVER);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setSpacing(10);
        titleBox.setPadding(new Insets(15, 0, 15, 0));

        ImageView logo = new ImageView(new Image("Sudoku/GUI/Resources/cube.png"));
        logo.setFitHeight(30);
        logo.setFitWidth(30);
        titleBox.getChildren().add(logo);

        Text title = new Text("Sudoku Player");
        title.getStyleClass().add("title");
        title.setFill(Color.ALICEBLUE);
        title.setTextAlignment(TextAlignment.RIGHT);

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
        grid.setPadding(new Insets(5, 5, 0, 5));
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                TextField textField = controller.createTextField();
                controller.formatTextField(textField);
                controller.addBoardField(j - 1, i - 1, textField);
                controller.formatBoard(i, j, textField);
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
        buttons.setPadding(new Insets(10, 0, 10, 0));
        buttons.setSpacing(25);

        Button clear = new Button("New Game");
        clear.setOnAction((ae) -> controller.clearBoard());
        clear.setPrefHeight(35);
        clear.getStyleClass().add("button");

        Button solve = new Button("Check");
        solve.setPrefHeight(35);
        solve.getStyleClass().add("button");
        solve.setOnAction((ae) -> controller.solveSudoku());

        Button play = new Button("Solve Sudoku");
        play.setPrefHeight(35);
        play.setMinWidth(100);
        play.getStyleClass().add("button");
        play.setOnAction((ae) -> {
            try {
                controller.switchToSolveView();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        });

        buttons.getChildren().add(clear);
        buttons.getChildren().add(solve);
        buttons.getChildren().add(play);
        return buttons;
    }
}