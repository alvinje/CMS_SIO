/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms_sio.controllers;

import cms_sio.model.PageDataElement;
import cms_sio.model.TemplateVariableElement;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author sgoyet
 */
public class TemplateVariableElementFXMLController implements Initializable {

    @FXML Label id;
    @FXML Label type;
    @FXML Label multiplicity;
    @FXML TextArea data;
    @FXML AnchorPane anchorContainer;
    @FXML CheckBox isData;
      
    TemplateVariableElement variableElement;
    
    
    public void setVariableElement(TemplateVariableElement variableElement,PageDataElement pageDataElement){
        this.variableElement=variableElement;
        type.setText(variableElement.getElementType());
        multiplicity.setText(variableElement.multiplicity);
        data.setText(pageDataElement.content);
        id.setText(variableElement.getElementId());
        isData.setSelected(variableElement.isStructural==0);
        setColor();
        int lines = 0;
        int chars = 0;
         Scanner in = new Scanner(pageDataElement.content);
        while(in.hasNextLine())  {
            lines++;
            String line = in.nextLine();
           lines+=line.length()/70;
            if (line.length()>chars){
                chars=line.length();
            }
      
        }
       data.setPrefRowCount(lines+1);
       data.setPrefColumnCount(chars);
        anchorContainer.setMinHeight(20*(lines+1));
    }
    
    public void onIsDataChanged(){
        if (isData.isSelected()){
            variableElement.isStructural=0;
        }else{
                variableElement.isStructural=1;
        }
         setColor();
    }
    
    void setColor(){
        if (variableElement.isStructural==0){
           data.setStyle("-fx-background-color: slateblue; -fx-text-fill: black;");
        }else{
             data.setStyle("-fx-background-color: grey; -fx-text-fill: black;"); 
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
 
    
    
    
}
