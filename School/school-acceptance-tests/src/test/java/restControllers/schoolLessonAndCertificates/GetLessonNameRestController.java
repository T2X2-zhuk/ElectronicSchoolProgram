package restControllers.schoolLessonAndCertificates;

import acceptanceTest.schoolLessonsAndCertificates.request.GetLessonNameRequest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetLessonNameRestController {

    private static final String BASE_URL_SCHOOL_LESSONS_AND_CERTIFICATES = "http://localhost:8092";

    public static Response execute(String lessonName) {
        return given()
                .baseUri(BASE_URL_SCHOOL_LESSONS_AND_CERTIFICATES)
                .contentType("application/json")
                .body(GetLessonNameRequest.builder().lessonName(lessonName).build())
                .post("/lessonsAndCertificates/getLessonName");
    }
}
