package SchoolLessonsAndCertificates.core.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferOfStudentToANewClassRequest {

    private Long number;
    private String category;
    private String email;
}
