import spark.Request;
import spark.Response;
//create user turi but i users o ne /user
//headers pridet location ir json
import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        port(5000);
        UserData userData = new UserData();

        get("/users", (Request req, Response res) -> {
            res.type("application/json");
            return UserController.getAll(req, res, userData);
        }, new JsonTransformer());

        get("/names/:name", (req, res) -> {
            res.type("application/json");
            return UserController.searchUserName(req, res, userData);
        }, new JsonTransformer());

        get("/usernames/:name", (req, res) -> {
            res.type("application/json");
            return UserController.searchUserNameObject(req, res, userData);
        }, new JsonTransformer());

        post("/users", (req, res) -> {
            res.type("application/json");
            return UserController.createUser(req, res, userData);
        }, new JsonTransformer());

        put("/users/:id", (req, res) -> {
            res.type("application/json");
            return UserController.updateUser(req, res, userData);
        }, new JsonTransformer());

        delete("/users/:id", (req, res) -> {
            res.type("application/json");
            return UserController.deleteUser(req, res, userData);
        }, new JsonTransformer());

        get("/users/name/:name", (req, res) -> {
            res.type("application/json");
            return UserController.getUserNames(req, res, userData);
        }, new JsonTransformer());


        get("/users/:id", (req, res) -> {
            res.type("application/json");
            return UserController.getUser(req, res, userData);
        }, new JsonTransformer());

        get("/ss", (req, res) -> {
            return UserController.getVehicles(req,res,userData);
        }, new JsonTransformer());
}
//prideti statusus create 401 +
//tuscia pridet negalima poste +
//paieska padaryt pagal varda
}