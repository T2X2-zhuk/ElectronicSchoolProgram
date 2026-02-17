package restControllers.schoolStudent.student;

import acceptanceTest.schoolStudent.dto.SchoolClassDTO;
import acceptanceTest.schoolStudent.request.student.TransferStudentToNewClassRequest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TransferStudentToANewClassRestController {

    private static final String BASE_URL_SCHOOL_STUDENT_APP = "http://localhost:8093";

    public static Response execute(String email, SchoolClassDTO schoolClassDTO) {
        return given()
                .baseUri(BASE_URL_SCHOOL_STUDENT_APP)
                .contentType("application/json")
                .body(TransferStudentToNewClassRequest.builder().email(email).schoolClassDTO(schoolClassDTO).build())
                .post("/school/student/api/transferStudent");
    }
}
