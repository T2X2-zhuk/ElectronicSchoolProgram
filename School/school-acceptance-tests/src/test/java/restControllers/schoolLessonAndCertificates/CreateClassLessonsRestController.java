package restControllers.schoolLessonAndCertificates;

import acceptanceTest.schoolLessonsAndCertificates.dto.LessonDTO;
import acceptanceTest.schoolLessonsAndCertificates.request.CreateClassLessonsRequest;
import acceptanceTest.schoolStudent.dto.SchoolClassDTO;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class CreateClassLessonsRestController {

    private static final String BASE_URL_SCHOOL_LESSONS_AND_CERTIFICATES = "http://localhost:8092";

    public static Response execute(List<SchoolClassDTO> schoolClassDTOS,LessonDTO lessonDTO) {
        return given()
                .baseUri(BASE_URL_SCHOOL_LESSONS_AND_CERTIFICATES)
                .contentType("application/json")
                .body(CreateClassLessonsRequest.builder().lessonDTO(lessonDTO).schoolClassDTOS(schoolClassDTOS).build())
                .post("/lessonsAndCertificates/createClassLessons");
    }
}
