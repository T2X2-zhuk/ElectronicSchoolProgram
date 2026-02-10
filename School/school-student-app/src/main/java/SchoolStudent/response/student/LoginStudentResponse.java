package SchoolStudent.response.student;

import SchoolStudent.jpa.dto.SchoolStudentDTO;
import SchoolStudent.request.common.CoreResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginStudentResponse extends CoreResponse {

    private SchoolStudentDTO schoolStudentDTO;
}
