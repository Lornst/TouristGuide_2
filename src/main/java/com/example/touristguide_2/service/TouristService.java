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

    public TouristAttraction addAttraction(TouristAttraction attraction) {

        return touristRepository.addAttraction(attraction);

    }

    public TouristAttraction deleteAttraction(String name) {

        return touristRepository.deleteAttraction(name);
    }

    public TouristAttraction getAttractionByName(String name) {

        return touristRepository.getAttractionByName(name);
    }

    public TouristAttraction editAttraction(String nameID,TouristAttraction attraction) {
        return touristRepository.editAttraction(nameID, attraction);
    }

    public List<String> getCityList() {
        return touristRepository.getCityList();
    }

}

