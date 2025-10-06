package com.example.touristguide_2.service;

import com.example.touristguide_2.model.*;
import com.example.touristguide_2.repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristService {

    final private TouristRepository touristRepository;

    public TouristService(TouristRepository touristRepository) {
        this.touristRepository = touristRepository;

    }

    public List<Tag> getTagList() {
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

    public void editAttraction(int id, TouristAttraction attraction) {
        touristRepository.editAttraction(id, attraction);
    }

    public List<City> getCityList() {
        return touristRepository.getCityList();
    }

}

