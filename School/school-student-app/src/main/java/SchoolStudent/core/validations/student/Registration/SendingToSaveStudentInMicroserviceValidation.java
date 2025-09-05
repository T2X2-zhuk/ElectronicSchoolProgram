package SchoolStudent.core.validations.student.Registration;
import SchoolStudent.core.SchoolLessonsAndCertificates.SchoolLessonsAndCertificatesMicroservice;
import SchoolStudent.core.dto.StudentDTO;
import SchoolStudent.core.dto.ValidationErrorDTO;
import SchoolStudent.core.request.RegistrationStudentInDatabaseRequest;
import SchoolStudent.core.validations.ValidationErrorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
class SendingToSaveStudentInMicroserviceValidation implements RegistrationStudentValidation {
    private static final Logger logger = LoggerFactory.getLogger(SendingToSaveStudentInMicroserviceValidation.class);
    @Autowired
    private SchoolLessonsAndCertificatesMicroservice service;
    @Autowired private ValidationErrorFactory factory;

    @Override
    public List<ValidationErrorDTO> validationErrorDTOSList(RegistrationStudentInDatabaseRequest request) {
        StudentDTO studentDTO = new StudentDTO(request.getFirstName(),request.getLastName(),request.getFatherland());
       List<ValidationErrorDTO> errorDTOS = new ArrayList<>();
       if (!firstNameIsNullOrBlank(studentDTO) && !lastNameIsNullOrBlank(studentDTO) && !fatherlandIsNullOrBlank(studentDTO)){
           lessonsAndCertificatesSaveStudentDTO(studentDTO).ifPresent(errorDTOS::add);
       }
       return errorDTOS;
    }

    private boolean firstNameIsNullOrBlank(StudentDTO person) {
        return person.getFirst_name() == null || person.getFirst_name().isBlank() || person.getFirst_name().isEmpty();
    }

    private boolean lastNameIsNullOrBlank(StudentDTO person) {
        return person.getLast_name() == null || person.getLast_name().isBlank() || person.getLast_name().isEmpty();
    }

    private boolean fatherlandIsNullOrBlank(StudentDTO person) {
        return person.getFatherland() == null || person.getFatherland().isBlank() || person.getFatherland().isEmpty();
    }

    private Optional<ValidationErrorDTO> lessonsAndCertificatesSaveStudentDTO(StudentDTO studentDTO) {
        try {
            if (!service.execute(studentDTO)) {
                return Optional.of(factory.buildError("ERROR_CODE_19"));
            }
        } catch (Throwable e) {
            logger.error("SchoolLessonsAndCertificatesMicroservice call failed!", e);
            return Optional.of(factory.buildError("ERROR_CODE_20"));
        }
        return Optional.empty();
    }
}
