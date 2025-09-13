package SchoolStudent.core.SchoolLessonsAndCertificates.services;

import SchoolStudent.core.SchoolLessonsAndCertificates.interficesForServices.SchoolLessonsAndCertificatesMicroserviceForSaveStudent;
import SchoolStudent.core.SchoolLessonsAndCertificates.dto.request.SaveStudentForSchoolLessonsAndCertificatesMicroserviceRequest;
import SchoolStudent.core.SchoolLessonsAndCertificates.dto.response.SaveStudentForSchoolLessonsAndCertificatesMicroserviceResponse;
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
 class SchoolLessonsAndCertificatesMicroserviceServiceImplForSaveStudent implements SchoolLessonsAndCertificatesMicroserviceForSaveStudent {

    @Value("${student.lessonsAndCertificates.saveStudent.url}")
    private String studentLessonsAndCertificatesForSaveStudentUrl;

    @Autowired private RestTemplate restTemplate;
    @Override
    public SaveStudentForSchoolLessonsAndCertificatesMicroserviceResponse execute(SaveStudentForSchoolLessonsAndCertificatesMicroserviceRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<SaveStudentForSchoolLessonsAndCertificatesMicroserviceRequest> requestEntity = new HttpEntity<>(request, headers);
        return restTemplate.postForObject(studentLessonsAndCertificatesForSaveStudentUrl, request, SaveStudentForSchoolLessonsAndCertificatesMicroserviceResponse.class);
    }

}
