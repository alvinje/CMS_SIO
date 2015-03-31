package cms_sio.controllers;

import cms_sio.model.Setting;
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
 *
 * @author goyet.stephane
 */
public class SettingDatabasePathController implements Initializable {

    Setting setting;
    @FXML
    private GridPane gridPane;
    String property;
    Button propertyDirPath;
    TextField propertyURL;
    Button validationPropertyURL;

    public void setData(Setting setting) {
        this.setting = setting;
        gridPane.add(new Label(setting.getName()), 0, 0);
        property = setting.getProperty();
        if (property.equals("")) {
            property = "to define";
        }

        Logger.getLogger(getClass().getName()).log(Level.INFO, "case setButtonForDir");
        setButtonForDir(property);

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

    public void handleButtonClickForDir(ActionEvent event) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "open directory chooser");
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choisissez un dossier");
        File file = directoryChooser.showDialog(new Stage());

        if (file != null) {
            try {
                Logger.getLogger(getClass().getName()).log(Level.INFO, "on va fair un storeDataBasePath(file);");
                storeDataBasePath(file);
                if (DBUtils.connect()) {
                    Logger.getLogger(getClass().getName()).log(Level.INFO, "on est dans le connect");
                    property = file.getCanonicalPath();
                    propertyDirPath.setText(property);
                    Toast.makeText("OK", Toast.DURATION_SHORT);
                    SettingDatabasePathController.this.setting.setProperty(property);

                    if (DBUtils.updateDB(SettingDatabasePathController.this.setting)) {
                        Logger.getLogger(getClass().getName()).log(Level.INFO, "on va fair un Toast.makeText");
                        Toast.makeText("DB path validé", Toast.DURATION_LONG);
                    }

                } else {
                    Logger.getLogger(getClass().getName()).log(Level.INFO, "La connection a echouée");
                    Toast.makeText("La connection a échouée !", Toast.DURATION_LONG);
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
