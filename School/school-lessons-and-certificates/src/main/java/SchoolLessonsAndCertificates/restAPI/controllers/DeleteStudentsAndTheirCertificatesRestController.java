package SchoolLessonsAndCertificates.restAPI.controllers;


import SchoolLessonsAndCertificates.restAPI.controllers.fromOtherMicroservicesRequest.DeleteStudentsAndTheirCertificatesRequest;
import SchoolLessonsAndCertificates.restAPI.controllers.fromOtherMicroservicesResponse.DeleteStudentsAndTheirCertificatesResponse;
import SchoolLessonsAndCertificates.services.DeleteStudentsAndTheirCertificatesService;
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
public class DeleteStudentsAndTheirCertificatesRestController {

    private final DeleteStudentsAndTheirCertificatesService deleteStudentsAndTheirCertificatesService;

    @PostMapping(path = "/deleteStudentsByStudentIds",
            consumes = "application/json",
            produces = "application/json")
    public DeleteStudentsAndTheirCertificatesResponse execute(@RequestBody DeleteStudentsAndTheirCertificatesRequest request) {
        return deleteStudentsAndTheirCertificatesService.execute(request);
    }
}
