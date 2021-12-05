package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadersForConfigs {

    public static Properties properties;

    static String FilePath=System.getProperty("user.dir")+"/src/test/resources/person.properties.properties";

    public static String getPropertiesValue(String Key){

        try {

            FileInputStream fileInputStream = new FileInputStream(FilePath);
            properties = new Properties();
            properties.load(fileInputStream);
            fileInputStream.close();
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());


        }catch (IOException i){
            System.out.println(i.getMessage());
        }
        return properties.getProperty(Key);

    }
}
