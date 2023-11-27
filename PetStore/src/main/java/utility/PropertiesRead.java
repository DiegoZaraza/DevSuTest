package utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesRead {
    private Properties environment;

    public void getPropertiesFile(String propertiesURL) {
        setEnvironment();
        ClassLoader loader = getClass().getClassLoader();
        try {
            InputStream inputEnvironment = loader.getResourceAsStream(propertiesURL);
            getEnvironment().load(inputEnvironment);
        } catch (IOException exc) {
            throw new RuntimeException("File not found", exc);
        }
    }

    public Properties getEnvironment() {
        return environment;
    }

    public void setEnvironment(){
        this.environment = new Properties();
    }

    public String returnProperties(String data){
        return environment.getProperty(data);
    }
}
