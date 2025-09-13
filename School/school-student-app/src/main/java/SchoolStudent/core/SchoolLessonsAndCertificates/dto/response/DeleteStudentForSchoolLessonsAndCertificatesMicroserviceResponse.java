package SchoolStudent.core.SchoolLessonsAndCertificates.dto.response;

import SchoolStudent.core.dto.CoreResponse;
import SchoolStudent.core.dto.ValidationErrorDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeleteStudentForSchoolLessonsAndCertificatesMicroserviceResponse extends CoreResponse {

    private String message;

    public DeleteStudentForSchoolLessonsAndCertificatesMicroserviceResponse(List<ValidationErrorDTO> errors) {
        super(errors);
    }
}
