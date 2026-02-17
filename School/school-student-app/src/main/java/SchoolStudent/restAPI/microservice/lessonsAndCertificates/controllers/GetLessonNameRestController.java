package SchoolStudent.restAPI.microservice.lessonsAndCertificates.controllers;

import SchoolStudent.restAPI.microservice.lessonsAndCertificates.request.GetLessonNameRequest;
import SchoolStudent.restAPI.microservice.lessonsAndCertificates.response.GetLessonNameResponse;
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
public class GetLessonNameRestController {

    @Value("${lessonsAndCertificates.getLessonName.url}")
    private String URL;
    private final RestClient restClient;

    public GetLessonNameResponse execute(GetLessonNameRequest request) {

        log.info("Sending lesson name to LessonsAndCertificates microservice: {}", request);
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
                .body(GetLessonNameResponse.class);
    }
}
