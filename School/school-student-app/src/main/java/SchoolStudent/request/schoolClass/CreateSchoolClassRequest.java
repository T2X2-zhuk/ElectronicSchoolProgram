package SchoolStudent.request.schoolClass;

import SchoolStudent.jpa.dto.SchoolClassDTO;
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
