package utility;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.UUID;

public class Utility {
    public static void hideWindow(Event event){
        ((Node)(event.getSource())).getScene().getWindow().hide();//acilan pencereden qabagki pencereni baglamaq ucun

    }

    public static String passwordGenerator(){
        return UUID.randomUUID().toString().substring(0,4);
    }
    //yeni bir frame yaratmaq ucun metod yaratdiq ki artiq kod tekrarina vaxt itirmeyek qisa bir sekilde utilityinin obyektini yaradib
    //-----oradan bu metodu cagiririq. (ve kod tekrarina yol vermirirk_);
//    public void showStage(String tittle , String path){
//        Stage primaryStage = new Stage();
//        Parent root = null;
//        try {
//            root = FXMLLoader.load(getClass().getResource(path));//"/fxml/showUsers.fxml"
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        primaryStage.setTitle(tittle);
//        primaryStage.getIcons().add(new Image("/icons/pngUFM.jpeg"));
//        primaryStage.setScene(new Scene(root, 708, 500));//umumi acilmaq olcusunu buradan veririrk
//        primaryStage.show();
//    }
}
