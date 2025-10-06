package com.example.touristguide_2.controller;

import com.example.touristguide_2.model.*;
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
        List<TouristAttraction> attractionList = touristService.getAttractionList();
        model.addAttribute("list", attractionList);
        return "attractionList";
    }

    //Slettet name

    @GetMapping("/add")
    public String addAttraction(Model model) {
        TouristAttraction newAttraction = new TouristAttraction();

        model.addAttribute("attraction", newAttraction);
        model.addAttribute("cityList", touristService.getCityList());
        model.addAttribute("tagList", touristService.getTagList());
        return "attractionAddForm";
    }

    @PostMapping("/save")
    public String saveAttraction(@ModelAttribute TouristAttraction attraction) {
        touristService.addAttraction(attraction);

        return "redirect:/attraction/list";
    }

    //Reminder, Byttet om på update og edit i opgaven.
    @GetMapping("/update")
    public String updateAttraction(@ModelAttribute TouristAttraction attraction, Model model) {

        if (attraction == null) {
            throw new IllegalArgumentException("Invalid attraction name");
        }
        model.addAttribute("attraction", attraction);
        model.addAttribute("cityList", touristService.getCityList());
        model.addAttribute("tagList", touristService.getTagList());
        return "updateAttractionForm";
    }

    @PostMapping("/edit")
    public String editAttraction(@ModelAttribute TouristAttraction attraction) {
        touristService.editAttraction(attraction);
        return "redirect:/attraction/list";
    }

    @GetMapping("/delete")
    public String deleteAttraction(@ModelAttribute TouristAttraction attraction) {
        touristService.deleteAttraction(attraction);
        return "redirect:/attraction/list";
    }

    @GetMapping("/tags")
    public String getTagInfo(@ModelAttribute TouristAttraction attraction, Model model) {
        model.addAttribute("attraction", touristService.getAttraction(attraction));
        return "tags";
    }
}
