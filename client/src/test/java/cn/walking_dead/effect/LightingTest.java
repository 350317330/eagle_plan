package cn.walking_dead.effect;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//照明效果产生照射给定内容的光源。它可以给平面物体更逼真的三维外观。
public class LightingTest extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Group rootGroup = new Group();
        Scene scene =new Scene(rootGroup, 800, 400);

        Text text1 = new Text(25, 25, "www.w3cschool.cn");
        text1.setFill(Color.CHOCOLATE);
        //text1.setFont(Font.font(Font.MONOSPACED, 35));

        final Light.Distant light = new Light.Distant();
        light.setAzimuth(-135.0);
        final Lighting lighting = new Lighting();
        lighting.setLight(light);
        lighting.setSurfaceScale(9.0);
        text1.setEffect(lighting);



        rootGroup.getChildren().add(text1);

        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
