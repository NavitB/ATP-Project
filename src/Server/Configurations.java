package Server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configurations {
    private static Configurations conf = null;
    private Properties properties;

    private Configurations(){
        properties = new Properties();
        try (InputStream inputStream = Configurations.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);
            }
            else {
                System.out.println("file not found");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Configurations getConf()
    {
        if(conf==null)
        {
            conf = new Configurations();
        }
        return conf;
    }

    public String getNumOfThreads(){
        return properties.getProperty("threadPoolSize");
    }

    public String getGeneratingAlgo(){
        return properties.getProperty("mazeGeneratingAlgorithm");
    }

    public String getSearchingAlgo(){
        return properties.getProperty("mazeSearchingAlgorithm");
    }

}

