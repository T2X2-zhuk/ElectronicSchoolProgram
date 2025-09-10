package SchoolStudent.core.response.student;

import SchoolStudent.core.domain.SchoolStudent;
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
public class GetAllStudentsBySchoolClassResponse extends CoreResponse {

    private List<SchoolStudent> schoolStudentList;

    public GetAllStudentsBySchoolClassResponse(List<ValidationErrorDTO> errors) {
        super(errors);
    }
}
