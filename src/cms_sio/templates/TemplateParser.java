/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms_sio.templates;

import cms_sio.model.Configuration;
import cms_sio.model.ConfigurationType;
import cms_sio.model.Data;
import cms_sio.model.DataPiece;
import cms_sio.model.Multiplicity;
import cms_sio.model.Template;
import cms_sio.model.VariableElement;
import cms_sio.view.Toast;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author sgoyet
 */
public class TemplateParser {
    
    public final static String TEXT="text";
    public final static String URL="url";
    
    public Data data;
    public Template template;
    
    public TemplateParser(File file) throws Exception{
        
        data=new Data();
        Template template=new Template(file);
        Toast.makeText("Création d'un template", Toast.DURATION_SHORT);
        Configuration configuration=new Configuration();
        template.setConfiguration(configuration);
        
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Parsing file using "+"file://"+file.getParentFile().getAbsolutePath());
        Document doc = Jsoup.parse(file, "UTF-8", "file://"+file.getParentFile().getAbsolutePath());
        Elements elements= doc.getElementsByAttribute("extra_cms");
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Nb tags found )"+elements.size());
          
        for (Element element:elements){
            Logger.getLogger(getClass().getName()).log(Level.INFO, "Found "+element.attr("extra_cms"));
            String contentAttribute=element.attr("extra_cms");
            String id=element.id();
            if (id.equals("")){
                Toast.makeText("Element cms sans id ", Toast.DURATION_LONG);
                throw new Exception();
            }
            
            VariableElement variableElement=parseContentAttribute( contentAttribute,id) ;
             Toast.makeText("Ajout d'une configuration pour le template", Toast.DURATION_SHORT);
            configuration.addVariableElement(variableElement);
            
            String data=element.html();
            Toast.makeText("Création d'une donnée  pour l'id "+id, Toast.DURATION_SHORT);
            DataPiece dataPiece=new DataPiece(variableElement,data);
          
        }
        data.template=template;
        Toast.makeText("Ajout du template pour les données", Toast.DURATION_SHORT);
    }
    
    VariableElement parseContentAttribute(String contentAttribute,String element_id) throws Exception{
          String [] pieceAttributes=contentAttribute.split("_");
          if (pieceAttributes.length<2){
              Toast.makeText("CMS attribut mal formé (attentu : type_name ", Toast.DURATION_LONG);
              throw new Exception();
          }
          
          for (ConfigurationType configurationType :ConfigurationType.values()){
              if (pieceAttributes[0].equals(configurationType.toString())){
                   Toast.makeText("Création d'un élément de configutation pour  "+pieceAttributes[1], Toast.DURATION_SHORT);
                return new VariableElement(configurationType.toString(),Multiplicity._1.toString(),element_id);
              }
          }

          throw new Exception();
    }
    
}
