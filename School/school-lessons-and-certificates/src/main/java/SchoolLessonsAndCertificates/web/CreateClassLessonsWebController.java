package SchoolLessonsAndCertificates.web;

import SchoolLessonsAndCertificates.request.CreateClassLessonsRequest;
import SchoolLessonsAndCertificates.response.CreateClassLessonsResponse;
import SchoolLessonsAndCertificates.services.CreateClassLessonsService;
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
public class CreateClassLessonsWebController {

    private final CreateClassLessonsService service;

    @GetMapping("create-class-lessons")
    public String showForm(ModelMap modelMap) {
        modelMap.addAttribute("request", new CreateClassLessonsRequest());
        modelMap.putIfAbsent("response", new CreateClassLessonsResponse());
        return "create-class-lessons";
    }

    @PostMapping("create-class-lessons")
    public String processForm(@ModelAttribute("request") CreateClassLessonsRequest request,
                              RedirectAttributes redirectAttributes) {
        CreateClassLessonsResponse response = service.execute(request);
        redirectAttributes.addFlashAttribute("response", response);
        return "redirect:/create-class-lessons";
    }
}
