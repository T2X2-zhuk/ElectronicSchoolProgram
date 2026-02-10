package SchoolLessonsAndCertificates.restAPI.controllers;

import SchoolLessonsAndCertificates.restAPI.controllers.fromOtherMicroservicesRequest.GetLessonNameRequest;
import SchoolLessonsAndCertificates.restAPI.controllers.fromOtherMicroservicesResponse.GetLessonNameResponse;
import SchoolLessonsAndCertificates.services.GetLessonNameService;
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
public class GetLessonNameRestController {

    private final GetLessonNameService subjectByNameService;

    @PostMapping(path = "/getLessonName",
            consumes = "application/json",
            produces = "application/json")
    public GetLessonNameResponse execute(@RequestBody GetLessonNameRequest request) {
        return subjectByNameService.execute(request);
    }
}
