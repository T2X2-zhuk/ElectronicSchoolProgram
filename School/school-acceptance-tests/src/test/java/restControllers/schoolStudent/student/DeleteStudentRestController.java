package restControllers.schoolStudent.student;

import acceptanceTest.schoolStudent.request.student.DeleteStudentsRequest;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class DeleteStudentRestController {

    private static final String BASE_URL_SCHOOL_STUDENT_APP = "http://localhost:8093";

    public static Response execute(List<String> emails) {
        return given()
                .baseUri(BASE_URL_SCHOOL_STUDENT_APP)
                .contentType("application/json")
                .body(DeleteStudentsRequest.builder().emails(emails).build())
                .post("/school/student/api/deleteStudent");
    }
}
