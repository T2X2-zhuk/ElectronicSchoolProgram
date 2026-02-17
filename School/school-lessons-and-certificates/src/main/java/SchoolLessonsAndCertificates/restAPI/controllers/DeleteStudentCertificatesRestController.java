package SchoolLessonsAndCertificates.restAPI.controllers;


import SchoolLessonsAndCertificates.request.certificate.DeleteStudentCertificatesRequest;
import SchoolLessonsAndCertificates.response.DeleteStudentCertificatesResponse;
import SchoolLessonsAndCertificates.services.cetrificate.DeleteStudentCertificatesService;
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
public class DeleteStudentCertificatesRestController {

    private final DeleteStudentCertificatesService deleteStudentCertificatesService;

    @PostMapping(path = "/deleteStudentCertificates",
            consumes = "application/json",
            produces = "application/json")
    public DeleteStudentCertificatesResponse execute(@RequestBody DeleteStudentCertificatesRequest request) {
        return deleteStudentCertificatesService.execute(request);
    }
}
