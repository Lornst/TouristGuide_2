package com.example.touristguide_2.controller;

import com.example.touristguide_2.model.TouristAttraction;
import com.example.touristguide_2.service.TouristService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;



@WebMvcTest(TouristController.class)
public class TouristControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TouristService touristService;

    @BeforeEach
    void setUp(){
    }

    @AfterEach
    void tearDown(){
    }

    @Test
    void shouldGetAllAttractions() throws Exception{
        TouristAttraction attractionTest = new TouristAttraction("name", "description", 1, List.of(), 1);
        when(touristService.getAttractionList()).thenReturn(List.of(attractionTest));

        mockMvc.perform(get("/attraction/list"))
                .andExpect(view().name("attractionList"))
                .andExpect(status().isOk());

        verify(touristService).getAttractionList();

    }
}
