package SchoolStudent.request.teacher;

import SchoolStudent.jpa.dto.SpecificCodeForRegistrationInElectronicSchoolDTO;
import SchoolStudent.jpa.dto.TeacherDTO;
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
