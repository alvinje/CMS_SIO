/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms_sio.controllers;

import cms_sio.model.generic.database.DBUtils;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author sgoyet
 */
public class MainController implements Initializable {

    @FXML private Button page;
    @FXML private Button data;
    @FXML private Button template;    
    @FXML private Button configuration; 
    @FXML private FlowPane body; 
    
    public void showTemplate() throws IOException{
       
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane myPane = (Pane) fxmlLoader.load(getClass().getResource("/cms_sio/view/Template.fxml"));
        body.getChildren().clear();
        body.getChildren().add(myPane);
        Logger.getLogger(MainController.class.getName()).log(Level.INFO, "Show template");
       
    }
    public void showConfiguration() throws IOException{
        DBUtils.init();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane myPane = (Pane) fxmlLoader.load(getClass().getResource("/cms_sio/view/SettingsView.fxml"));
        body.getChildren().clear();
        body.getChildren().add(myPane);
       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        try {
            showTemplate();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }    
    
}
