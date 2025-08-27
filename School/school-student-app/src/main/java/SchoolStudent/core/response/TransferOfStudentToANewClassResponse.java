package SchoolStudent.core.response;

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
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransferOfStudentToANewClassResponse extends CoreResponse {

   private String message;

    public TransferOfStudentToANewClassResponse(List<ValidationErrorDTO> errors) {
        super(errors);
    }
}
