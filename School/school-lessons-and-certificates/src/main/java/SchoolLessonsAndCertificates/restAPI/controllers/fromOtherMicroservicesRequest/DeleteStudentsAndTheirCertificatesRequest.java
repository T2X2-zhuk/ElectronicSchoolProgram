package SchoolLessonsAndCertificates.restAPI.controllers.fromOtherMicroservicesRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteStudentsAndTheirCertificatesRequest {

    private List<Long> studentIds;
}
