package restControllers.schoolStudent.schoolClass;

import acceptanceTest.schoolStudent.dto.SchoolClassDTO;
import acceptanceTest.schoolStudent.request.schoolClass.GetSchoolClassIdsRequest;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetSchoolClassIdsRestController {

    private static final String BASE_URL_SCHOOL_STUDENT_APP = "http://localhost:8093";

    public static Response execute(List<SchoolClassDTO> schoolClassDTOS) {
        return given()
                .baseUri(BASE_URL_SCHOOL_STUDENT_APP)
                .contentType("application/json")
                .body(GetSchoolClassIdsRequest.builder().schoolClassDTOS(schoolClassDTOS).build())
                .post("/school/student/api/getSchoolClassIds");
    }
}
