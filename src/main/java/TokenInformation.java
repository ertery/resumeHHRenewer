import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Ertery on 13.02.2016.
 */
public class TokenInformation {
    public static ArrayList<String> getProperties(String fileName) {

        Properties prop = new Properties();
        InputStream input = null;
        ArrayList<String> propertyList =  new ArrayList<String>();

        File file = new File(fileName);
        if (!file.exists())
        {
            System.out.println(file.getAbsolutePath() + " doesn't exists. Exit.");
            System.exit(1);
        }

        try {

            input = new FileInputStream(fileName);

            prop.load(input);

            propertyList.add(prop.getProperty("token"));
            propertyList.add(prop.getProperty("resume_id"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return propertyList;
    }

}

