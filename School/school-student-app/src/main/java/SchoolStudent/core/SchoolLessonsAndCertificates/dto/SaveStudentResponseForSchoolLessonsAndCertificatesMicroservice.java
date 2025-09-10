package SchoolStudent.core.SchoolLessonsAndCertificates.dto;

import SchoolStudent.core.dto.CoreResponse;
import SchoolStudent.core.dto.ValidationErrorDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaveStudentResponseForSchoolLessonsAndCertificatesMicroservice extends CoreResponse {

    private String successfulMessage;

    public SaveStudentResponseForSchoolLessonsAndCertificatesMicroservice(List<ValidationErrorDTO> errors) {
        super(errors);
    }

}
