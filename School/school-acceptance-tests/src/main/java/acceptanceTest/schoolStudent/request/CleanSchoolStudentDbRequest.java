package acceptanceTest.schoolStudent.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CleanSchoolStudentDbRequest {

    private boolean cleanSchoolClass;
    private boolean cleanSchoolStudent;
    private boolean cleanTeacher;
    private boolean cleanSpecificCodeForRegistrationInElectronicSchool;

}
