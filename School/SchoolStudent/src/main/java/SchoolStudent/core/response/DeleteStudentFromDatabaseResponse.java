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
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeleteStudentFromDatabaseResponse extends CoreResponse {

    private String message;

    public DeleteStudentFromDatabaseResponse(List<ValidationErrorDTO> errors) {
        super(errors);
    }
}
