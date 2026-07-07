package ar.edu.utn.frba.ddsi.schedulers;

import ar.edu.utn.frba.ddsi.dto.ClimaReponseDTO;
import ar.edu.utn.frba.ddsi.repositories.ClimaRepository;
import ar.edu.utn.frba.ddsi.repositories.RegistroClima;
import ar.edu.utn.frba.ddsi.services.ClimaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class ClimaScheduler {
  private static final Logger log = LoggerFactory.getLogger(ClimaScheduler.class);

  private final ClimaService climaService;
  private final ClimaRepository climaRepository;

  public ClimaScheduler(ClimaService climaService, ClimaRepository climaRepository) {
    this.climaService = climaService;
    this.climaRepository = climaRepository;
  }

  @Scheduled(fixedRate = 300000)
  public void obtenerYGuardarClima() {
    ClimaReponseDTO clima;

    try {
      clima = climaService.obtenerClima();
    } catch (Exception e) {
      log.error("Error al consultar WeatherAPI: {}", e.getMessage());
      return;
    }

    if (clima == null || clima.current() == null) {
      log.warn("WeatherAPI no devolvio datos; se omite este ciclo.");
      return;
    }

    RegistroClima registro = new RegistroClima(clima, Instant.now());

    climaRepository.agregar(registro);
    log.info("Registro guardado: {}", registro);
  }
}
