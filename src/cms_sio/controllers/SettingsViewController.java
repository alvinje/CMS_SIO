/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms_sio.controllers;

import cms_sio.model.ApplicationSetting;
import cms_sio.model.generic.database.DBUtils;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author sgoyet
 */
public class SettingsViewController implements Initializable {

    public static final String DATA_PATH = "dataPath";
    public static final String DATABASE_PATH = "databasePath";
    public static final String HTTP_SERVEUR_URL = "Serveur URL";
    
    String[] propertyNames = new String[]{HTTP_SERVEUR_URL, DATA_PATH, DATABASE_PATH};
    
    List<ApplicationSetting> setting = new ArrayList<ApplicationSetting>();
    @FXML
    private GridPane gridPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int i = 0;
        for (String name : propertyNames) {
            ApplicationSetting setting = new ApplicationSetting(name);
            
            try {
                setting = (ApplicationSetting) DBUtils.loadFromDB(setting, setting.getClass().getField("name"));
                
            } catch (Exception ex) {
                Logger.getLogger(SettingsViewController.class.getName()).log(Level.INFO, null, "databasePath non configuré");
                setting.property="";
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cms_sio/view/SettingView.fxml"));
            Pane settingViewPane = null;
            try {
                settingViewPane = (Pane) loader.load();
            } catch (IOException ex) {
                Logger.getLogger(SettingsViewController.class.getName()).log(Level.SEVERE, "problème structurel");
                System.exit(-1);
            }
            
            if(name.equals(DATABASE_PATH) ){
                SettingDatabasePathController settingDatabasePathController = (SettingDatabasePathController) loader.getController();
                settingDatabasePathController.setData(setting);
            }
            else{
                SettingViewController settingViewController = (SettingViewController) loader.getController();
                settingViewController.setData(setting);
            }
            
            if (gridPane == null) {
                Logger.getLogger(SettingsViewController.class.getName()).log(Level.INFO, "root pan is null");
            }
            if (gridPane.getChildren() == null) {
                Logger.getLogger(SettingsViewController.class.getName()).log(Level.INFO, "root pan child is null");
            }
            
            gridPane.add(settingViewPane, 0, i);
            i++;
            
        }
        
    }    
    
}
