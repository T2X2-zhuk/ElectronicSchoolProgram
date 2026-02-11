package SchoolLessonsAndCertificates.services;

import SchoolLessonsAndCertificates.jpa.database.CertificateRepository;
import SchoolLessonsAndCertificates.restAPI.microservice.schoolStudent.request.DeleteStudentsAndTheirCertificatesRequest;
import SchoolLessonsAndCertificates.restAPI.microservice.schoolStudent.response.DeleteStudentsAndTheirCertificatesResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteStudentsAndTheirCertificatesService {

    private final CertificateRepository repository;

    @Transactional
    public DeleteStudentsAndTheirCertificatesResponse execute(DeleteStudentsAndTheirCertificatesRequest request){
        request.getStudentIds()
                .forEach(repository::deleteByStudentId);
        return DeleteStudentsAndTheirCertificatesResponse.builder().message("Students and their certificates successfully deleted.").build();
    }
}
