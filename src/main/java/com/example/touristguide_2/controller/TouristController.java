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
    public String saveAttraction(@ModelAttribute TouristAttraction attraction, @ModelAttribute City city, @ModelAttribute List<Tag> tagList) {
        attraction.setTags(tagList);
        attraction.setCity(city);
        touristService.addAttraction(attraction);

        return "redirect:/attraction/list";
    }

    //Reminder, Byttet om på update og edit i opgaven.
    @GetMapping("/{id}/update")
    public String updateAttraction(@PathVariable int id, Model model) {
        TouristAttraction attraction = touristService.getAttraction(id);

        if (attraction == null) {
            throw new IllegalArgumentException("Invalid attraction name");
        }
        model.addAttribute("attraction", attraction);
        model.addAttribute("cityList", touristService.getCityList());
        model.addAttribute("tagList", touristService.getTagList());
        return "updateAttractionForm";
    }

    @PostMapping("/{id}/edit")
    public String editAttraction(@PathVariable int id, TouristAttraction attraction) {
        touristService.editAttraction(id, attraction);
        return "redirect:/attraction/list";
    }

    @GetMapping("/{id}/delete")
    public String deleteAttraction(@PathVariable int id) {
        touristService.deleteAttraction(id);
        return "redirect:/attraction/list";
    }

    @GetMapping("/{id}/tags")
    public String getTagInfo(@PathVariable int id, Model model) {
        model.addAttribute("attraction", touristService.getAttraction(id));
        return "tags";
    }
}
