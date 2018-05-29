package repolezanettiperuzzi.view;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginFXMLController extends FXMLController{

    private Stage stage;
    private GameView gV;

    @FXML
    // The reference of inputText will be injected by the FXML loader
    private TextField textFieldName;

    // The reference of outputText will be injected by the FXML loader
    @FXML
    private PasswordField textFieldPwd;

    @FXML
    private ToggleGroup groupConn;

    @FXML
    private ToggleGroup groupUI;

    // location and resources will be automatically injected by the FXML loader
    @FXML
    private URL location;

    @FXML
    private ResourceBundle resources;

    // Add a public no-args constructor
    public LoginFXMLController()
    {
    }

    @FXML
    private void initialize()
    {
        setGameView(new GameView());
        gV.setFXMLController(this);
    }

    @FXML
    private void onSubmit(ActionEvent event)
    {
        if ((textFieldName.getText() != null && textFieldPwd.getText()!=null)) {

            RadioButton conn = (RadioButton) groupConn.getSelectedToggle();
            RadioButton userInt = (RadioButton) groupUI.getSelectedToggle();
            this.stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Platform.runLater(() -> {
                try {
                    gV.onLogin(stage, textFieldName.getText(), textFieldPwd.getText(), conn.getText(), userInt.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public void setWaitingRoomScene() {
        WaitingRoomFXMLController controller = new WaitingRoomFXMLController();
        controller.setGameView(gV);
        gV.setFXMLController(controller);
        controller.setStage(stage);
        FXMLLoader loader = null;
        try {
            loader = new FXMLLoader(new File("src/main/java/repolezanettiperuzzi/view/WaitingRoomFXML.fxml").toURI().toURL());
            loader.setController(controller);
            Group root = (Group) loader.load();
            stage.setScene(new Scene(root, 600 , 600));
            stage.setUserData(loader);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void setGameView(GameView gV){
        this.gV = gV;
    }
}