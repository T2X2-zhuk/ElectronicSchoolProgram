package SchoolLessonsAndCertificates.response;


import SchoolLessonsAndCertificates.util.CoreResponse;
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
public class CreateClassLessonsResponse extends CoreResponse {

    private String message;
}
