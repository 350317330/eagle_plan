package cn.walking_dead.transition;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FadeTransitionTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Group group = new Group();

        Rectangle rect = new Rectangle(20,20,200,200);

        FadeTransition ft = new FadeTransition(Duration.millis(5000), rect);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.play();

        group.getChildren().add(rect);

        Scene scene = new Scene(group, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
       launch(args);
    }
}
