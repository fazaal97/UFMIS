/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MaintenanceCall;

import RequestMove.RequestMoveController;
import com.jfoenix.controls.JFXDatePicker;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ufmis.util.UFmisUtil;

/**
 * FXML Controller class
 *
 * @author Fazal
 */
public class MaintenanceCallController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button saveMaintenance;

    private AnchorPane rootPane;
    @FXML
    private Button btnCancel;
    @FXML
    private JFXDatePicker txtDate;
    @FXML
    private ComboBox<String> cmbFloorIds;
    @FXML
    private ComboBox<String> cmbRoomIds;
    @FXML
    private ComboBox<String> cmbMaintenencetypes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cmbFloorIds.getItems().addAll("f01","f02","f03","f04","f025");
        cmbRoomIds.getItems().addAll("004D","007I","005E","006F","009H");
        cmbMaintenencetypes.getItems().addAll("Office Open","Change Bulb","Clear Room");
    }

    @FXML
    private void cancelMaint(ActionEvent event) {
        ((Stage) btnCancel.getScene().getWindow()).close();
        UFmisUtil.loadWindow(getClass().getResource("/Main/Main.fxml"), " ", null);
    }

    @FXML
    private void submitMaintanenceCall(ActionEvent event) {
        JSONArray list = new JSONArray();
        JSONObject obj, req;
        obj = new JSONObject();
        obj.put("floor", cmbFloorIds.getValue());
        obj.put("room", cmbRoomIds.getValue());
        obj.put("maintence_type", cmbMaintenencetypes.getValue());
        obj.put("date", txtDate.getValue().toString());
        list.add(obj);
        req = new JSONObject();
        req.put("calls", list);
        try (FileWriter file = new FileWriter("maintenance_call.json")) {
            file.write(req.toJSONString());
            file.flush();
            System.out.println("Saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
