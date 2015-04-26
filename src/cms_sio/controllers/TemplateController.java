/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms_sio.controllers;


import cms_sio.model.PageData;
import cms_sio.model.PageDataElement;
import cms_sio.model.Template;
import cms_sio.model.TemplateVariableElement;
import cms_sio.templates.TemplateParser;
import cms_sio.view.Toast;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sgoyet
 */
public class TemplateController implements Initializable {

    @FXML Button file;
   // @FXML WebView web;
    @FXML VBox variableElementList;

    public void handleButtonClickForFile(ActionEvent event) throws IOException, Exception {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "open directory chooser");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisissez un fichier");

        File templateFile = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
          
                TemplateParser htmlParser;
                try {
                    htmlParser = new TemplateParser(templateFile);
                } catch (Exception ex) {
                    Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE,"PArsing failed "+ex);
                    return;
                }

                assert (htmlParser.template.templateConfiguration.templateVariableElement != null);
                assert (htmlParser.data != null);
                Logger.getLogger(getClass().getName()).log(Level.INFO, "Nombre d'éléments variables "+htmlParser.template.templateConfiguration.templateVariableElement.size());
                
                Template template=  htmlParser.data.template;
                
                PageData pageData=htmlParser.data;
              
                for (TemplateVariableElement templateVariableElement :template.templateConfiguration.templateVariableElement) {

                    Logger.getLogger(getClass().getName()).log(Level.INFO,  "Un element variable a été trouvé");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/cms_sio/view/TemplateVariableElementFXML.fxml"));
                    Parent root;
                    try {
                        root = (Parent) loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE,  "Failed to load view "+ex);
                        return;
                    }
                    Logger.getLogger(getClass().getName()).log(Level.INFO, "vue de l' élement variable créé");
                    TemplateVariableElementFXMLController controler = loader.getController();
                    assert (controler != null);
                    
                    PageDataElement pageDataElement=pageData.getPageDataElement(templateVariableElement);
                       assert (pageDataElement != null);
                       
                    controler.setVariableElement(templateVariableElement,pageDataElement);
                   
                    variableElementList.getChildren().add(root);
                     Logger.getLogger(getClass().getName()).log(Level.INFO, "Vue ajoutée  " + templateVariableElement.getElementType());
                }

                //setWebView(templateFile);
                file.setText(templateFile.getCanonicalPath());
 

        }
    }

    void setWebView(File file) {
        String path = "file://" + file.getAbsolutePath();
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Chargement de " + path);
        Toast.makeText("Chargement de " + path, Toast.DURATION_LONG);
        //web.getEngine().load(path);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
