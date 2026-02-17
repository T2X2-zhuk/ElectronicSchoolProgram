package restControllers.schoolLessonAndCertificates;

import acceptanceTest.schoolLessonsAndCertificates.request.CreateCertificatesRequest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CreateCertificatesRestController {

    private static final String BASE_URL_SCHOOL_LESSONS_AND_CERTIFICATES = "http://localhost:8092";

    public static Response execute(Long studentId,Long schoolClassId) {
        return given()
                .baseUri(BASE_URL_SCHOOL_LESSONS_AND_CERTIFICATES)
                .contentType("application/json")
                .body(CreateCertificatesRequest.builder().studentId(studentId).schoolClassId(schoolClassId).build())
                .post("/lessonsAndCertificates/createCertificates");
    }
}
