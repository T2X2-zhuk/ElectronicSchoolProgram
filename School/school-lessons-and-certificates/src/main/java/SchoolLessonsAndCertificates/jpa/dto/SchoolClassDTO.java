package SchoolLessonsAndCertificates.jpa.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SchoolClassDTO {

    private Long id;
    private Long number;
    private String category;
}
