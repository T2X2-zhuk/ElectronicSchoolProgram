package SchoolLessonsAndCertificates.restAPI.controllers.fromOtherMicroservicesResponse;

import SchoolLessonsAndCertificates.response.common.CoreResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@SuperBuilder
@NoArgsConstructor
public class DeleteStudentsAndTheirCertificatesResponse extends CoreResponse {

    private String message;
}
