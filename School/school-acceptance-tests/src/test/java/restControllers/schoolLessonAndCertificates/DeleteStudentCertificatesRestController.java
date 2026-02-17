package restControllers.schoolLessonAndCertificates;
import acceptanceTest.schoolLessonsAndCertificates.request.DeleteStudentCertificatesRequest;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class DeleteStudentCertificatesRestController {

    private static final String BASE_URL_SCHOOL_LESSONS_AND_CERTIFICATES = "http://localhost:8092";

    public static Response execute(List<Long> studentIds) {
        return given()
                .baseUri(BASE_URL_SCHOOL_LESSONS_AND_CERTIFICATES)
                .contentType("application/json")
                .body(DeleteStudentCertificatesRequest.builder().studentIds(studentIds).build())
                .post("/lessonsAndCertificates/deleteStudentCertificates");
    }
}
