package ar.edu.utn.frba.ddsi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClimaResponseDto {
  private LocationDTO location;
  private CurrentDTO current;
}
