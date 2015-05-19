package cms_sio.xebparser;

import java.io.File;
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
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author ducruet.anthony
 */
public class WebParser {

    private void getConnecProxy() {
        final String authUser = "etudiant";
        final String authPassword = "secret";
        
        Authenticator.setDefault(
                new Authenticator() {
                    @Override
                    public PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                authUser, authPassword.toCharArray());
                    }
                }
        );

    }

    void getDocument(URL url) throws Exception {

        

        System.setProperty("http.proxyHost", "192.168.216.250");
        System.setProperty("http.proxyPort", "8080");

        URL urlAParser = new URL(url.toString());
        String host = urlAParser.getHost();
        String fileName = "home";

        Path path = Files.createDirectory(new File(host).toPath());
        File file = new File(host + "/" + fileName);
        Document doc = Jsoup.connect(urlAParser.toString()).get();
        //String title = doc.title();
        System.out.print(doc.toString());
        List<String> maListeUrl = getUrl(doc);
        List<String> listeUrlParsed = new ArrayList<String>();
        List<String> liste = new ArrayList<String>();
        liste.add(doc.toString());
        Files.write(file.toPath(), liste, StandardCharsets.UTF_8);
        for (String uneUrl : maListeUrl) {

            if (!listeUrlParsed.contains(uneUrl)) {
                URL monUrl = new URL(uneUrl);
                getDocument(monUrl);
                listeUrlParsed.add(monUrl.toString());

            }

        }

    }

    private List<String> getUrl(Document doc) {
        List<String> urlUrl = new ArrayList();
        Element content = doc.getElementById("content");
        Elements links = content.getElementsByTag("a");
        for (Element link : links) {

            String linkHref = link.attr("href");
            String linkText = link.text();
            System.out.print(linkText.toString());
            urlUrl.add(linkText);

        }
        return urlUrl;

    }

}
