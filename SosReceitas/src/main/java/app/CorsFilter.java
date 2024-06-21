package app;

import spark.Filter;
import spark.Request;
import spark.Response;

public class CorsFilter {
    private static final String ORIGIN = "http://127.0.0.1:5500"; // Substitua pela origem correta

    public static void apply() {
        spark.Spark.before((request, response) -> {
            response.header("Access-Control-Allow-Origin", ORIGIN);
            response.header("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
            response.header("Access-Control-Allow-Headers", "Content-Type,Authorization");
        });

        // Handle the OPTIONS method
        spark.Spark.options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });
    }
}
