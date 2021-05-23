package cn.walking_dead.effect;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//内部阴影效果在具有指定颜色，半径和偏移的内容内绘制阴影。
public class DropShadowTestTest extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Text t = new Text();
        t.setX(20.0f);
        t.setY(80.0f);
        t.setText("www.w3cschool.cn");
        t.setFill(Color.RED);
        t.setFont(Font.font("Arial", FontWeight.BOLD, 60));

        InnerShadow is = new InnerShadow();
        is.setOffsetX(2.0f);
        is.setOffsetY(2.0f);

        t.setEffect(is);

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
