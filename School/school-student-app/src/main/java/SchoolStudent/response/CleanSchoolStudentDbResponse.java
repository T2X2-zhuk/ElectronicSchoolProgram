package SchoolStudent.response;

import SchoolStudent.util.CoreResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CleanSchoolStudentDbResponse extends CoreResponse {

    private boolean deleteSchoolClass;
    private boolean deleteSchoolStudent;
    private boolean deleteTeacher;
    private boolean deleteSpecificCodeForRegistrationInElectronicSchool;
}
