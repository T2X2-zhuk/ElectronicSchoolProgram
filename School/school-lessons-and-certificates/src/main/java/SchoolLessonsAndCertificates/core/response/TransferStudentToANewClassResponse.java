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
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransferStudentToANewClassResponse extends CoreResponse {

   private String message;

    public TransferStudentToANewClassResponse(List<ValidationErrorDTO> errors) {
        super(errors);
    }
}
