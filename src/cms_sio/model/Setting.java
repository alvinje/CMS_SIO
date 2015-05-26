/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms_sio.model;

import cms_sio.model.generic.HasId;
import cms_sio.model.generic.database.DBUtils;
import java.sql.SQLException;


/**
 *Définit HasId avec les données des ID et des Names pour ensuite charger depuis la base
 * de données et il va enregistrer les données en mettant à jour la base de données
 * @author sgoyet
 */
public class Setting implements HasId{
    public int id;
    public String name;
    public String property;
    public Setting(){
        
    }
    public Setting(String name){
        this.name=name;
        property="";
    }
         
    public Setting(String name, String property)  {
      this.name=name;
      this.property=property;
    }
     
    public Setting(int id)  throws Exception{
       DBUtils.loadFromDB(this, id);
    }
    
    
    @Override
    public HasId loadFromDB(int id) throws Exception {
      DBUtils.loadFromDB(this, id);
      return this;
    }

    @Override
    public boolean save() {
        return DBUtils.updateDB(this); 
    }
    @Override
    public int getId() {
    return id;    }

    @Override
    public void setId() throws SQLException {
        setId(DBUtils.getId(this));   }

    /**Constructeur de setID. Configuration d'un ID
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /** Récupère le nom
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**Constructeur d'un nom.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**Récupère la chaine de caractère de la Propriété
     * @return the property
     */
    public String getProperty() {
        return property;
    }

    /**Constructeur de la chaine de caractère Property.
     * @param property the property to set
     */
    public void setProperty(String property) {
        this.property = property;
    }

   
    
}
