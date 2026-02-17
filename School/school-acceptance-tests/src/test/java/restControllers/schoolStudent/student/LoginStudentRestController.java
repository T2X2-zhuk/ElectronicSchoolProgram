package restControllers.schoolStudent.student;

import acceptanceTest.schoolStudent.request.student.LoginStudentRequest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LoginStudentRestController {

    private static final String BASE_URL_SCHOOL_STUDENT_APP = "http://localhost:8093";

    public static Response execute(String password) {
        return given()
                .baseUri(BASE_URL_SCHOOL_STUDENT_APP)
                .contentType("application/json")
                .body(LoginStudentRequest.builder().password(password).build())
                .post("/school/student/api/loginStudent");
    }
}
