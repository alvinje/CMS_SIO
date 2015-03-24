/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms_sio.controllers;

import cms_sio.templates.TemplateParser;
import cms_sio.view.Toast;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sgoyet
 */
public class TemplateController implements Initializable {

    @FXML
    Button file;
    @FXML
    WebView web;


    public void handleButtonClickForFile(ActionEvent event)  {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "open directory chooser");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisissez un fichier");

        File templateFile = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            try {
                TemplateParser htmlParser=new TemplateParser( templateFile) ;
        
                
                
                setWebView(templateFile);
                file.setText(templateFile.getCanonicalPath());
             } catch (IOException ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
                Toast.makeText("Problème de chargement du fichier", Toast.DURATION_LONG);
            }catch (Exception ex) {
                    
                Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    void setWebView(File file) {
        String path="file://"+ file.getAbsolutePath();
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Chargement de "+path);
        Toast.makeText("Chargement de " + path, Toast.DURATION_LONG);
        web.getEngine().load(path);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
