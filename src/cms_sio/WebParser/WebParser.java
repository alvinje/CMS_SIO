/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms_sio.WebParser;

import java.io.File;
import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author goyet.stephane
 */
public class WebParser {

    public void WebParser() throws IOException {
        final String authUser = "etudiant";
        final String authPassword = "secret";

        Authenticator.setDefault(
                new Authenticator() {
                    public PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                authUser, authPassword.toCharArray());
                    }
                }
        );
        System.setProperty("http.proxyHost", "192.168.216.250");
        System.setProperty("http.proxyPort", "8080");
        
        URL url=new URL("http://www.creaduweb.fr/");
        String host=url.getHost();
        String fileName="home";
        
        Path path= Files.createDirectory(new File(host).toPath());
        
        
        
        File file = new File(host+"/"+fileName);
        Document doc = Jsoup.connect("http://www.creaduweb.fr/").get();
        
         
        System.out.print(doc.toString());
        
        List<String> liste=new ArrayList<String>();
        liste.add(doc.toString());
        
    
        Files.write(file.toPath(),liste,StandardCharsets.UTF_8);
                
    }
    public String CreateDirectoryFromURL(URL url1)
    {        
        String directoryName=url1.toString().replace(url1.getProtocol()+"://", "./");
        if(directoryName.substring(directoryName.length()-1).equals("/"))
        {
                    directoryName=directoryName.substring(0,directoryName.length()-1);
        }
        File fb = new File(directoryName); 
        
        fb.mkdirs();
        
        return directoryName;
    }
    public void FillDataBaseWithHTMLPage(String HTMLPage)
    {
        
    }

}
