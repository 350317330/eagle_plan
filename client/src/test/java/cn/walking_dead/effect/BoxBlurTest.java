package cn.walking_dead.effect;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//模糊效果高斯模糊
public class BoxBlurTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Text Fonts");
        Group root = new Group();
        Scene scene = new Scene(root, 550, 250,Color.web("0x0000FF",1.0));

        Text text = new Text(50, 100, "JavaFX 2.0 from Java2s.com");
        Font sanSerif = Font.font("Dialog", 30);
        text.setFont(sanSerif);
        text.setFill(Color.RED);
        root.getChildren().add(text);

        BoxBlur bb = new BoxBlur();
        bb.setWidth(15);
        bb.setHeight(15);
        bb.setIterations(3);

        text.setEffect(bb);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
