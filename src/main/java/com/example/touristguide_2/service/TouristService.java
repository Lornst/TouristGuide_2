package com.example.touristguide_2.service;

import com.example.touristguide_2.model.*;
import com.example.touristguide_2.repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TouristService {

    final private TouristRepository touristRepository;

    public TouristService(TouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }

    public Map<Integer, String> getTagList() {
        return touristRepository.getTagList();
    }

    public List<TouristAttraction> getAttractionList() {
        return touristRepository.getAttractionList();
    }

    public TouristAttraction getAttraction(int id) {
        return touristRepository.getAttraction(id);
    }

    public void addAttraction(TouristAttraction attraction) {
        touristRepository.addAttraction(attraction);
    }

    public void deleteAttraction(int id) {
        touristRepository.deleteAttraction(id);
    }

    public void editAttraction(TouristAttraction attraction) {
        touristRepository.editAttraction(attraction);
    }

    public Map<Integer, String> getCityList() {
        return touristRepository.getCityList();
    }

}

