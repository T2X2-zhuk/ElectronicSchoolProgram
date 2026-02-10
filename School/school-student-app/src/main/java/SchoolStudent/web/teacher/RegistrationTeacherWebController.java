package SchoolStudent.web.teacher;

import SchoolStudent.request.teacher.RegistrationTeacherRequest;
import SchoolStudent.response.teacher.RegistrationTeacherResponse;
import SchoolStudent.services.teacher.RegistrationTeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@Slf4j
public class RegistrationTeacherWebController {

    private final RegistrationTeacherService service;

    @GetMapping("register-teacher")
    public String showForm(ModelMap modelMap) {
        modelMap.addAttribute("request", new RegistrationTeacherRequest());
        modelMap.putIfAbsent("response", new RegistrationTeacherResponse());
        return "register-teacher";
    }

    @PostMapping("register-teacher")
    public String processForm(@ModelAttribute(value = "request") RegistrationTeacherRequest request,
                              RedirectAttributes redirectAttributes) {
        RegistrationTeacherResponse response = service.execute(request);
        redirectAttributes.addFlashAttribute("response", response);
        return "redirect:/register-teacher";
    }

}
