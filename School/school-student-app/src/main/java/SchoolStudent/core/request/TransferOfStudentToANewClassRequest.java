package SchoolStudent.core.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferOfStudentToANewClassRequest {

    private String email;
    private String category;
    private Long newClassNumber;
}
