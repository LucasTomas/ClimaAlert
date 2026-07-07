package ar.edu.utn.frba.ddsi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LocationDTO {
  private String name;
  private String region;
  private String country;
  private Double lat;
  private Double lon;
  private String tz_id;
  private String localtime;
}
