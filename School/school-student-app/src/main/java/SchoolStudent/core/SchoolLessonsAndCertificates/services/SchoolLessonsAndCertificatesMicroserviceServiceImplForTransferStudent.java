package SchoolStudent.core.SchoolLessonsAndCertificates.services;

import SchoolStudent.core.SchoolLessonsAndCertificates.interficesForServices.SchoolLessonsAndCertificatesMicroserviceForTransferStudent;
import SchoolStudent.core.SchoolLessonsAndCertificates.dto.request.TransferOfStudentToANewClassForSchoolLessonsAndCertificatesMicroserviceRequest;
import SchoolStudent.core.SchoolLessonsAndCertificates.dto.response.TransferStudentToANewClassForSchoolLessonsAndCertificatesMicroserviceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Profile({"mysql-app", "mysql-local", "h2"})
 class SchoolLessonsAndCertificatesMicroserviceServiceImplForTransferStudent implements SchoolLessonsAndCertificatesMicroserviceForTransferStudent
{

    @Value("${student.lessonsAndCertificates.transferStudentToNewClassService.url}")
    private String studentLessonsAndCertificatesForTransferStudentToNewClassServiceUrl;

    @Autowired private RestTemplate restTemplate;

    @Override
    public TransferStudentToANewClassForSchoolLessonsAndCertificatesMicroserviceResponse execute(TransferOfStudentToANewClassForSchoolLessonsAndCertificatesMicroserviceRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<TransferOfStudentToANewClassForSchoolLessonsAndCertificatesMicroserviceRequest> requestEntity = new HttpEntity<>(request, headers);
        return restTemplate.postForObject(studentLessonsAndCertificatesForTransferStudentToNewClassServiceUrl, request, TransferStudentToANewClassForSchoolLessonsAndCertificatesMicroserviceResponse.class);
    }
}
