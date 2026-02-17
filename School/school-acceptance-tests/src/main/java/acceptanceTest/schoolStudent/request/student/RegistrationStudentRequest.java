package acceptanceTest.schoolStudent.request.student;

import acceptanceTest.schoolStudent.dto.SchoolStudentDTO;
import acceptanceTest.schoolStudent.dto.SpecificCodeForRegistrationInElectronicSchoolDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationStudentRequest {

    private SchoolStudentDTO schoolStudentDTO;
    private SpecificCodeForRegistrationInElectronicSchoolDTO specificCodeForRegistrationInElectronicSchoolDTO;
}
