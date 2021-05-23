package cn.walking_dead.transition;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

//缩放。
public class ScaleTestTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Text Fonts");

        Group rectangleGroup = new Group();
        Scene scene = new Scene(rectangleGroup, 550, 250);

        Rectangle rect = new Rectangle();
        rect.setWidth(100);
        rect.setHeight(100);

        rect.setScaleY(2);
        rectangleGroup.getChildren().add(rect);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
