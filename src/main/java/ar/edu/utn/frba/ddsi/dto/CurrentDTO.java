package ar.edu.utn.frba.ddsi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CurrentDTO(
    @JsonProperty("temp_c") Double temperatura,
    @JsonProperty("humidity") Integer humedad
) {}
