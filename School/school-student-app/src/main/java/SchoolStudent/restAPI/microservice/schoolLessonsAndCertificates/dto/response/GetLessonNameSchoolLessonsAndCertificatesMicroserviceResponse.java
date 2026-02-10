
package SchoolStudent.restAPI.microservice.schoolLessonsAndCertificates.dto.response;

import SchoolStudent.request.common.CoreResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetLessonNameSchoolLessonsAndCertificatesMicroserviceResponse extends CoreResponse {

    private String lessonName;
}
