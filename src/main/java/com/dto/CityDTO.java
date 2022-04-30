package com.dto;

import com.model.City;
import lombok.*;

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
        return City.class.cast(this);
    }
}
