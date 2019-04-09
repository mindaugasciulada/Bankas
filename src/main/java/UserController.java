import com.google.gson.Gson;
import com.google.gson.JsonObject;

import com.google.gson.JsonParser;

import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HTTP;

import org.json.JSONException;
import org.json.JSONObject;
import spark.Request;
import spark.Response;


import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


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


    public static Object searchUserBalanceObject(Request request, Response response, UserData userData) {
        try {
            ArrayList<User> usersArray = new ArrayList<>();
            int reqname = Integer.valueOf(request.params("balance"));
//            int reqName=request.params("balance");
            String names = "";

            User[] userArray = new User[1];
            User user = userData.get(1);
            for (int i = 1; i <= userData.getSize(); i++) {
                user = userData.get(i);
                if (user.getBalance()== reqname){
                    names+=user.getName() + " ";

                    usersArray.add(user);
                }
            }

            if (names.equals(""))
                throw new Exception("No user name found with this " + reqname);
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
















//    public static Object getVehicles(Request req, Response res, UserData userData)  {
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//        HttpUriRequest getRequest = new HttpGet("http://172.30.1.140/api/courses");
//        getRequest.addHeader(HttpHeaders.ACCEPT, "application/json");
//        String content = "";
//        try (CloseableHttpResponse httpResponse = httpClient.execute(getRequest)) {
//            content = EntityUtils.toString(httpResponse.getEntity());
//
//            int statusCode = httpResponse.getStatusLine().getStatusCode();
//            System.out.println("statusCode = " + statusCode);
//            System.out.println("content = " + content);
//
//            JsonObject jsonObject =  HTTP.toJSONObject(content.toString());
//        } catch (IOException e) {
//            //handle exception
//        }
//
//        return content;
//    }

//
//    //1. Convert object to JSON string
//    Gson gson = new Gson();
//    String json = gson.toJson(staff);
//        System.out.println(json);

    public static Object getVehicles(Request req, Response res,UserData userData) throws IOException, JSONException {

        String result="";

            HttpClient httpClient = HttpClientBuilder.create().build();

            HttpGet getRequest = new HttpGet("http://localhost:5000/users/1");
            HttpResponse response = httpClient.execute(getRequest);

            // Check for HTTP response code: 200 = success
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
            }
            // Get-Capture Complete application/xml body response
            BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
            String output;
            while ((output = br.readLine()) != null) {
                  result+=output;
            }
            User user = JsonTransformer.fromJson(result, User.class);
            user.setBalance(user.getBalance() - 20);
            userData.updateUser(user.getId(),user);

       return user;
    }


    public static Object modifyCourses(Request req, Response res,UserData userData) throws IOException, JSONException {

        String result="";

        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet getRequest = new HttpGet("http://localhost:5000/users/1");
        HttpResponse response = httpClient.execute(getRequest);

        // Check for HTTP response code: 200 = success
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
        }
        // Get-Capture Complete application/xml body response
        BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
        String output;
        while ((output = br.readLine()) != null) {
            result+=output;
        }
        User user = JsonTransformer.fromJson(result, User.class);
        user.setBalance(user.getBalance() - 20);
//      user.setCourse("modifyCoursesedit");
        userData.updateUser(user.getId(),user);

        return user;
    }
    public static Object addCourse(Request req, Response res,UserData userData) throws IOException, JSONException {

        String result="";
        User userREQ = JsonTransformer.fromJson(req.body(), User.class);
        User user = userData.get(userREQ.getId());

//        try {
//            int id = Integer.valueOf(req.params("id"));
//            if (userData.get(id) == null) {
//                throw new Exception("No user found with id " + req.params("id"));
//            }
//
//        } catch(Exception e) {
//            res.status(HTTP_NOT_FOUND);
//            return new ErrorMessage(e);
//        }

        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet getRequest = new HttpGet("http://172.30.1.140/api/courses/"+userREQ.getBuy());
        HttpResponse response = httpClient.execute(getRequest);

        // Check for HTTP response code: 200 = success
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
        }
        // Get-Capture Complete application/xml body response
        BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
        String output;
        while ((output = br.readLine()) != null) {
            result+=output;
        }
        respa respaa = JsonTransformer.fromJson(result, respa.class);
        user.setBalance(user.getBalance()-respaa.getPrice());
        user.setCourse(respaa);
        userData.updateUser(user.getId(),user);

        return user;
    }


}