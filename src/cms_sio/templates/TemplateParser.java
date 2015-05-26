/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms_sio.templates;

import cms_sio.model.TemplateConfiguration;
import cms_sio.model.ElementType;
import cms_sio.model.PageData;
import cms_sio.model.PageDataElement;
import cms_sio.model.Multiplicity;
import cms_sio.model.Template;
import cms_sio.model.TemplateVariableElement;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.nodes.XmlDeclaration;
import org.jsoup.select.Elements;

/**
 *
 * @author sgoyet
 */
public class TemplateParser {
    
    public final static String TEXT="text";
    public final static String URL="url";
    
    public PageData data;
    public Template template;
    
    /**
     * reçoit comme paramètre un fichier, puis il va le parser en extrayant un template
     * et les données associées au template.
     * 
     *
     * @param file
     * @throws Exception 
     */
    public TemplateParser(File file) throws Exception{
        
        data=new PageData();
        template=new Template(file);
        System.out.println("Création d'un template");
        TemplateConfiguration configuration=new TemplateConfiguration();
    
        
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Parsing file using "+"file://"+file.getParentFile().getAbsolutePath());
        Document doc = Jsoup.parse(file, "UTF-8", "file://"+file.getParentFile().getAbsolutePath());
        
        
        Element head=doc.head();
        Element body=doc.body();
        parseNode(head,  configuration,  data);
        parseNode(body,  configuration,  data);
        
        template.templateConfiguration=configuration;
        data.template=template;
        
        
     
        Logger.getLogger(getClass().getName()).log(Level.INFO, "Parsing terminé");
    }
    
    
    void parseNode(Element element, TemplateConfiguration configuration, PageData pageData){
        //tag name, attributes, child nodes
        String key="";
        getData(element,key,configuration,  pageData);
        
       
    }
     void getData(Node node, String parent_key,TemplateConfiguration configuration, PageData pageData){
        //tag name, attributes, child nodes
         Logger.getLogger(getClass().getName()).log(Level.INFO, "Element name "+node.nodeName().toUpperCase()+ " "+node.toString());
         String key=parent_key+"_"+node.siblingIndex();
         
       
         
         if ( node instanceof Element ||  node instanceof DataNode || node instanceof TextNode  ){ //or contents of style, script tags etc, where contents should not show in text()
            String content=getUrl(node);
            boolean hasUrl=(content!=null && !content.trim().replaceAll(" ","").replaceAll("\t", "").equals(""));
              Logger.getLogger(getClass().getName()).log(Level.INFO, "has url  "+hasUrl+ " "+content);
            if (!hasUrl){
                content=getContent(node);
            }
         
            if (content!=null && !content.trim().replaceAll(" ","").replaceAll("\t", "").equals("")){
               
                Logger.getLogger(getClass().getName()).log(Level.INFO, "Add element with content   "+content);
                TemplateVariableElement templateVariableElement=new TemplateVariableElement(getType(node,hasUrl),Multiplicity._1.toString(),key,isStrucrtural( node, hasUrl));
                PageDataElement pagdeDataElement=new PageDataElement(templateVariableElement , content);
                configuration.addVariableElement(templateVariableElement);
                pageData.addPageDataElement(pagdeDataElement);
            }
            
         }else{
               Logger.getLogger(getClass().getName()).log(Level.INFO, "Element name "+node.getClass().getSimpleName());
       
         }
  
        for ( Node child:node.childNodes()){
            getData(child, key, configuration,  pageData);
        }
    }
     
    int isStrucrtural(Node node, boolean hasURL){
        if (hasURL){
            return 1;
        }else{
            return 0;
        }
    }
    String getUrl(Node node) {

        String urlHref = node.attributes().get("href");
        if (urlHref != null) {

            return urlHref;

        }
        String urlSrc = node.attributes().get("src");
        if (urlSrc != null) {

            return urlSrc;

        }
        return null;

    }
    String getContent(Node node){
      if (node instanceof TextNode ){
                return ((TextNode) node).getWholeText();
            }else{
                return null;
            }
    }
    String getType(Node node, boolean hasUrl) {
        String parentName=node.parent().nodeName().toUpperCase();
        Logger.getLogger(getClass().getName()).log(Level.WARNING, "Parent name "+parentName + " has url "+hasUrl);
        if (hasUrl) {
            switch (parentName) {
                case "IMG":
                    return ElementType.Image.toString();
                case "A":
                    return ElementType.PageRef.toString();
                default:
                    return ElementType.DataUrl.toString();
            }

        } else {
            switch (parentName) {

                case "IMG":
                    return ElementType.Image.toString();
                case "H1":
                    return ElementType.Title.toString();
                case "H2":
                    return ElementType.Title.toString();
                case "H3":
                    return ElementType.Title.toString();
                case "H4":
                    return ElementType.Title.toString();
                case "TITLE":
                    return ElementType.Title.toString();
                default:
                    return ElementType.TEXT.toString();

            }
        }

    }
    void getArtibutes(Node node){
        Attributes attributes=node.attributes();
        for (Attribute attribute:attributes){
                
            String attributeName=attribute.getKey();
            String value=attribute.getValue();
        }
        
        
    }
       
    
     
    
    
}
