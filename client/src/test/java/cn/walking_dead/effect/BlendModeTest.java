package cn.walking_dead.effect;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class BlendModeTest extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Rectangle r = new Rectangle();
        r.setX(590);
        r.setY(50);
        r.setWidth(50);
        r.setHeight(50);
        r.setFill(Color.BLUE);

        Circle c = new Circle();
        c.setFill(Color.RED);
        c.setCenterX(590);
        c.setCenterY(50);
        c.setRadius(25);
        c.setBlendMode(BlendMode.SRC_ATOP);

        Group g = new Group();
        g.setBlendMode(BlendMode.SRC_OVER);
        g.getChildren().add(r);
        g.getChildren().add(c);

        HBox box = new HBox();
        box.getChildren().add(g);

        Scene scene = new Scene(box, 400, 450);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
