package SchoolStudent.core.SchoolLessonsAndCertificates;

import SchoolStudent.core.SchoolLessonsAndCertificates.dto.SaveStudentRequestForSchoolLessonsAndCertificatesMicroservice;
import SchoolStudent.core.SchoolLessonsAndCertificates.dto.SaveStudentResponseForSchoolLessonsAndCertificatesMicroservice;
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
 class SchoolLessonsAndCertificatesMicroserviceServiceImpl implements SchoolLessonsAndCertificatesMicroservice {

    @Value("${student.lessonsAndCertificates.saveStudent.url}")
    private String studentLessonsAndCertificatesForSaveStudentUrl;

    @Autowired private RestTemplate restTemplate;
    @Override
    public SaveStudentResponseForSchoolLessonsAndCertificatesMicroservice execute(SaveStudentRequestForSchoolLessonsAndCertificatesMicroservice request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // Create an HttpEntity object with the request body and headers
        HttpEntity<SaveStudentRequestForSchoolLessonsAndCertificatesMicroservice> requestEntity = new HttpEntity<>(request, headers);

        // Make the POST request and expect a BlackListedPersonCheckResponse object in response
        return restTemplate.postForObject(studentLessonsAndCertificatesForSaveStudentUrl, request, SaveStudentResponseForSchoolLessonsAndCertificatesMicroservice.class);
    }

}
