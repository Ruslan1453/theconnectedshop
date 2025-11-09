package theconnectedshop.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.junit5.AllureJunit5;
import io.restassured.RestAssured;
import io.restassured.response.Response;

@Epic("WordPress Posts API")
@Feature("Полный CRUD с Allure")
@ExtendWith(AllureJunit5.class) 
public class Apitest {

    private final String username = "admin";
    private final String password = "Engineer_123";

    private int postId;

    @BeforeEach
    @SuppressWarnings("unused")
    void setup() {
       
        RestAssured.baseURI = "https://dev.emeli.in.ua/wp-json/wp/v2";
    }

   

    @Step("Создаём пост")
    private Response createPost() {

        String jsonBody = """
                {
                    "title": "Allure CRUD Test Post",
                    "content": "This post is created by Allure CRUD test",
                    "status": "publish"
                }
                """;

        Response response = RestAssured
                .given()
                    .auth().preemptive().basic(username, password)
                    .header("Content-Type", "application/json")
                    .body(jsonBody)
                    .log().all()
                .when()
                    .post("/posts")
                .then()
                    .log().all()
                    .statusCode(201)
                    .extract().response();

        postId = response.jsonPath().getInt("id");
        Allure.addAttachment("Create Response", response.asString());

        return response;
    }

    @Step("Получаем пост по ID {postId}")
    private Response getPost() {

        Response response = RestAssured
                .given()
                    .auth().preemptive().basic(username, password)
                    .log().all()
                .when()
                    .get("/posts/" + postId)
                .then()
                    .log().all()
                    .statusCode(200)
                    .extract().response();

        Allure.addAttachment("Get Response", response.asString());
        return response;
    }

    @Step("Обновляем пост {postId}")
    private Response updatePost() {

        String jsonBody = """
                {
                    "title": "Updated Allure CRUD Post",
                    "content": "Updated content for Allure CRUD test"
                }
                """;

        Response response = RestAssured
                .given()
                    .auth().preemptive().basic(username, password)
                    .header("Content-Type", "application/json")
                    .body(jsonBody)
                    .log().all()
                .when()
                    .put("/posts/" + postId)
                .then()
                    .log().all()
                    .statusCode(200)
                    .extract().response();

        Allure.addAttachment("Update Response", response.asString());
        return response;
    }

    @Step("Удаляем пост {postId}")
    private Response deletePost() {

        Response response = RestAssured
                .given()
                    .auth().preemptive().basic(username, password)
                    .queryParam("force", true)
                    .log().all()
                .when()
                    .delete("/posts/" + postId)
                .then()
                    .log().all()
                    .statusCode(200)
                    .extract().response();

        Allure.addAttachment("Delete Response", response.asString());
        return response;
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Полный CRUD: создание, чтение, обновление и удаление поста")
    void fullCrudTest() {
        // CREATE
        Response createResp = createPost();
        Assertions.assertEquals(
                "publish",
                createResp.jsonPath().getString("status"),
                "Post status should be 'publish' after creation"
        );

        // GET
        Response getResp = getPost();
        Assertions.assertEquals(
                postId,
                getResp.jsonPath().getInt("id"),
                "Loaded post id should match created post id"
        );

        // UPDATE
        Response updateResp = updatePost();
        Assertions.assertTrue(
                updateResp.jsonPath().getString("title.rendered")
                        .contains("Updated Allure CRUD Post"),
                "Updated title should contain 'Updated Allure CRUD Post'"
        );

        // DELETE
Response deleteResp = deletePost();


Boolean deleted = deleteResp.jsonPath().getBoolean("deleted");
Assertions.assertNotNull(deleted, "Delete response should contain 'deleted' flag");
Assertions.assertTrue(deleted, "Post should be marked as deleted");


int deletedId = deleteResp.jsonPath().getInt("previous.id");
Assertions.assertEquals(postId, deletedId, "Deleted post id should match created post id");

Assertions.assertEquals(postId, deletedId,
        "Deleted post id (previous.id) should match created post id");

        
    }
}
