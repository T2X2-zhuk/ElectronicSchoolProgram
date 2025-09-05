package SchoolLessonsAndCertificates.core.response;

import SchoolLessonsAndCertificates.core.dto.CoreResponse;
import SchoolLessonsAndCertificates.core.dto.ValidationErrorDTO;
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
public class SaveStudentResponse extends CoreResponse {

    private Boolean successfulOrUnsuccessfulRegister;

    public SaveStudentResponse(List<ValidationErrorDTO> errors) {
        super(errors);
    }

}
