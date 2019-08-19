/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login.Astaff.BuildingView;

import com.jfoenix.controls.JFXTabPane;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Fazal
 */
public class BuildingViewController implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane rootAnchorPane;
    @FXML
    private JFXTabPane mainTabPane;
    @FXML
    private Tab bookIssueTab;
    @FXML
    private HBox book_info;
    @FXML
    private HBox member_info;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loadFloor1(ActionEvent event) {
    }

    @FXML
    private void loadFloor2(ActionEvent event) {
    }

    @FXML
    private void loadFloor3(ActionEvent event) {
    }

    @FXML
    private void loadFloor4(ActionEvent event) {
    }
    
}
