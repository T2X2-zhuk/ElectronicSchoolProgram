package SchoolStudent.restAPI.microservice.lessonsAndCertificates.controllers;

import SchoolStudent.restAPI.microservice.lessonsAndCertificates.request.GetSchoolClassIdsRequest;
import SchoolStudent.restAPI.microservice.lessonsAndCertificates.response.GetSchoolClassIdsResponse;
import SchoolStudent.services.schoolClass.GetSchoolClassIdsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/school/student/api")
@RequiredArgsConstructor
@Slf4j
public class GetSchoolClassIdsRestController {


    private final GetSchoolClassIdsService service;

    @PostMapping(path = "/getSchoolClassIds",
            consumes = "application/json",
            produces = "application/json")
    public GetSchoolClassIdsResponse execute(@RequestBody GetSchoolClassIdsRequest request) {
        return service.execute(request);
    }
}
