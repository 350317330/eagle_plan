package cn.walking_dead.module.login;

import cn.walking_dead.Main;
import cn.walking_dead.plugin.ViewManager;
import com.gn.GNAvatarView;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {

    @FXML
    private GNAvatarView avatar;
    @FXML private HBox box_username;
    @FXML private HBox box_password;
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private Button login;

    @FXML private Label lbl_password;
    @FXML private Label lbl_username;
    @FXML private Label lbl_error;

    private RotateTransition rotateTransition = new RotateTransition();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rotateTransition.setNode(avatar);
        rotateTransition.setByAngle(360);
        rotateTransition.setDuration(Duration.seconds(1));
        rotateTransition.setAutoReverse(true);

        //addEffect(password);
        //addEffect(username);

        //setupListeners();
    }

    @FXML
    private void switchCreate(){
        Main.decorator.setContent(ViewManager.getInstance().get("account"));
    }

    @FXML
    private void loginAction(){

    }


}
