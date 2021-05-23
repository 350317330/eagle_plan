package cn.walking_dead.effect;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.MotionBlur;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//使用运动模糊，我们可以配置半径和角度来创建移动对象的效果
public class MotionBlurTest extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Text t = new Text();
        t.setX(20.0f);
        t.setY(80.0f);
        t.setText("Motion Blur");
        t.setFill(Color.RED);
        t.setFont(Font.font("Arial", FontWeight.BOLD, 60));

        MotionBlur mb = new MotionBlur();
        mb.setRadius(15.0f);
        mb.setAngle(45.0f);

        t.setEffect(mb);

        t.setTranslateX(10);
        t.setTranslateY(150);

        HBox box = new HBox();
        box.getChildren().add(t);

        Scene scene = new Scene(box, 400, 450);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
