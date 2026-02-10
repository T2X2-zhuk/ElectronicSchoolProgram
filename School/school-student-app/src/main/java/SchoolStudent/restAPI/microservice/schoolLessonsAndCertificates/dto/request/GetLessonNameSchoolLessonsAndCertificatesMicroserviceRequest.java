package SchoolStudent.restAPI.microservice.schoolLessonsAndCertificates.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetLessonNameSchoolLessonsAndCertificatesMicroserviceRequest {

    private String lessonName;
}
