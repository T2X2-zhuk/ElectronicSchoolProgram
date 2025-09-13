package SchoolStudent.core.web;

import SchoolStudent.core.domain.SchoolStudent;
import SchoolStudent.core.request.student.LoginToYourStudentPageRequest;
import SchoolStudent.core.response.student.LoginToYourPageForStudentResponse;
import SchoolStudent.core.service.interfeicesForServices.LoginIntoYourPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
public class LoginToYourPageForStudentWeb {

    @Autowired private LoginIntoYourPageService service;

    @GetMapping("authorization-in-school")
    public String showForm(ModelMap modelMap) {
        modelMap.addAttribute("request", new LoginToYourStudentPageRequest());
        modelMap.addAttribute("response", new LoginToYourPageForStudentResponse());
        return "authorization-in-school";
    }

    @PostMapping("authorization-in-school")
    public String handleLogin(@ModelAttribute(value = "request") LoginToYourStudentPageRequest request, RedirectAttributes redirectAttrs, ModelMap modelMap) {
        LoginToYourPageForStudentResponse response = service.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "authorization-in-school";
        }

        if (response.getSchoolStudent() != null) {
            redirectAttrs.addFlashAttribute("schoolStudent", response.getSchoolStudent());
            return "redirect:/profile";
        } else {
            return "authorization-in-school";
        }
    }

    @GetMapping("/profile")
    public String showProfile(@ModelAttribute("schoolStudent")SchoolStudent schoolStudent, ModelMap modelMap) {
        if (schoolStudent == null) {
            return "redirect:/authorization-in-school";
        }
        modelMap.addAttribute("schoolStudent", schoolStudent);
        return "profile";
    }
}
