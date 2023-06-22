import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Post {
    private  static HttpURLConnection connection;
    public static void main(String[] args) throws IOException {
        //method 1: java.net.HttpURLConnection
        BufferedReader reader;
        String line;
        StringBuffer respondContent=new StringBuffer();
        URL url=new URL("https://jsonplaceholder.typicode.com/photos");
        try {
            connection= (HttpURLConnection) url.openConnection();
            //request setUp
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status=connection.getResponseCode();
            //System.out.println(status);
            if(status>299){
                reader=new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line=reader.readLine())!=null){
                    respondContent.append(line);
                }
                reader.close();
            }else {
                reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line=reader.readLine())!=null){
                    respondContent.append(line);
                }
                reader.close();
            }
            System.out.println(respondContent.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        connection.disconnect();
    }
}
