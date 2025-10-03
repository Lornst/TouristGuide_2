package com.example.touristguide_2.service;

import com.example.touristguide_2.model.TouristAttraction;
import com.example.touristguide_2.repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristService {

    final private TouristRepository touristRepository;

    public TouristService(TouristRepository touristRepository) {
        this.touristRepository = touristRepository;

    }

    public List<String> getTags() {

        return touristRepository.getTags();
    }

    public List<TouristAttraction> getAllAttractions() {

        return touristRepository.getAll();
    }

    public TouristAttraction getSpecificAttraction(String name) {

        return touristRepository.getAttractionByName(name);
    }

    public void addAttraction(TouristAttraction attraction) {
        touristRepository.addAttraction(attraction);
    }

    public void deleteAttraction(TouristAttraction attraction) {
        touristRepository.deleteAttraction(attraction);
    }

    public TouristAttraction getAttractionByName(String name) {

        return touristRepository.getAttractionByName(name);
    }

    public void editAttraction(TouristAttraction attraction) {
        touristRepository.editAttraction(attraction);
    }

    public List<String> getCityList() {
        return touristRepository.getCityList();
    }

}

