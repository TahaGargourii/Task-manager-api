package com.gargouri.TaskManager;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskManagerApiTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void testCreateAndRetrieveTask() {
        // Create a task
        String taskId = given()
                .contentType("application/json")
                .body("{\"title\":\"API Test Task\",\"description\":\"API Test Description\",\"completed\":false}")
                .when()
                .post("/api/tasks")
                .then()
                .statusCode(201)
                .extract().jsonPath().getString("id");

        // Retrieve the task
        get("/api/tasks/" + taskId)
                .then()
                .statusCode(200)
                .body("title", equalTo("API Test Task"));
    }

}