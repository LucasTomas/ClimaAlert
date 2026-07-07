package ar.edu.utn.frba.ddsi.services;

import ar.edu.utn.frba.ddsi.dto.ClimaResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ClimaService {

  @Value("${weather.api.keye}")
  private String API_KEY;

  private final RestClient restClient = RestClient.create();

  public ClimaResponseDto obtenerClima(String ciudad) {
    return restClient.get()
        .uri("https://api.weatherapi.com/v1/current.json?key={key}&q={ciudad}",
            API_KEY, ciudad)
        .retrieve()
        .body(ClimaResponseDto.class);
  }
}
