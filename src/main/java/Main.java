import spark.Request;
import spark.Response;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        UserData userData = new UserData();
        get("/user", (Request req, Response res) -> {
            return UserController.getAll(req, res, userData);
        }, new JsonTransformer());

        post("/user", (req, res) -> {
            return UserController.createUser(req, res, userData);
        }, new JsonTransformer());

        put("/user/:id", (req, res) -> {
            return UserController.updateUser(req, res, userData);
        }, new JsonTransformer());

        delete("/user/:id", (req, res) -> {
            return UserController.deleteUser(req, res, userData);
        }, new JsonTransformer());

        get("/user/:id", (req, res) -> {
            return UserController.getUser(req, res, userData);
        }, new JsonTransformer());
}
}