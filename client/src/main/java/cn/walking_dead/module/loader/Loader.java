package cn.walking_dead.module.loader;

import com.jfoenix.controls.JFXProgressBar;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Loader extends Preloader {

    private JFXProgressBar progressBar;
    private Parent view;
    private Stage stage;

    @Override
    public void init(){
        try {
            view = FXMLLoader.load(getClass().getResource("/cn/walking_dead/module/loader/loader.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage=stage;
        stage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(view);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add(getClass().getResource("/css/fonts.css").toExternalForm());
        progressBar = (JFXProgressBar) scene.lookup("#progressBar");
        stage.getIcons().add(new Image(getClass().getResource("/icon/icon.png").toExternalForm()));
        stage.setScene(scene);
//        primary.setAlwaysOnTop(true);
        stage.show();
    }

    @Override
    public synchronized void handleApplicationNotification(Preloader.PreloaderNotification info) {
        // Handle application notification in this point (see MyApplication#Init).

        if (info instanceof Preloader.ProgressNotification) {
            double x = ((Preloader.ProgressNotification) info).getProgress();

            double percent = x / 100f;
            progressBar.progressProperty().set(percent > 1 ? 1 : percent);
        }
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification info) {
        // Handle state change notifications.

        StateChangeNotification.Type type = info.getType();
        switch (type) {
            case BEFORE_LOAD:
                break;
            case BEFORE_INIT:
                break;
            case BEFORE_START:
                stage.close();
        }
    }
}
