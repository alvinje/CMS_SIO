/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms_sio.WebParser;

import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
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
        Document doc = Jsoup.connect("http://www.creaduweb.fr/").get();
        
        
        System.out.print(doc.toString());
        
        
        
    }

}
