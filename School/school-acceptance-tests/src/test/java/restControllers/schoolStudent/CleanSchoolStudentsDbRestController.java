package restControllers.schoolStudent;

import acceptanceTest.schoolLessonsAndCertificates.request.CleanSchoolLessonsAndCertificatesDbRequest;
import acceptanceTest.schoolStudent.request.CleanSchoolStudentDbRequest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CleanSchoolStudentsDbRestController {

    private static final String BASE_URL_SCHOOL_STUDENT_APP = "http://localhost:8093";

    public static Response execute(boolean cleanSchoolClass,boolean cleanSchoolStudent,boolean cleanTeacher,
    boolean cleanSpecificCodeForRegistrationInElectronicSchool) {
        return given()
                .baseUri(BASE_URL_SCHOOL_STUDENT_APP)
                .contentType("application/json")
                .body(CleanSchoolStudentDbRequest.builder()
                        .cleanSchoolClass(cleanSchoolClass)
                        .cleanSchoolStudent(cleanSchoolStudent)
                        .cleanTeacher(cleanTeacher)
                        .cleanSpecificCodeForRegistrationInElectronicSchool(cleanSpecificCodeForRegistrationInElectronicSchool).build())
                .post("/school/student/api/cleanDb");
    }

}
