package acceptanceTest.schoolStudent.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecificCodeForRegistrationInElectronicSchoolDTO {

    private Long id;
    private String specificCodeForRegistrationForStudent;
    private String specificCodeForRegistrationForTeacher;
}
