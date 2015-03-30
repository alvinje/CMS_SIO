/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms_sio.controllers;

import cms_sio.model.Setting;
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
   public static final String DATA_PATH="dataPath";
   public static final String DATABASE_PATH="databasePath";
   public static final String HTTP_SERVEUR_URL="Serveur URL";
   
   String[] propertyNames=new String[]{HTTP_SERVEUR_URL,DATA_PATH,DATABASE_PATH};
   
   List<Setting> setting=new ArrayList<Setting>();
   @FXML
    private GridPane gridPane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int i=0;
        for (String name: propertyNames){
            
            try {
                Setting setting=new Setting(name);
                if (DBUtils.connection != null) {
                    try {
                        setting = (Setting) DBUtils.loadFromDB(setting, setting.getClass().getField("name"));
                    } catch (Exception ex) {
                        
                    }
                }
                FXMLLoader loader = new FXMLLoader(getClass().getResource( "/cms_sio/view/SettingView.fxml"));
                
                Pane myPane = (Pane) loader.load();
                
                SettingViewController settingViewController = (SettingViewController) loader.getController();
                settingViewController.setData(setting);
                if (gridPane==null){
                    Logger.getLogger(SettingsViewController.class.getName()).log(Level.INFO, "root pan is null" );
                }
                if (gridPane.getChildren()==null){
                    Logger.getLogger(SettingsViewController.class.getName()).log(Level.INFO, "root pan child is null" );
                }
                
                gridPane.add(myPane, 0, i);
                i++;
            } catch (IOException ex) {
                Logger.getLogger(SettingsViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
                
           
           
            
        }

    }    
    
}
