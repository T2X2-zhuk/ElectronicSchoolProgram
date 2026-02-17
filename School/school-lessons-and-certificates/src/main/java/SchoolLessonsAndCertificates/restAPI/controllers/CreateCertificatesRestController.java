package SchoolLessonsAndCertificates.restAPI.controllers;

import SchoolLessonsAndCertificates.request.certificate.CreateCertificatesRequest;
import SchoolLessonsAndCertificates.response.CreateCertificatesResponse;
import SchoolLessonsAndCertificates.services.cetrificate.CreateCertificatesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lessonsAndCertificates")
@RequiredArgsConstructor
@Slf4j
public class CreateCertificatesRestController {

    private final CreateCertificatesService service;

    @PostMapping(path = "/createCertificates",
            consumes = "application/json",
            produces = "application/json")
    public CreateCertificatesResponse execute(@RequestBody CreateCertificatesRequest request) {
        return service.execute(request);
    }
}
