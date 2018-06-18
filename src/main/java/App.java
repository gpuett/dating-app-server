import com.google.gson.Gson;
import dao.Sql2oUserDao;
import exceptions.ApiException;
import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    private static boolean isProduction = false;

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            isProduction = true;
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }

    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");
        Sql2oUserDao userDao;
        Connection conn;
        Gson gson = new Gson();
        Sql2o sql2o;
        if(isProduction) {
            String connectionString = "jdbc:postgresql://"; // Heroku credentials
            sql2o = new Sql2o(connectionString, "", ""); // Heroku credentials
        } else {
            String connectionString = "jdbc:postgresql://localhost:5432/dating_app";
            sql2o = new Sql2o(connectionString, null, null);
        }
        userDao = new Sql2oUserDao(sql2o);
        conn = sql2o.open();

        post("/users/new", "application/json", (request, response) -> {
            User user = gson.fromJson(request.body(), User.class);
            userDao.add(user);
            response.status(201);
            return gson.toJson(user);
        });

        get("/users", "application/json", (request, response) -> {
           System.out.println(userDao.getAll());
           List<User> allUsers = userDao.getAll();
            if(allUsers.size() > 0 ) {
                return gson.toJson(allUsers);
            } else {
                return "{\"message\":\"I'm sorry, but no users are currently listed in the database.\"}";
            }
        });

        get("/users/:id", "application/json", (request, response) -> {
            int userId = Integer.parseInt(request.params("id"));

            User userToFind = userDao.findById(userId);

            if(userToFind == null) {
                throw new ApiException(404, String.format("No user with the id: '%s' exists", request.params("id")));
            }

            return gson.toJson(userToFind);
        });

        post("/users/:id/update", "application/json", (request, response) -> {
           int userId = Integer.parseInt(request.params("id"));
           User updatedUser = gson.fromJson(request.body(), User.class);
           userDao.update(userId, updatedUser.getName(), updatedUser.getBio(), updatedUser.getAge(), updatedUser.getOrientation(), updatedUser.getImageUrl(), updatedUser.getInterests());
           response.status(201);
           return gson.toJson(userDao.findById(userId));
        });

        post("/users/:id/delete", "application/json", (request, response) -> {
            int userId = Integer.parseInt(request.params("id"));
            userDao.deleteById(userId);
            return "{\"message\":\"User deleted\"}";
        });



        //FILTERS
        exception(ApiException.class, (exception, request, response) -> {
            ApiException err = (ApiException) exception;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            response.type("application/json");
            response.status(err.getStatusCode());
            response.body(gson.toJson(jsonMap));
        });


        after((request, response) -> {
            response.type("application/json");
        });

    }

}
