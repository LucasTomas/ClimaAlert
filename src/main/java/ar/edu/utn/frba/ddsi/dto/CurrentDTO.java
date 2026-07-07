package ar.edu.utn.frba.ddsi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CurrentDTO {
  @JsonProperty("temp_c")
  private Double temperatura;
}
