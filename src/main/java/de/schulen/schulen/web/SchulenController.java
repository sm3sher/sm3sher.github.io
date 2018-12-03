package de.schulen.schulen.web;

import de.schulen.schulen.dataaccess.SchulRepository;
import de.schulen.schulen.model.Schulen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SchulenController {

    @Autowired
    SchulRepository schulen;

    @GetMapping("/")
    public String list(Model model) {

        List<Schulen> all = schulen.findAll();
        model.addAttribute("schulen",all);
        return "schulen";
    }
}
