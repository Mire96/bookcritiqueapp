/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Phenom
 */
public class SettingsLoader {
    private static SettingsLoader instance;
    private Properties prop;

    private SettingsLoader() throws IOException{
        loadProperties();
    }

    public static SettingsLoader getInstance() throws IOException {
        if(instance == null)
            instance = new SettingsLoader();
        return instance;
    }

    private void loadProperties() throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream("db.properties");
        prop = new Properties();
        prop.load(fis);
    }
    
    public String getValue(String key){
        return prop.getProperty(key);
    }
    
    public void setValue(String key, String value){
        prop.setProperty(key, value);
    }
    
    public void saveProperties() throws FileNotFoundException, IOException{
        FileOutputStream fr = new FileOutputStream("db.properties");
        prop.store(fr, "Properties");
        fr.close();
    }
    
    
    
    
}
