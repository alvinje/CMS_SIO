/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms_sio.controllers;

import cms_sio.model.ApplicationSetting;
import cms_sio.model.generic.database.DBUtils;
import cms_sio.view.Toast;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 * 
 * @author sgoyet
 */
public class SettingViewController extends SettingDatabasePathController implements Initializable {

    ApplicationSetting setting;
    @FXML
    private GridPane gridPane;
    String property;
    Button propertyDirPath;
    TextField propertyURL;
    Button validationPropertyURL;

    public void setData(ApplicationSetting setting) {
        this.setting = setting;
        gridPane.add(new Label(setting.getName()), 0, 0);
        property = setting.getProperty();
        if (property.equals("")) {
            property = "to define";
        }
        switch (setting.getName()) {
            case SettingsViewController.DATABASE_PATH:
                Logger.getLogger(getClass().getName()).log(Level.INFO, "case setButtonForDir");
                setButtonForDir(property);

                break;
            case SettingsViewController.DATA_PATH:
                setButtonForDir(property);

                break;
            case SettingsViewController.HTTP_SERVEUR_URL:
                setTextField(property);
                break;
        }

    }

    void setButtonForDir(String property) {
        propertyDirPath = new Button(property);
        propertyDirPath.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Logger.getLogger(getClass().getName()).log(Level.INFO, "on vas dans handleButtonClickForDir");
                handleButtonClickForDir(e);
            }
        });
        gridPane.add(propertyDirPath, 1, 0);
    }

    void setButtonForFile(String property) {
        propertyDirPath = new Button(property);
        propertyDirPath.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                handleButtonClickForFile(e);
            }
        });
        gridPane.add(propertyDirPath, 1, 0);
    }

    public void handleButtonClickForDir(ActionEvent event) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "open directory chooser");
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choisissez un dossier");
        File file = directoryChooser.showDialog(new Stage());

        if (file != null) {
            try {
                if (setting.getName().equals(SettingsViewController.DATABASE_PATH)) {
                    Logger.getLogger(getClass().getName()).log(Level.INFO, "on va fair un storeDataBasePath(file);");                  
                    storeDataBasePath(file);
                    if (DBUtils.connect()) {
                        Logger.getLogger(getClass().getName()).log(Level.INFO, "on est dans le connect");
                        property = file.getCanonicalPath();
                        propertyDirPath.setText(property);
                        Toast.makeText("OK", Toast.DURATION_SHORT);
                        SettingViewController.this.setting.setProperty(property);
                        
                        if (DBUtils.updateDB(SettingViewController.this.setting)) {
                            Logger.getLogger(getClass().getName()).log(Level.INFO, "on va fair un Toast.makeText");
                            Toast.makeText("DB path validé", Toast.DURATION_LONG);
                        }

                    } else {
                        Logger.getLogger(getClass().getName()).log(Level.INFO, "La connection a echouée");
                        Toast.makeText("La connection a échouée !", Toast.DURATION_LONG);
                    }

                } else {
                    property = file.getCanonicalPath();
                    propertyDirPath.setText(property);
                    SettingViewController.this.setting.setProperty(property);
                    if (DBUtils.updateDB(SettingViewController.this.setting)) {
                        alert("DB path validé");
                    }
                }

            } catch (IOException ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void storeDataBasePath(File file) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "on est dans le store");        
        File databasePathFile = new File("databasePath.txt");
        try {
            Logger.getLogger(getClass().getName()).log(Level.INFO, "write in file:" + databasePathFile.getCanonicalPath());
            FileWriter fileWriter = new FileWriter(databasePathFile);
            Logger.getLogger(getClass().getName()).log(Level.INFO, "2");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            Logger.getLogger(getClass().getName()).log(Level.INFO, "write into file: " + databasePathFile.getCanonicalPath());
            bufferedWriter.write(file.getCanonicalPath() + "/cms.db");
            Logger.getLogger(getClass().getName()).log(Level.INFO, "4");
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            System.exit(-1);
        }
    }

    public void handleButtonClickForFile(ActionEvent event) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "open directory chooser");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisissez un fichier");

        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            try {
                property = file.getCanonicalPath();
                propertyDirPath.setText(property);
                SettingViewController.this.setting.setProperty(property);

                if (DBUtils.updateDB(SettingViewController.this.setting)) {
                    alert("Data path validé");
                }

            } catch (IOException ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    void setTextField(String property) {

        propertyURL = new TextField(property);
        validationPropertyURL = new Button("Ok");
        //Image image = new Image(getClass().getResourceAsStream("/cms_sio/resources/ok.png"));
        //validationPropertyURL.setGraphic(new ImageView(image));
        GridPane grid = new GridPane();
        grid.add(propertyURL, 0, 0);
        grid.add(validationPropertyURL, 1, 0);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(10);

        grid.getColumnConstraints().addAll(col1, col2);
        gridPane.add(grid, 1, 0);

        validationPropertyURL.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Logger.getLogger(SettingViewController.class.getName()).log(Level.INFO, "Clicked");
                handleTextFieldAction();
            }
        });

    }

    public void handleTextFieldAction() {
        Logger.getLogger(SettingViewController.class.getName()).log(Level.CONFIG, "new value  " + propertyURL.getText());
        if (checkURI(propertyURL.getText())) {
            setting.setProperty(propertyURL.getText());

            if (DBUtils.updateDB(setting)) {
                alert("Server path validé");
            }

        }
    }

    boolean checkURI(String value) {

        try {

            URI uri = new URI(value);
            Logger.getLogger(SettingViewController.class.getName()).log(Level.CONFIG, "scheme " + uri.getScheme());
            if (!"http".equals(uri.getScheme())) {
                return alert("Http seulement est accepté");
            } else if (uri.getHost() == null) {
                return alert("Host est nul");
            }

        } catch (URISyntaxException ex) {
            Logger.getLogger(SettingViewController.class.getName()).log(Level.SEVERE, null, ex);
            return alert("Syntaxe incorrecte");
        }
        return true;
    }

    boolean alert(String message) {
        Logger.getLogger(SettingViewController.class.getName()).log(Level.CONFIG, "BEEP");
        Toast toast = Toast.makeText(message, new Duration(2000));
        toast.show(gridPane);
       // toast.ma

        // toast.show(gridPane);
        return false;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
