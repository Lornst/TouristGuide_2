package com.example.touristguide_2.h2Tests;

import com.example.touristguide_2.model.TouristAttraction;
import com.example.touristguide_2.repository.TouristRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = "classpath:h2init.sql", executionPhase = BEFORE_TEST_METHOD)
public class TouristRepositoryTest {
    @Autowired
    private TouristRepository repo;

    @Test
    void testFindAllAttractions(){
        List<TouristAttraction> all = repo.getAttractionList();

        assertThat(all).isNotNull();
        assertThat(all.size()).isEqualTo(2);
        assertThat(all.get(0).getName()).isEqualTo("Tivoli");
        assertThat(all.get(1).getName()).isEqualTo("Den lille havfrue");
    }

    @Test
    void insertAndReadBack(){
        TouristAttraction attraction = new TouristAttraction();
        attraction.setName("Rundetårn");
        attraction.setDescription("Et meget stort tårn");
        attraction.setCity(4);
        attraction.setTags(List.of(1, 3));
        repo.addAttraction(attraction);
        //repo.addAttraction(new TouristAttraction("Rundetårn", "Et meget stort tårn", 4, List.of(1, 3), 3 ));
        var attractionGetted = repo.getAttraction(3);
        assertThat(attractionGetted).isNotNull();
        assertThat(attractionGetted.getName()).isEqualTo("Rundetårn");

    }
}
