import spark.Request;
import spark.Response;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class UserController {
    private static HttpURLConnection con;
    private static final int HTTP_NOT_FOUND = 404;

    public static Object getAll(Request request, Response response, UserData userData) {
        return userData.getAll();
    }


    public static Object getUser(Request request, Response response, UserData userData) {
        try {
            int id = Integer.valueOf(request.params("id"));
            User user = userData.get(id);

            if (user == null)
                throw new Exception("No user found with id " + id);

            return user;
        } catch(Exception e) {
            response.status(HTTP_NOT_FOUND);
            return new ErrorMessage(e);
        }
}
    public static Object createUser(Request req, Response res, UserData userData) {
        User user = JsonTransformer.fromJson(req.body(), User.class);
        userData.createUser(user);
        return "ok";
    }
    public static Object updateUser(Request req, Response res, UserData userData) {
        try {
            User user = JsonTransformer.fromJson(req.body(), User.class);
            int id = Integer.valueOf(req.params("id"));
            if (userData.get(id) == null) {
                throw new Exception("No user found with id " + req.params("id"));
            }
            userData.updateUser(id, user);
            return user;
        } catch(Exception e) {
            res.status(HTTP_NOT_FOUND);
            return new ErrorMessage(e);
        }
    }

    public static Object deleteUser(Request req, Response res, UserData userData) {
        try {
        int id = Integer.valueOf(req.params("id"));
        User user = userData.get(id);

            if (user == null)
                throw new Exception("No user found with id " + id);
        userData.deleteUser(id);
        return "User with id " + id + " deleted";

        } catch(Exception e) {
            res.status(HTTP_NOT_FOUND);
            return new ErrorMessage(e);
        }
    }

    public static Object getVehicles(Request req, Response res,UserData userData)throws IOException {

        String url = "http://5.20.139.104:81/api/vehicles";
        final String USER_AGENT = "Mozilla/5.0";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
        new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());
        return response;

    }


    }
