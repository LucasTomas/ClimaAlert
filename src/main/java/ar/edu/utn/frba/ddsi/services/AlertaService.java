package ar.edu.utn.frba.ddsi.services;

import ar.edu.utn.frba.ddsi.repositories.ClimaRepository;
import ar.edu.utn.frba.ddsi.repositories.RegistroClima;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlertaService {
  private static final Logger log = LoggerFactory.getLogger(AlertaService.class);

  @Value("${alerta.umbral.temperatura}")
  private double umbralTemperatura;

  @Value("${alerta.umbral.humedad}")
  private double umbralHumedad;

  private final ClimaRepository climaRepository;
  private final EmailService emailService;

  private RegistroClima ultimoRegistroAlertado;

  public AlertaService(ClimaRepository climaRepository, EmailService emailService) {
    this.climaRepository = climaRepository;
    this.emailService = emailService;
  }

  public void analizarUltimoRegistro() {
    Optional<RegistroClima> ultimoOpt = climaRepository.obtenerUltimo();

    if (ultimoOpt.isEmpty()) {
      return;
    }

    RegistroClima ultimo = ultimoOpt.get();

    if (ultimo.equals(ultimoRegistroAlertado)) {
      return;
    }

    if (esCondicionCritica(ultimo)) {
      log.info("Condicion critica detectada: {}", ultimo);
      emailService.enviarAlerta(ultimo);
      ultimoRegistroAlertado = ultimo;
    }
  }

  private boolean esCondicionCritica(RegistroClima registro) {
    var current = registro.datosClima().current();
    return current != null
        && current.temperatura() != null
        && current.humedad() != null
        && current.temperatura() > umbralTemperatura
        && current.humedad() > umbralHumedad;
  }
}
