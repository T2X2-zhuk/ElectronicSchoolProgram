package restControllers.schoolStudent.student;

import acceptanceTest.schoolStudent.dto.SchoolStudentDTO;
import acceptanceTest.schoolStudent.dto.SpecificCodeForRegistrationInElectronicSchoolDTO;
import acceptanceTest.schoolStudent.request.student.RegistrationStudentRequest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RegistrationStudentRestController {

    private static final String BASE_URL_SCHOOL_STUDENT_APP = "http://localhost:8093";

    public static Response execute(SchoolStudentDTO schoolStudentDTO, SpecificCodeForRegistrationInElectronicSchoolDTO specificCodeForRegistrationInElectronicSchoolDTO) {
        return given()
                .baseUri(BASE_URL_SCHOOL_STUDENT_APP)
                .contentType("application/json")
                .body(RegistrationStudentRequest.builder().schoolStudentDTO(schoolStudentDTO).specificCodeForRegistrationInElectronicSchoolDTO(specificCodeForRegistrationInElectronicSchoolDTO).build())
                .post("/school/student/api/saveStudent");
    }
}
