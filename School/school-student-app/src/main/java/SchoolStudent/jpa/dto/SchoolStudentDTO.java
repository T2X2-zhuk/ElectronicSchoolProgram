package SchoolStudent.jpa.dto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SchoolStudentDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String fatherland;
    private String email;
    private String password;
    private SchoolClassDTO schoolClassDTO;
}
