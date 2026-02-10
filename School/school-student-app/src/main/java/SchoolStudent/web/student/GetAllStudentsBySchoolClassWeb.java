package SchoolStudent.web.student;

import SchoolStudent.request.student.GetStudentsBySchoolClassRequest;
import SchoolStudent.response.student.GetStudentsBySchoolClassResponse;
import SchoolStudent.services.schoolStudent.GetStudentsBySchoolClassService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class GetAllStudentsBySchoolClassWeb {

    private final GetStudentsBySchoolClassService service;

    @GetMapping("/get-all-students-by-school-class")
    public String showForm(ModelMap modelMap) {
        modelMap.addAttribute("request", new GetStudentsBySchoolClassRequest());
        modelMap.addAttribute("response", new GetStudentsBySchoolClassResponse());
        return "get-all-students-by-school-class";
    }

    @PostMapping("/get-all-students-by-school-class")
    public String processForm(@ModelAttribute(value = "request") GetStudentsBySchoolClassRequest request,
                              ModelMap modelMap) {
        GetStudentsBySchoolClassResponse response = service.execute(request);
        modelMap.addAttribute("response", response);
        return "get-all-students-by-school-class";
    }
}
