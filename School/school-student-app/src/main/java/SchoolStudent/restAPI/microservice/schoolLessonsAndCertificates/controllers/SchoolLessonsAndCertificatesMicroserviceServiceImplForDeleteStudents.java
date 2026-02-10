package SchoolStudent.restAPI.microservice.schoolLessonsAndCertificates.controllers;

import SchoolStudent.restAPI.microservice.schoolLessonsAndCertificates.dto.request.DeleteStudentsLessonsAndCertificatesMicroserviceRequest;
import SchoolStudent.restAPI.microservice.schoolLessonsAndCertificates.dto.response.DeleteStudentsLessonsAndCertificatesMicroserviceResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
@Slf4j
public class SchoolLessonsAndCertificatesMicroserviceServiceImplForDeleteStudents {

    @Value("${student.lessonsAndCertificates.deleteStudentsByStudentIds.url}")
    private String URL;
    private final RestClient restClient;

    public DeleteStudentsLessonsAndCertificatesMicroserviceResponse execute(DeleteStudentsLessonsAndCertificatesMicroserviceRequest request) {

        log.info("Sending students Ids to LessonsAndCertificates microservice: {}", request);

        return restClient
                .post()
                .uri(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .onStatus(
                        HttpStatusCode::isError,
                        (req, res) -> {
                            String errorBody = new String(res.getBody().readAllBytes());

                            log.error("Error response from microservice. Status: {}, Body: {}",
                                    res.getStatusCode(),
                                    errorBody);

                            throw new RuntimeException(
                                    "LessonsAndCertificates microservice error: "
                                            + res.getStatusCode()
                            );
                        }
                )
                .body(DeleteStudentsLessonsAndCertificatesMicroserviceResponse.class);
    }
}
