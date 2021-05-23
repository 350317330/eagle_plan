package cn.walking_dead.transition;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

//平移变换将节点沿着一个轴相对于其初始位置从一个位置移动到另一个位置。木琴条的初始位置由x，y和z坐标定义。
public class TransitionTest1 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Text Fonts");

        Group rectangleGroup = new Group();
        Scene scene = new Scene(rectangleGroup, 550, 250);

        Rectangle rect = new Rectangle();
        rect.setWidth(0);
        rect.setHeight(0);

        Button button = new Button("butot");


        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, // set start position at 0
                new KeyValue(rect.translateXProperty(), 0),
                new KeyValue(rect.translateYProperty(), 0)
        ),new KeyFrame(Duration.seconds(4000), // set start position at 0
                new KeyValue(rect.translateXProperty(), 100),
                new KeyValue(rect.translateYProperty(), 100)
        ));

        timeline.play();

        rectangleGroup.getChildren().add(rect);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
