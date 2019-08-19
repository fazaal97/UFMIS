package Login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ufmis.util.UFmisUtil;

public class LoginController implements Initializable {

    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton btnCancel;
    @FXML
    private AnchorPane loginWindow;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        ((Stage) loginWindow.getScene().getWindow()).close();
        UFmisUtil.loadWindow(getClass().getResource("/Login/Astaff/Astaff.fxml"), " ", null);
    }

    @FXML
    private void handleCancelButtonAction(ActionEvent event) {
        ((Stage) btnCancel.getScene().getWindow()).close();
        UFmisUtil.loadWindow(getClass().getResource("/Main/Main.fxml"), " ", null);
    }

}
