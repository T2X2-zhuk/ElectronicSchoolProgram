package SchoolStudent.core.SchoolLessonsAndCertificates;

import SchoolStudent.core.dto.StudentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile({"h2"})
class SchoolLessonsAndCertificatesMicroserviceStubImpl implements SchoolLessonsAndCertificatesMicroservice {

    private static final Logger logger = LoggerFactory.getLogger(SchoolLessonsAndCertificatesMicroserviceStubImpl.class);

    @Override
    public Boolean execute(StudentDTO studentDTO) {
        logger.info("SchoolLessonsAndCertificates stub invoked! Always return false!");
        return false;
    }
}
