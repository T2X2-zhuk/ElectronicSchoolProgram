package SchoolLessonsAndCertificates.restAPI.microservice.schoolStudent.controllers;

import SchoolLessonsAndCertificates.restAPI.microservice.schoolStudent.request.GetSchoolClassIdsRequest;
import SchoolLessonsAndCertificates.restAPI.microservice.schoolStudent.response.GetSchoolClassIdsResponse;
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
public class SchoolStudentMicroserviceGetSchoolClassIds {

    @Value("${schoolStudent.getSchoolClassIds.url}")
    private String URL;
    private final RestClient restClient;

    public GetSchoolClassIdsResponse execute(GetSchoolClassIdsRequest request) {

        log.info("Sending school class DTOs Ids to SchoolStudent microservice: {}", request);

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
                                    "SchoolStudent microservice error: "
                                            + res.getStatusCode()
                            );
                        }
                )
                .body(GetSchoolClassIdsResponse.class);
    }
}
