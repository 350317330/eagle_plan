package cn.walking_dead.effect;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
//阴影效果呈现内容的阴影。我们可以配置阴影的颜色，半径，偏移和其他参数。
public class DropShadowTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Text Fonts");
        Group root = new Group();
        Scene scene = new Scene(root, 550, 250, Color.WHITE);

        Text text = new Text(150, 50, "JavaFX from Java2s.com");
        text.setFill(Color.BLUE);
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(2.0f);
        dropShadow.setOffsetY(4.0f);
        dropShadow.setColor(Color.rgb(150, 50, 50, .688));
        text.setEffect(dropShadow);

        root.getChildren().add(text);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
