/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RequestMove;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ufmis.util.UFmisUtil;

/**
 * FXML Controller class
 *
 * @author Fazal
 */
public class RequestMoveController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button addAsset;
    @FXML
    private TableView<Row> tableRequestMove;
    @FXML
    private TableColumn<Row, String> assetType;
    @FXML
    private TableColumn<Row, String> qunatity;
    @FXML
    private TableColumn<Row, String> cost;
    @FXML
    private TableColumn<Row, String> floorNumber;
    @FXML
    private TableColumn<Row, String> date;
    @FXML
    private ComboBox cmbBuildingIds;
    @FXML
    private ComboBox cmbFloorIds;
    @FXML
    private ComboBox cmbRoomIds;
    @FXML
    private ComboBox cmbAssets;

    private ObservableList<Row> data;
    @FXML
    private TableColumn<Row, String> roomnumber;
    @FXML
    private JFXDatePicker requestDate;
    @FXML
    private Button btnSubmitRequest;
    @FXML
    private JFXTextField txtCount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data = FXCollections.observableArrayList();
        tableRequestMove.setEditable(true);
        assetType.setCellValueFactory(new PropertyValueFactory<>("assetType"));
        qunatity.setCellValueFactory(new PropertyValueFactory<>("qunatity"));
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        floorNumber.setCellValueFactory(new PropertyValueFactory<>("floorNumber"));
        roomnumber.setCellValueFactory(new PropertyValueFactory<>("roomnumber"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        readAssets();
        readBuidings();
    }

    @FXML
    private void handleAddAsset(ActionEvent event) {
        if (cmbAssets.getValue().toString() != ""
                && txtCount.getText() != "" && cmbFloorIds.getValue().toString() != "" && cmbRoomIds.getValue().toString() != ""
                && requestDate.getValue().toString() != "") {
            double assetsCost = getAssetsCost(cmbAssets.getValue().toString());
            double cost = Double.valueOf(txtCount.getText()) * assetsCost;
            data.add(new Row(cmbAssets.getValue().toString(), txtCount.getText(), String.valueOf(cost),
                    cmbFloorIds.getValue().toString(), cmbRoomIds.getValue().toString(), requestDate.getValue().toString()));
            tableRequestMove.setItems(data);
        }
    }

    

    private double getAssetsCost(String key) {
        JSONParser parser = new JSONParser();
        double cost = 0;
        try {
            Object obj = parser.parse(new FileReader("assets.json"));
            JSONObject jsonObject = (JSONObject) obj;
            // loop array
            JSONArray msg = (JSONArray) jsonObject.get("assets");
            Iterator<JSONObject> iterator = msg.iterator();
            while (iterator.hasNext()) {
                JSONObject ob = iterator.next();
                if (key.equals((String) ob.get("Name"))) {
                    cost = Double.valueOf((String) ob.get("Cost"));
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
        }
        return cost;
    }

    private void readAssets() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("assets.json"));
            JSONObject jsonObject = (JSONObject) obj;
            // loop array
            JSONArray msg = (JSONArray) jsonObject.get("assets");
            Iterator<JSONObject> iterator = msg.iterator();
            while (iterator.hasNext()) {
                JSONObject ob = iterator.next();
                cmbAssets.getItems().add((String) ob.get("Name"));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
        }

    }

    private void readBuidings() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("buildings.json"));
            JSONObject jsonObject = (JSONObject) obj;
            // loop array
            JSONArray buildings = (JSONArray) jsonObject.get("buildings");
            Iterator<JSONObject> iterator = buildings.iterator();
            JSONObject bOb;
            cmbBuildingIds.getItems().add("Select Building");
            while (iterator.hasNext()) {
                bOb = iterator.next();
                cmbBuildingIds.getItems().add((String) bOb.get("Name"));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
        }

    }

    private void loadBuildingFloors() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("buildings.json"));
            JSONObject jsonObject = (JSONObject) obj;
            // loop array
            JSONArray buildings = (JSONArray) jsonObject.get("buildings");
            Iterator<JSONObject> iterator = buildings.iterator();
            JSONObject bOb, fOb;
            JSONArray floors;
            Iterator<JSONObject> fIterator;
            cmbFloorIds.getItems().add("Select Floor");
            while (iterator.hasNext()) {
                bOb = iterator.next();
                if (cmbBuildingIds.getValue().equals((String) bOb.get("Name"))) {
                    floors = (JSONArray) bOb.get("floors");
                    fIterator = floors.iterator();
                    while (fIterator.hasNext()) {
                        fOb = fIterator.next();
                        cmbFloorIds.getItems().add((String) fOb.get("floor_id"));
                    }
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadBuildingFloorRooms() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("buildings.json"));
            JSONObject jsonObject = (JSONObject) obj;
            // loop array
            JSONArray buildings = (JSONArray) jsonObject.get("buildings");
            Iterator<JSONObject> iterator = buildings.iterator();
            JSONObject bOb, fOb, rOb;
            JSONArray floors, rooms;
            Iterator<JSONObject> fIterator;
            Iterator<JSONObject> rIterator;
            cmbRoomIds.getItems().add("Select Room");
            while (iterator.hasNext()) {
                bOb = iterator.next();
                if (cmbBuildingIds.getValue().equals((String) bOb.get("Name"))) {
                    floors = (JSONArray) bOb.get("floors");
                    fIterator = floors.iterator();
                    while (fIterator.hasNext()) {
                        fOb = fIterator.next();
                        if (cmbFloorIds.getValue().equals((String) fOb.get("floor_id"))) {
                            rooms = (JSONArray) fOb.get("rooms");
                            rIterator = rooms.iterator();
                            while (rIterator.hasNext()) {
                                rOb = rIterator.next();
                                cmbRoomIds.getItems().add((String) rOb.get("room_id"));
                            }
                            break;
                        }
                    }
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void selectBuilding(ActionEvent event) {
        loadBuildingFloors();
    }

    @FXML
    private void selectRoom(ActionEvent event) {
        loadBuildingFloorRooms();
    }

    @FXML
    private void submitRequest(ActionEvent event) {
        writeRequest();
    }
    
    private void writeRequest() {
        JSONArray list = new JSONArray();
        JSONObject obj = null,req;        
        for (Row row : data) {
            obj = new JSONObject();
            obj.put("assetType", row.getAssetType());
            obj.put("qunatity", row.getQunatity());
            obj.put("cost", row.getCost());
            obj.put("floorNumber", row.getFloorNumber());
            obj.put("roomnumber", row.getRoomnumber());
            obj.put("date", row.getDate());            
            list.add(obj);
        }
        req = new JSONObject();
        req.put("request_moves", list);
        try (FileWriter file = new FileWriter("requestmoves.json")) {
            file.write(req.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCancel(ActionEvent event) {
        ((Stage)anchorPane.getScene().getWindow()).close();
        UFmisUtil.loadWindow(getClass().getResource("/Main/Main.fxml"), " ", null);
    }

    public static class Row {

        private final String assetType;
        private final String qunatity;
        private final String cost;
        private final String floorNumber;
        private final String roomnumber;
        private final String date;

        public Row(String assetType, String qunatity, String cost, String floorNumber, String roomnumber, String date) {
            this.assetType = assetType;
            this.qunatity = qunatity;
            this.cost = cost;
            this.floorNumber = floorNumber;
            this.roomnumber = roomnumber;
            this.date = date;
        }

        public String getAssetType() {
            return assetType;
        }

        public String getQunatity() {
            return qunatity;
        }

        public String getCost() {
            return cost;
        }

        public String getFloorNumber() {
            return floorNumber;
        }

        public String getRoomnumber() {
            return roomnumber;
        }

        public String getDate() {
            return date;
        }

    }

}
