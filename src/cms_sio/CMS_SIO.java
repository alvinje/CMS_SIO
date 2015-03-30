/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms_sio;

import cms_sio.model.Setting;
import cms_sio.model.generic.database.DBUtils;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author sgoyet
 */
public class CMS_SIO extends Application {
    private List<Setting> settings = new ArrayList<Setting>();
   

    
    @Override
    public void start(Stage stage) throws Exception {
        showMain( stage) ;
      
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
     public void showMain(Stage stage) throws IOException  {
       FXMLLoader fxmlLoader = new FXMLLoader();
       Pane myPane = (Pane) fxmlLoader.load(getClass().getResource("view/Main.fxml"));
       
        Scene scene=new Scene(myPane);
        String css = this.getClass().getResource("view/styles/glass.css").toExternalForm();
        scene.getStylesheets().add(css); 
        stage.setScene(scene);
        stage.show();
     }
    public void showSetting(Stage stage) throws IOException  {
        
        DBUtils.init();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane myPane = (Pane) fxmlLoader.load(getClass().getResource("view/SettingsView.fxml"));
       
        Scene scene=new Scene(myPane);
        String css = this.getClass().getResource("view/styles/glass.css").toExternalForm();
        scene.getStylesheets().add(css); 
        stage.setScene(scene);
        stage.show();;
        
}
    
    
}
