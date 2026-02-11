package SchoolStudent.restAPI.microservice.lessonsAndCertificates.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetLessonNameSchoolLessonsAndCertificatesMicroserviceRequest {

    private String lessonName;
}
