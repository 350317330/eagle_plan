package cn.walking_dead.effect;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
//模糊效果高斯模糊
public class GaussianBlurTest extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250, Color.WHITE);

        Group g = new Group();

        Text t = new Text();
        t.setX(10.0);
        t.setY(40.0);
        t.setCache(true);
        t.setText("Blurry Text");
        t.setFill(Color.RED);
        t.setFont(Font.font(null, FontWeight.BOLD, 36));

        t.setEffect(new GaussianBlur());

        g.getChildren().add(t);

        root.getChildren().add(g);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
