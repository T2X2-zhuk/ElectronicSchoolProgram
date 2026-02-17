package restControllers.schoolLessonAndCertificates;

import acceptanceTest.schoolLessonsAndCertificates.request.CleanSchoolLessonsAndCertificatesDbRequest;
import acceptanceTest.schoolLessonsAndCertificates.request.CreateCertificatesRequest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CleanSchoolLessonsAndCertificatesDbRestController {

    private static final String BASE_URL_SCHOOL_LESSONS_AND_CERTIFICATES = "http://localhost:8092";

    public static Response execute(boolean cleanCertificateDb,boolean cleanClassLessonDb,boolean cleanLessonDb) {
        return given()
                .baseUri(BASE_URL_SCHOOL_LESSONS_AND_CERTIFICATES)
                .contentType("application/json")
                .body(CleanSchoolLessonsAndCertificatesDbRequest.builder()
                        .cleanCertificateDb(cleanCertificateDb)
                        .cleanClassLessonDb(cleanClassLessonDb)
                        .cleanLessonDb(cleanLessonDb).build())
                .post("/lessonsAndCertificates/cleanDb");
    }

}
