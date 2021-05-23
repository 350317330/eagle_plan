package cn.walking_dead.effect;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//反射效果将对象的反射版本渲染到实际对象下面。
public class ReflectionTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Text Fonts");
        Group root = new Group();
        Scene scene = new Scene(root, 550, 250, Color.WHITE);

        Text text = new Text(50, 50, "JavaFX 2.0 from Java2s.com");
        Font monoFont = Font.font("Dialog", 30);
        text.setFont(monoFont);
        text.setFill(Color.BLACK);
        root.getChildren().add(text);

        Reflection refl = new Reflection();
        refl.setFraction(0.8f);
        text.setEffect(refl);

        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
