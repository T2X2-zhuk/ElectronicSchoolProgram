package SchoolStudent.response.student;

import SchoolStudent.jpa.dto.SchoolStudentDTO;
import SchoolStudent.util.CoreResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetStudentsBySchoolClassResponse extends CoreResponse {

    private List<SchoolStudentDTO> schoolStudentDTOS;
}
