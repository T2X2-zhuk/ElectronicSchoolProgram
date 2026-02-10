package SchoolStudent.web.student;

import SchoolStudent.request.student.DeleteStudentRequest;
import SchoolStudent.response.student.DeleteStudentResponse;
import SchoolStudent.services.schoolStudent.DeleteStudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class DeleteStudentWebController {

    private final DeleteStudentService service;

    @GetMapping("delete-student")
    public String showForm(ModelMap modelMap) {
        modelMap.addAttribute("request",  new DeleteStudentRequest());
        modelMap.addAttribute("response", new DeleteStudentResponse());
        return "delete-student";
    }

    @PostMapping("delete-student")
    public String processForm(@RequestParam(value = "emails") String emails,
                              ModelMap modelMap) {
        List<String> stringList = Arrays.stream(emails.split(","))
                .map(String::trim)
                .toList();
        DeleteStudentRequest request = DeleteStudentRequest.builder()
                .emails(stringList)
                .build();
        DeleteStudentResponse response = service.execute(request);
        modelMap.addAttribute("request", request);
        modelMap.addAttribute("response", response);
        return "delete-student";
    }
}
