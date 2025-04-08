package org.example.bootjsp.Controller;

import org.example.bootjsp.model.dto.RealEstate;
import org.example.bootjsp.model.repository.RealEstateRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MainController {

    private final RealEstateRepository realEstateRepository;

    public MainController(RealEstateRepository realEstateRepository) {
        this.realEstateRepository = realEstateRepository;
    }

    @GetMapping
    public String index(Model model) {
        List<RealEstate> data = realEstateRepository.findAll();
        model.addAttribute("data", data);
        return "index";
    }

    @PostMapping
    public String add(@ModelAttribute RealEstate realEstate) {
        realEstateRepository.save(realEstate);
        return "redirect:/";
    }
}
