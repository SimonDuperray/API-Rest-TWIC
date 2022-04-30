package com.dto;

import com.model.City;
import lombok.*;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CityDTO {
    private String codeCommune;
    private String nomCommune;
    private String codePostal;
    private String libelleAcheminement;
    private String ligne;
    private String latitude;
    private String longitude;

    public City villeToEnt() {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(this, City.class);
    }
}
