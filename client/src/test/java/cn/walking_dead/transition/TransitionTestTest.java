package cn.walking_dead.transition;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
//平移变换将节点沿着一个轴相对于其初始位置从一个位置移动到另一个位置。木琴条的初始位置由x，y和z坐标定义。
public class TransitionTestTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Text Fonts");

        Group rectangleGroup = new Group();
        Scene scene = new Scene(rectangleGroup, 550, 250);

        Rectangle rect = new Rectangle();
        rect.setWidth(100);
        rect.setHeight(100);
        rect.setTranslateX( 135);
        rect.setTranslateY(11.0);

        rectangleGroup.getChildren().add(rect);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
