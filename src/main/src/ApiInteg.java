import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class ApiInteg{
    public static void getTrips(int originID, int destID, String date, String time) {
        try {
            URL request = new URL("http://xmlopen.rejseplanen.dk/bin/rest.exe/trip?originId=" + originID + "&destId=" + destID + "&date=" +
                    date + "&time=" + time +
                    "&useBus=0&format=json\n");
            HttpURLConnection connec = (HttpURLConnection) request.openConnection();
            connec.setRequestMethod("GET");
            int status = connec.getResponseCode();
            StringBuffer content = null;
            if (status != 200) {
                System.out.println("Something went wrong");
            } else {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(connec.getInputStream()));
                String inputLine;
                content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
            }
            connec.disconnect();
            Map<String, Object> json = PrintJson.parseJson(content);
            PrintJson.printJson(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}