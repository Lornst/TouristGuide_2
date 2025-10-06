package com.example.touristguide_2.service;

import com.example.touristguide_2.model.*;
import com.example.touristguide_2.repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristService {
    private TouristRepository touristRepository;

    public TouristService(TouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }

    public List<String> getCityList() {
        return touristRepository.getCityList();
    }

    public List<String> getTagList() {
        return touristRepository.getTagList();
    }

    public List<TouristAttraction> getAttractionList() {
        return touristRepository.getAttractionList();
    }

    public void addAttraction(TouristAttraction attraction) {
        touristRepository.addAttraction(attraction);
    }

    public void deleteAttraction(TouristAttraction attraction) {
        touristRepository.deleteAttraction(attraction);
    }

    public TouristAttraction getAttraction(TouristAttraction attraction) {
        return touristRepository.getAttraction(attraction);
    }

    public void editAttraction(TouristAttraction attraction) {
        touristRepository.editAttraction(attraction);
    }
}

