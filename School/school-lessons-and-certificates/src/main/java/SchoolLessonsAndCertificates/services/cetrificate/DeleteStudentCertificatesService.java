package SchoolLessonsAndCertificates.services.cetrificate;

import SchoolLessonsAndCertificates.jpa.database.CertificateRepository;
import SchoolLessonsAndCertificates.request.certificate.DeleteStudentCertificatesRequest;
import SchoolLessonsAndCertificates.response.DeleteStudentCertificatesResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeleteStudentCertificatesService {

    private final CertificateRepository repository;

    @Transactional
    public DeleteStudentCertificatesResponse execute(DeleteStudentCertificatesRequest request){
        repository.deleteByStudentIds(request.getStudentIds());
        return DeleteStudentCertificatesResponse.builder().message("Students and their certificates successfully deleted.").build();
    }
}
