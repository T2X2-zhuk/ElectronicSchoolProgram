package SchoolLessonsAndCertificates.restAPI.controllers;

import SchoolLessonsAndCertificates.request.CleanSchoolLessonsAndCertificatesDbRequest;
import SchoolLessonsAndCertificates.response.CleanSchoolLessonsAndCertificatesDbResponse;
import SchoolLessonsAndCertificates.services.CleanDbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lessonsAndCertificates")
@RequiredArgsConstructor
@Slf4j
public class CleanDbRestController {

    private final CleanDbService service;

    @PostMapping(path = "/cleanDb",
            consumes = "application/json",
            produces = "application/json")
    public CleanSchoolLessonsAndCertificatesDbResponse execute(@RequestBody CleanSchoolLessonsAndCertificatesDbRequest request) {
        return service.execute(request);
    }
}
