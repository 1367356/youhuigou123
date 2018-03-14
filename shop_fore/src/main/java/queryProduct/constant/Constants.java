package queryProduct.constant;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Constants {

    public String URL;
    public Constants() {
        InputStream in = this.getClass().getResourceAsStream("/config.properties");
        Properties properties = new Properties();
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        URL = properties.getProperty("backgroundURL");
    }
}
