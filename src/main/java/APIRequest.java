
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by Ertery on 13.02.2016.
 */
public class APIRequest {

    public static void sendPost(String resumeId, String authToken) throws Exception {
        String USER_AGENT = "cv_autoUPD/1.0 (golubin.andrew@gmail.ru)";

        String url = "https://api.hh.ru/resumes/" + resumeId + "/publish";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Authorization", "Bearer " + authToken);
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        for (Map.Entry<String, List<String>> r : con.getRequestProperties().entrySet()) {
            System.out.println(r.getKey() + r.getValue().toString());
        }

        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(true);


        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);


        if (responseCode == 429) {
            System.out.println("Резюме было обновлено менее, чем 4 часа назад");
        } else if (responseCode == 204)
            System.out.println("Резюме с id " + resumeId + " было успешно обновлено");

    }
}
