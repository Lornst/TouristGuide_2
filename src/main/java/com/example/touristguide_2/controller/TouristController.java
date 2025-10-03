package com.example.touristguide_2.controller;

import com.example.touristguide_2.model.TouristAttraction;
import com.example.touristguide_2.service.TouristService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("attraction")
public class TouristController {
    final private TouristService touristService;

    public TouristController(TouristService touristService) {
        this.touristService = touristService;
    }


    @GetMapping("list") //attraction
    public String getAllAttractions(Model model) {
        List<TouristAttraction> attractionList = touristService.getAllAttractions();
        model.addAttribute("list", attractionList);
        return "attractionList";
    }

    //Slettet name

    @GetMapping("/add")
    public String addAttraction(Model model) {
        TouristAttraction newAttraction = new TouristAttraction();

        model.addAttribute("attraction", newAttraction);
        model.addAttribute("cityList", touristService.getCityList());
        model.addAttribute("tagList", touristService.getTags());
        return "attractionAddForm";
    }

    @PostMapping("/save")
    public String saveAttraction(TouristAttraction attraction) {
        touristService.addAttraction(attraction);

        return "redirect:/attraction/list";
    }

    //Reminder, Byttet om på update og edit i opgaven.
    @GetMapping("/{name}/update")
    public String updateAttraction(@PathVariable String name, Model model) {
        TouristAttraction attraction = touristService.getAttractionByName(name);

        if (attraction == null) {
            throw new IllegalArgumentException("Invalid attraction name");
        }
        model.addAttribute("attraction", attraction);
        model.addAttribute("cityList", touristService.getCityList());
        model.addAttribute("tagList", touristService.getTags());
        return "updateAttractionForm";
    }

    @PostMapping("/{nameID}/edit")
    public String editAttraction(TouristAttraction attraction) {
        touristService.editAttraction(attraction);
        return "redirect:/attraction/list";
    }

    @GetMapping("/{name}/delete")
    public String deleteAttraction(@PathVariable String name) {
        TouristAttraction attraction = touristService.getAttractionByName(name);
        touristService.deleteAttraction(attraction);
        return "redirect:/attraction/list";
    }

    @GetMapping("/{name}/tags")
    public String getTagInfo(@PathVariable String name, Model model) {
        model.addAttribute("attraction", touristService.getSpecificAttraction(name));
        return "tags";
    }
}
