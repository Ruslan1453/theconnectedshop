package theconnectedshop.tests;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Apitest {

    @Test
    void createPostTest() {
        RestAssured.baseURI = "https://dev.emeli.in.ua/wp-json/wp/v2";

        String jsonBody = """
                {
                    "title": "new title",
                    "content": "new content",
                    "status": "publish"
                }
                """;
        String username = "admin";
        String password = "Engineer_123";

        Response response = RestAssured
                .given()
                .auth().preemptive().basic(username, password)
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .extract()
                .response();

        System.out.println("Response:");
        System.out.println(response.asString());
    }

    @Test
    void createDraftPostTest() {
        RestAssured.baseURI = "https://dev.emeli.in.ua/wp-json/wp/v2";

        String jsonBody = """
                {
                    "title": "draft post",
                    "content": "this is draft content",
                    "status": "draft"
                }
                """;
        String username = "admin";
        String password = "Engineer_123";

        Response response = RestAssured
                .given()
                .auth().preemptive().basic(username, password)
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .extract()
                .response();

        System.out.println("Response (draft):");
        System.out.println(response.asString());
    }

    @Test
    void createPendingPostTest() {
        RestAssured.baseURI = "https://dev.emeli.in.ua/wp-json/wp/v2";

        String jsonBody = """
                {
                    "title": "pending post",
                    "content": "this post is waiting for review",
                    "status": "pending"
                }
                """;
        String username = "admin";
        String password = "Engineer_123";

        Response response = RestAssured
                .given()
                .auth().preemptive().basic(username, password)
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .extract()
                .response();

        System.out.println("Response (pending):");
        System.out.println(response.asString());
    }

    @Test
    void createFuturePostTest() {
        RestAssured.baseURI = "https://dev.emeli.in.ua/wp-json/wp/v2";

        
        String futureDate = java.time.LocalDateTime.now().plusDays(1).toString();

        String jsonBody = """
                {
                    "title": "future post",
                    "content": "this post will be published in future",
                    "status": "future",
                    "date": "2025-10-31T16:35:03"
                }
                """.formatted(futureDate);

        String username = "admin";
        String password = "Engineer_123";

        Response response = RestAssured
                .given()
                .auth().preemptive().basic(username, password)
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .extract()
                .response();

        System.out.println("Response (future):");
        System.out.println(response.asString());
    }
}
