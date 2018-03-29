import spark.Request;
import spark.Response;


public class UserController {

    public static Object getAll(Request request, Response response, UserData userData) {
        return userData.getAll();
    }
        public static Object getUser(Request request, Response response, UserData userData) {
                int id = Integer.valueOf(request.params("id"));
                User user = userData.get(id);
             return user;
        }
    public static Object createUser(Request req, Response res, UserData userData) {
        User user = JsonTransformer.fromJson(req.body(), User.class);
        userData.createUser(user);
        return "ok";
    }
    public static Object updateUser(Request req, Response res, UserData userData) {
        User user = JsonTransformer.fromJson(req.body(), User.class);
        int id = Integer.valueOf(req.params("id"));
        userData.updateUser(id,user);
        return "ok";
    }
    public static Object deleteUser(Request req, Response res, UserData userData) {
        User user = JsonTransformer.fromJson(req.body(), User.class);
        int id = Integer.valueOf(req.params("id"));
        userData.deleteUser(id);
        return "ok";
    }

}
