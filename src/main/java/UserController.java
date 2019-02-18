import com.google.gson.JsonArray;
import org.eclipse.jetty.websocket.api.SuspendToken;
import spark.Request;
import spark.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;


public class UserController {
    private static HttpURLConnection con;
    private static final int HTTP_NOT_FOUND = 404;
    private static final int Created = 201;
    private static final int OK = 200;


    public static Object getAll(Request request, Response response, UserData userData) {
        return userData.getAll();
    }

    public static Object searchUserName(Request request, Response response, UserData userData) {
        try {
            String reqName=request.params("name");
            String names = "";
            User user = userData.get(1);
            for (int i = 1; i <= userData.getSize(); i++) {
                user = userData.get(i);
                if (user.getName().contains(reqName)){
                    names+=user.getName() + " ";
                }
            }
            if (names.equals(""))
                throw new Exception("No user name found with this" + reqName);
            return names;
        } catch(Exception e) {
            response.status(HTTP_NOT_FOUND);
            return new ErrorMessage(e);
        }
    }

    public static Object searchUserNameObject(Request request, Response response, UserData userData) {
        try {
            ArrayList<User> usersArray = new ArrayList<>();
            String reqName=request.params("name");
            String names = "";
            User[] userArray = new User[1];
            User user = userData.get(1);
            for (int i = 1; i <= userData.getSize(); i++) {
                user = userData.get(i);
                if (user.getName().contains(reqName)){
                    names+=user.getName() + " ";
                    usersArray.add(user);
                }
            }

            if (names.equals(""))
                throw new Exception("No user name found with this " + reqName);
            return usersArray;
        } catch(Exception e) {
            response.status(HTTP_NOT_FOUND);
            return new ErrorMessage(e);
        }
    }

    public static Object getUser(Request request, Response response, UserData userData) {
        try {
            int id = Integer.valueOf(request.params("id"));
            User user = userData.get(id);

            if (user == null)
                throw new Exception("No user found with id " + id);
            response.status(OK);
            return user;
        } catch(Exception e) {
            response.status(HTTP_NOT_FOUND);
            return new ErrorMessage(e);
        }
}
    public static Object createUser(Request req, Response res, UserData userData) {

        try {
            User user = JsonTransformer.fromJson(req.body(), User.class);
            if (user.getName().equals(null) || user.getLastName().equals(null)){
                Exception e;
            }

            userData.createUser(user);

        }
        catch(Exception e) {
            res.status(HTTP_NOT_FOUND);
            return new ErrorMessage(e);
        }
        res.status(Created);
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
            res.status(Created);
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
            res.status(OK);
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
