package SchoolLessonsAndCertificates.core.response;

import SchoolLessonsAndCertificates.core.dto.CoreResponse;
import SchoolLessonsAndCertificates.core.dto.ValidationErrorDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeleteStudentsResponse extends CoreResponse {

    private String message;

    public DeleteStudentsResponse(List<ValidationErrorDTO> errors) {
        super(errors);
    }
}
