package cn.walking_dead.effect;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//发光的文本
public class GlowTest extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Group rootGroup = new Group();
        Scene scene =new Scene(rootGroup, 800, 400);

        Text text1 = new Text(25, 25, "www.w3cschool.cn");
        text1.setFill(Color.CHOCOLATE);
        //text1.setFont(Font.font(java.awt.Font.MONOSPACED, 35));

        Effect glow = new Glow(1.0);
        text1.setEffect(glow);

        rootGroup.getChildren().add(text1);

        stage.setScene(scene);
        stage.show();




    }

    public static void main(String[] args) {
        launch(args);
    }
}
