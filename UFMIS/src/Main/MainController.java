/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import com.jfoenix.controls.JFXTabPane;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ufmis.util.UFmisUtil;

/**
 * FXML Controller class
 *
 * @author Fazal
 */
public class MainController implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane rootAnchorPane;
    @FXML
    private JFXTabPane mainTabPane;
    @FXML
    private Tab staffTab;
    @FXML
    private Tab aStaffTab;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loadRequestOfficeMove(ActionEvent event) {
        ((Stage)rootPane.getScene().getWindow()).close();
        UFmisUtil.loadWindow(getClass().getResource("/RequestMove/RequestMove.fxml"), " ", null);
    }

    @FXML
    private void loadRequestMaintanceCall(ActionEvent event) {
        ((Stage)rootPane.getScene().getWindow()).close();
        UFmisUtil.loadWindow(getClass().getResource("/MaintenanceCall/maintenanceCall.fxml"), " ", null);
    }

    @FXML
    private void loadChangingRequest(ActionEvent event) {
    }

    @FXML
    private void loadLogin(ActionEvent event) {
        ((Stage)rootPane.getScene().getWindow()).close();
         UFmisUtil.loadWindow(getClass().getResource("/Login/Login.fxml"), " ", null);
    }
    
}
