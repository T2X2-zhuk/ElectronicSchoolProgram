package SchoolStudent.core.request.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllStudentsBySchoolClassRequest {

    private Long number;
    private String category;
}
