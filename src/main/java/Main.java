import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        ArrayList<String> propertiesList = TokenInformation.getProperties("config.properties");
        final String  token = propertiesList.get(0);
        final String resume_id = propertiesList.get(1);

        Thread t = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        APIRequest.sendPost(resume_id, token);
                    } catch (Exception e) {
                        System.out.println("ERROR during send Post request");
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(4 * 60 * 1000 + 1000);
                    } catch (InterruptedException e) {
                        System.out.println("The process was interrupted");
                        e.printStackTrace();
                    }
                }
            }
        });
        t.run();
    }

}
