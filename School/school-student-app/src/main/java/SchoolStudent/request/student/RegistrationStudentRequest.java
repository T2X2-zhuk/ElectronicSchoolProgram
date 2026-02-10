package SchoolStudent.request.student;

import SchoolStudent.jpa.dto.SchoolStudentDTO;
import SchoolStudent.jpa.dto.SpecificCodeForRegistrationInElectronicSchoolDTO;
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
