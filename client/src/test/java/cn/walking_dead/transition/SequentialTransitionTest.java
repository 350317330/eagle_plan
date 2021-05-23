package cn.walking_dead.transition;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SequentialTransitionTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Group group = new Group();

        Rectangle rectSeq = new Rectangle(20, 20, 200, 200);

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000),
                rectSeq);
        fadeTransition.setFromValue(1.0f);
        fadeTransition.setToValue(0.3f);
        fadeTransition.setCycleCount(1);
        fadeTransition.setAutoReverse(true);

        TranslateTransition translateTransition = new TranslateTransition(
                Duration.millis(2000), rectSeq);
        translateTransition.setFromX(50);
        translateTransition.setToX(375);
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(true);

        RotateTransition rotateTransition = new RotateTransition(
                Duration.millis(2000), rectSeq);
        rotateTransition.setByAngle(180f);
        rotateTransition.setCycleCount(4);
        rotateTransition.setAutoReverse(true);

        ScaleTransition scaleTransition = new ScaleTransition(
                Duration.millis(2000), rectSeq);
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setToX(2);
        scaleTransition.setToY(2);
        scaleTransition.setCycleCount(1);
        scaleTransition.setAutoReverse(true);

        SequentialTransition sequentialTransition = new SequentialTransition();
        sequentialTransition.getChildren().addAll(fadeTransition,
                translateTransition, rotateTransition, scaleTransition);
        sequentialTransition.setCycleCount(Timeline.INDEFINITE);
        sequentialTransition.setAutoReverse(true);

        sequentialTransition.play();

        group.getChildren().add(rectSeq);

        Scene scene = new Scene(group, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
       launch(args);
    }
}
