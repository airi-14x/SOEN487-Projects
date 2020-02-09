/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryconsole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

/**
 *
 * @author Airi
 */
public class HttpURLConnectionMain {
    public static void main(String[] args) throws JAXBException {
        try {
            URL url = new URL("http://localhost:8080/A1-LibraryService/webresources/LibraryRESTService/books");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "text/plain");
            
            if (conn.getResponseCode() != 200){
                throw new RuntimeException("Failed: HTTP error code : " + conn.getResponseCode());
            }
            
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String apiOutput = br.readLine();
            System.out.println(apiOutput);
            conn.disconnect();
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(HttpURLConnectionMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(HttpURLConnectionMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
