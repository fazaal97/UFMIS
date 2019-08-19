/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainLoader extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        stage.setTitle("Uninversity of Gugsi Facilities Management Information System ");

//        LibraryAssistantUtil.setStageIcon(stage);
//        
//        new Thread(() -> {
//            DatabaseHandler.getInstance();
//        }).start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}