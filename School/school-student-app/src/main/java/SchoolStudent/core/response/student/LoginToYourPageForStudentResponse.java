package SchoolStudent.core.response.student;

import SchoolStudent.core.domain.SchoolStudent;
import SchoolStudent.core.dto.CoreResponse;
import SchoolStudent.core.dto.ValidationErrorDTO;
import jdk.dynalink.linker.LinkerServices;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginToYourPageForStudentResponse extends CoreResponse {

    private SchoolStudent schoolStudent;

    public LoginToYourPageForStudentResponse(List<ValidationErrorDTO> errors) {
        super(errors);
    }
}
