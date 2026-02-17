package acceptanceTest.schoolStudent.request.teacher;

import acceptanceTest.schoolStudent.dto.SpecificCodeForRegistrationInElectronicSchoolDTO;
import acceptanceTest.schoolStudent.dto.TeacherDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationTeacherRequest {

    private TeacherDTO teacherDTO;
    private SpecificCodeForRegistrationInElectronicSchoolDTO specificCodeForRegistrationInElectronicSchoolDTO;
}
