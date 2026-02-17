package restControllers.schoolStudent.teacher;

import acceptanceTest.schoolStudent.dto.SpecificCodeForRegistrationInElectronicSchoolDTO;
import acceptanceTest.schoolStudent.dto.TeacherDTO;
import acceptanceTest.schoolStudent.request.teacher.RegistrationTeacherRequest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RegistrationTeacherRestController {

    private static final String BASE_URL_SCHOOL_STUDENT_APP = "http://localhost:8093";

    public static Response execute(TeacherDTO teacherDTO, SpecificCodeForRegistrationInElectronicSchoolDTO specificCodeForRegistrationInElectronicSchoolDTO) {
        return given()
                .baseUri(BASE_URL_SCHOOL_STUDENT_APP)
                .contentType("application/json")
                .body(RegistrationTeacherRequest.builder().teacherDTO(teacherDTO).specificCodeForRegistrationInElectronicSchoolDTO(specificCodeForRegistrationInElectronicSchoolDTO).build())
                .post("/school/student/api/registrationTeacher");
    }
}
