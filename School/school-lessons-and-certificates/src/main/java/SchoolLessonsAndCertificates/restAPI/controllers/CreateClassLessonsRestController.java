package SchoolLessonsAndCertificates.restAPI.controllers;

import SchoolLessonsAndCertificates.request.CreateClassLessonsRequest;
import SchoolLessonsAndCertificates.response.CreateClassLessonsResponse;
import SchoolLessonsAndCertificates.services.CreateClassLessonsService;
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
public class CreateClassLessonsRestController {

    private final CreateClassLessonsService service;

    @PostMapping(path = "/createClassLessons",
            consumes = "application/json",
            produces = "application/json")
    public CreateClassLessonsResponse execute(@RequestBody CreateClassLessonsRequest request) {
        return service.execute(request);
    }
}
