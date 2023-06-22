import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    private static HttpURLConnection connection;
    public static void main(String[] args) throws IOException {
        //System.out.println("Hello world!");
        //Method 1: java.net.HttpURLConnection
        BufferedReader reader;
        String line;
        StringBuffer responseContent=new StringBuffer();
        URL url=new URL("https://jsonplaceholder.typicode.com/photos");
        connection= (HttpURLConnection) url.openConnection();

        //request stUp
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        int status=connection.getResponseCode();
        //System.out.println(status);
        if(status>299){
            reader=new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            while ((line=reader.readLine())!=null){
                responseContent.append(line);
            }
            reader.close();
        }else {
            reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line=reader.readLine())!=null){
                responseContent.append(line);
            }
            reader.close();
        }
        //System.out.print(responseContent.toString());
        //connection.disconnect();
        parse(responseContent.toString());
    }
    public static String parse(String respondBody){
        JSONArray photos=new JSONArray(respondBody);
        for(int i=0; i<photos.length();i++){
            JSONObject photo=photos.getJSONObject(i);
            int id=photo.getInt("id");
            int albumId=photo.getInt("albumId");
            String title=photo.getString("title");
            String url=photo.getString("url");
            String thumbnailUrl=photo.getString("thumbnailUrl");
            System.out.println(id +" "+albumId +" "+title +" "+url +" "+thumbnailUrl);
        }return null;
    }
}