package SchoolStudent.core.SchoolLessonsAndCertificates.services;

import SchoolStudent.core.SchoolLessonsAndCertificates.interficesForServices.SchoolLessonsAndCertificatesMicroserviceForDeleteStudents;
import SchoolStudent.core.SchoolLessonsAndCertificates.dto.request.DeleteStudentForSchoolLessonsAndCertificatesMicroserviceRequest;
import SchoolStudent.core.SchoolLessonsAndCertificates.dto.response.DeleteStudentForSchoolLessonsAndCertificatesMicroserviceResponse;
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
 class SchoolLessonsAndCertificatesMicroserviceServiceImplForDeleteStudents implements SchoolLessonsAndCertificatesMicroserviceForDeleteStudents {

    @Value("${student.lessonsAndCertificates.deleteStudentsByListEmails.url}")
    private String studentLessonsAndCertificatesForDeleteStudentsByListEmailsUrl;

    @Autowired private RestTemplate restTemplate;
    @Override
    public DeleteStudentForSchoolLessonsAndCertificatesMicroserviceResponse execute(DeleteStudentForSchoolLessonsAndCertificatesMicroserviceRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<DeleteStudentForSchoolLessonsAndCertificatesMicroserviceRequest> requestEntity = new HttpEntity<>(request, headers);
        return restTemplate.postForObject(studentLessonsAndCertificatesForDeleteStudentsByListEmailsUrl, request, DeleteStudentForSchoolLessonsAndCertificatesMicroserviceResponse.class);
    }
}
