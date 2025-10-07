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
        model.addAttribute("cityList", touristService.getCityList());
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
    @PostMapping("/update")
    public String updateAttraction(@BindParam int id, Model model) {
        TouristAttraction attraction = touristService.getAttraction(id);

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

    @PostMapping("/delete")
    public String deleteAttraction(@BindParam int id) {
        touristService.deleteAttraction(id);
        return "redirect:/attraction/list";
    }

    @PostMapping("/tags")
    public String getTagInfo(@BindParam int id, Model model) {
        model.addAttribute("attraction", touristService.getAttraction(id));
        model.addAttribute("tagList", touristService.getTagList());
        return "tags";
    }
}
