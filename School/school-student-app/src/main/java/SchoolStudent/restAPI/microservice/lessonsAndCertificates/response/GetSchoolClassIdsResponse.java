package SchoolStudent.restAPI.microservice.lessonsAndCertificates.response;

import SchoolStudent.util.CoreResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@SuperBuilder
@NoArgsConstructor
public class GetSchoolClassIdsResponse extends CoreResponse {

    private List<Long> studentsIds;
}
