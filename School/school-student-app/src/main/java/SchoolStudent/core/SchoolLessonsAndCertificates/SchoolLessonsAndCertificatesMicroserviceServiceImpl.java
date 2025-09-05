package SchoolStudent.core.SchoolLessonsAndCertificates;

import SchoolStudent.core.SchoolLessonsAndCertificates.dto.SaveStudentRequest;
import SchoolStudent.core.SchoolLessonsAndCertificates.dto.SaveStudentResponse;
import SchoolStudent.core.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Profile({"mysql-app", "mysql-local"})
 class SchoolLessonsAndCertificatesMicroserviceServiceImpl implements SchoolLessonsAndCertificatesMicroservice {

    @Value("${student.lessonsAndCertificates.saveStudent.url}")
    private String studentLessonsAndCertificatesUrl;

    @Autowired private RestTemplate restTemplate;
    @Override
    public Boolean execute(StudentDTO studentDTO) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        SaveStudentRequest request = new SaveStudentRequest();
        request.setFirstName(studentDTO.getFirst_name());
        request.setLastName(studentDTO.getLast_name());
        request.setFatherland(studentDTO.getFatherland());

        // Create an HttpEntity object with the request body and headers
        HttpEntity<SaveStudentRequest> requestEntity = new HttpEntity<>(request, headers);

        // Make the POST request and expect a BlackListedPersonCheckResponse object in response
        SaveStudentResponse response = restTemplate.postForObject(studentLessonsAndCertificatesUrl, request, SaveStudentResponse.class);
        return response.getSuccessfulOrUnsuccessfulRegister();
    }

}
