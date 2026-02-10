package SchoolStudent.web.student;

import SchoolStudent.jpa.dto.SchoolStudentDTO;
import SchoolStudent.request.student.LoginStudentRequest;
import SchoolStudent.response.student.LoginStudentResponse;
import SchoolStudent.services.schoolStudent.LoginStudentService;
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
public class LoginStudentWebController {

    private final LoginStudentService service;

    @GetMapping("authorization-in-school")
    public String showForm(ModelMap modelMap) {
        modelMap.addAttribute("request", new LoginStudentRequest());
        modelMap.addAttribute("response", new LoginStudentResponse());
        return "authorization-in-school";
    }

    @PostMapping("authorization-in-school")
    public String handleLogin(@ModelAttribute(value = "request") LoginStudentRequest request, RedirectAttributes redirectAttrs, ModelMap modelMap) {
        LoginStudentResponse response = service.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "authorization-in-school";
        }
        redirectAttrs.addFlashAttribute("schoolStudentDTO", response.getSchoolStudentDTO());
        return "redirect:/profile";
    }

    @GetMapping("/profile")
    public String showProfile(@ModelAttribute("schoolStudentDTO") SchoolStudentDTO schoolStudentDTO, ModelMap modelMap) {
        modelMap.addAttribute("schoolStudentDTO", schoolStudentDTO);
        return "profile";
    }
}
