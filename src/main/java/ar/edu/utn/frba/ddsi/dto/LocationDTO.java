package ar.edu.utn.frba.ddsi.dto;

public record LocationDTO(
    String name,
    String region,
    String country,
    Double lat,
    Double lon,
    String tz_id,
    String localtime
) { }
