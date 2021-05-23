package cn.walking_dead;


import cn.walking_dead.module.loader.Loader;
import cn.walking_dead.plugin.ViewManager;
import com.gn.decorator.GNDecorator;
import com.gn.decorator.options.ButtonType;
import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.application.Preloader;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {
    private float  increment = 0;
    private float  progress = 0;

    @Override
    public synchronized void init(){
        float total = 45; // the difference represents the views not loaded
        increment = 100f / total;

        load("login","login");
        //load("main");

        // delay
        try {
            wait(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        LauncherImpl.launchApplication(Main.class, Loader.class,args);

    }

    private void load(String module,String name){
        try {
            ViewManager.getInstance().put(
                    name,
                    FXMLLoader.load(getClass().getResource("/cn/walking_dead/module/" + module + "/" + name + ".fxml"))
            );
            preloaderNotify();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private synchronized void preloaderNotify() {
        progress += increment;
        LauncherImpl.notifyPreloader(this,new Preloader.ProgressNotification(progress));
    }

    public static final GNDecorator decorator = new GNDecorator();
    public static final Scene scene = decorator.getScene();

    public static ObservableList<String> stylesheets;
    public static HostServices hostServices;

    @Override
    public void start(Stage primaryStage) throws Exception {
        initialScene();
        stylesheets = decorator.getScene().getStylesheets();

        stylesheets.addAll(
                getClass().getResource("/css/fonts.css").toExternalForm(),
                getClass().getResource("/css/material-color.css").toExternalForm(),
                getClass().getResource("/css/skeleton.css").toExternalForm(),
                getClass().getResource("/css/light.css").toExternalForm(),
                getClass().getResource("/css/bootstrap.css").toExternalForm(),
                getClass().getResource("/css/shape.css").toExternalForm(),
                getClass().getResource("/css/typographic.css").toExternalForm(),
                getClass().getResource("/css/helpers.css").toExternalForm(),
                getClass().getResource("/css/master.css").toExternalForm()
        );

        decorator.setMaximized(true);
        decorator.getStage().getIcons().add(new Image("/images/logo2.png"));
        decorator.show();
    }

    private void initialScene() {
        decorator.setTitle("DashboardFx");
        decorator.addButton(ButtonType.FULL_EFFECT);
        decorator.initTheme(GNDecorator.Theme.DEFAULT);

        decorator.setContent(ViewManager.getInstance().get("login"));

        decorator.getStage().setOnCloseRequest(event -> {
            System.out.println("退出触发事件");
            Platform.exit();
        });
    }


    @Override
    public void stop(){
    }
}
