package acceptanceTest.schoolLessonsAndCertificates.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCertificatesRequest {

    private Long studentId;
    private Long schoolClassId;
}
