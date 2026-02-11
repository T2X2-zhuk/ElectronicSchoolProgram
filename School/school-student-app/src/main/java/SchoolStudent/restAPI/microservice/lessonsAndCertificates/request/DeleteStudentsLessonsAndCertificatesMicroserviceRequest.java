package SchoolStudent.restAPI.microservice.lessonsAndCertificates.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DeleteStudentsLessonsAndCertificatesMicroserviceRequest {

    private List<Long> studentIds;
}
