package restControllers.schoolStudent.schoolClass;

import acceptanceTest.schoolStudent.dto.SchoolClassDTO;
import acceptanceTest.schoolStudent.request.schoolClass.CreateSchoolClassRequest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CreateSchoolClassRestController {

    private static final String BASE_URL_SCHOOL_STUDENT_APP = "http://localhost:8093";

    public static Response execute(SchoolClassDTO schoolClassDTO) {
        return given()
                .baseUri(BASE_URL_SCHOOL_STUDENT_APP)
                .contentType("application/json")
                .body(CreateSchoolClassRequest.builder().schoolClassDTO(schoolClassDTO).build())
                .post("/school/student/api/createSchoolClass");
    }
}
