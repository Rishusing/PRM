import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/releases")
public class ProductionReleaseManagementController {

    @Autowired
    private ProductionReleaseManagementService service;

    @GetMapping
    public String getAllReleases(Model model) {
        List<ProductionReleaseManagement> releases = service.getAllReleases();
        model.addAttribute("releases", releases);
        return "releases-list"; // Thymeleaf template
    }

    @GetMapping("/new")
    public String createReleaseForm(Model model) {
        model.addAttribute("release", new ProductionReleaseManagement());
        return "release-form"; // Thymeleaf template
    }

    @PostMapping
    public String saveRelease(@ModelAttribute ProductionReleaseManagement release) {
        service.saveRelease(release);
        return "redirect:/releases";
    }

    @GetMapping("/edit/{id}")
    public String editReleaseForm(@PathVariable String id, Model model) {
        Optional<ProductionReleaseManagement> release = service.getReleaseById(id);
        if (release.isPresent()) {
            model.addAttribute("release", release.get());
            return "release-form";
        } else {
            return "redirect:/releases";
        }
    }

    @PostMapping("/update/{id}")
    public String updateRelease(@PathVariable String id, @ModelAttribute ProductionReleaseManagement release) {
        service.saveRelease(release);
        return "redirect:/releases";
    }

    @GetMapping("/delete/{id}")
    public String deleteRelease(@PathVariable String id) {
        service.deleteRelease(id);
        return "redirect:/releases";
    }
}
