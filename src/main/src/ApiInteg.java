import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.nio.Buffer;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.ByteArrayInputStream;

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
            String q = content.toString();
            System.out.println(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*public static void xmlParser(String xmlFile) {
        try {
            // Create a DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parse the XML string and get the Document object
            String xmlString = xmlFile;
            Document doc = builder.parse(new ByteArrayInputStream(xmlString.getBytes()));

            // Get the root element
            Node root = doc.getDocumentElement();

            // Get all child nodes of the root element
            NodeList nodes = root.getChildNodes();

            // Iterate through the nodes and print their names
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                System.out.println(node.getNodeName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}