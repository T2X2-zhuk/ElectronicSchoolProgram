package acceptanceTest.schoolStudent.request.schoolClass;

import acceptanceTest.schoolStudent.dto.SchoolClassDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateSchoolClassRequest {

    private SchoolClassDTO schoolClassDTO;
}
