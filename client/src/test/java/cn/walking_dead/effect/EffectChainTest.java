package cn.walking_dead.effect;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//创建效果链
public class EffectChainTest extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Text t = new Text();
        t.setX(20.0f);
        t.setY(80.0f);
        t.setText("www.w3cschool.cn");
        t.setFill(Color.RED);
        t.setFont(Font.font("Arial", FontWeight.BOLD, 60));

        DropShadow ds = new DropShadow();
        ds.setOffsetY(5.0);
        ds.setOffsetX(5.0);
        ds.setColor(Color.GRAY);

        Reflection reflection = new Reflection();

        ds.setInput(reflection);

        t.setEffect(ds);

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
