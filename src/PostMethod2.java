import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PostMethod2 {
    public static void main(String[] args) {
        //method 2:java.net.http.HttpClient
        HttpClient httpClient=HttpClient.newHttpClient();
        HttpRequest httpRequest=HttpRequest.newBuilder().uri(URI.create("https://jsonplaceholder.typicode.com/photos")).build();
        httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(Main::parse)
                .join();
    }
    public static String parse(String respondBody){
        JSONArray photos=new JSONArray(respondBody);
        for(int i=0; i<photos.length();i++){
            JSONObject photo=photos.getJSONObject(i);
            int albumId=photo.getInt("albumId");
            int id=photo.getInt("id");
            String title=photo.getString("title");
            String url=photo.getString("url");
            String thumbnailUrl=photo.getString("thumbnailUrl");
            System.out.println(id +" "+albumId +" "+title +" "+url +" "+thumbnailUrl);
        }return null;
    }
}
