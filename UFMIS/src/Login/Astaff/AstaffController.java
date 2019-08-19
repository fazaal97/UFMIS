/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login.Astaff;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ufmis.util.UFmisUtil;

/**
 * FXML Controller class
 *
 * @author Fazal
 */
public class AstaffController implements Initializable {

    @FXML
    private JFXButton buildingView;
    @FXML
    private JFXButton FmoReport;
    @FXML
    private JFXButton setting;
    @FXML
    private JFXButton cancel;
    @FXML
    private AnchorPane aStaffWindow;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void aCancel(ActionEvent event) {
        ((Stage) aStaffWindow.getScene().getWindow()).close();
        UFmisUtil.loadWindow(getClass().getResource("/Main/Main.fxml"), " ", null);
    }

    @FXML
    private void loadBuildingView(ActionEvent event) {
    }

    @FXML
    private void loadFmoReport(ActionEvent event) {
    }

    @FXML
    private void loadSetting(ActionEvent event) {
        ((Stage) aStaffWindow.getScene().getWindow()).close();
        UFmisUtil.loadWindow(getClass().getResource("/ufmis/settings/settings.fxml"), " ", null);
    }

}
