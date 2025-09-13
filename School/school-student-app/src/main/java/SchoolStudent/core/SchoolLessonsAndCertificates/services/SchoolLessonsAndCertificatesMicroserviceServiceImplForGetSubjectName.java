package SchoolStudent.core.SchoolLessonsAndCertificates.services;

import SchoolStudent.core.SchoolLessonsAndCertificates.dto.request.DeleteStudentForSchoolLessonsAndCertificatesMicroserviceRequest;
import SchoolStudent.core.SchoolLessonsAndCertificates.dto.request.GettingTheNameOfAnExistingLessonForSchoolLessonsAndCertificatesMicroserviceRequest;
import SchoolStudent.core.SchoolLessonsAndCertificates.dto.response.DeleteStudentForSchoolLessonsAndCertificatesMicroserviceResponse;
import SchoolStudent.core.SchoolLessonsAndCertificates.dto.response.GettingTheNameOfAnExistingLessonForSchoolLessonsAndCertificatesMicroserviceResponse;
import SchoolStudent.core.SchoolLessonsAndCertificates.interficesForServices.SchoolLessonsAndCertificatesMicroserviceForDeleteStudents;
import SchoolStudent.core.SchoolLessonsAndCertificates.interficesForServices.SchoolLessonsAndCertificatesMicroserviceForGetSubjectNameService;
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
 class SchoolLessonsAndCertificatesMicroserviceServiceImplForGetSubjectName implements SchoolLessonsAndCertificatesMicroserviceForGetSubjectNameService {

    @Value("${student.lessonsAndCertificates.getSubjectByName.url}")
    private String studentLessonsAndCertificatesForGetSubjectByNameUrl;

    @Autowired private RestTemplate restTemplate;
    @Override
    public GettingTheNameOfAnExistingLessonForSchoolLessonsAndCertificatesMicroserviceResponse execute(GettingTheNameOfAnExistingLessonForSchoolLessonsAndCertificatesMicroserviceRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<GettingTheNameOfAnExistingLessonForSchoolLessonsAndCertificatesMicroserviceRequest> requestEntity = new HttpEntity<>(request, headers);
        return restTemplate.postForObject(studentLessonsAndCertificatesForGetSubjectByNameUrl, request, GettingTheNameOfAnExistingLessonForSchoolLessonsAndCertificatesMicroserviceResponse.class);

    }
}
