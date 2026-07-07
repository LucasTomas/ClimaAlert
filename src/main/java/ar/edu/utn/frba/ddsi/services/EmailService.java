package ar.edu.utn.frba.ddsi.services;

import ar.edu.utn.frba.ddsi.dto.LocationDTO;
import ar.edu.utn.frba.ddsi.repositories.RegistroClima;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class EmailService {
  private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

  private final String[] destinatarios = new String[] {"admin@clima.com", "emergencias@clima.com", "meteorologia@clima.com"};

  private final JavaMailSender mailSender;

  public EmailService(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  public void enviarAlerta(RegistroClima registro) {
    LocationDTO location = registro.datosClima().location();

    SimpleMailMessage mensaje = new SimpleMailMessage();
    mensaje.setTo(destinatarios);
    mensaje.setSubject("[ClimaAlert] Alerta climatica en " + (location != null ? location.name() : "N/D"));
    mensaje.setText(construirCuerpo(registro));
    mailSender.send(mensaje);
  }

  private String construirCuerpo(RegistroClima registro) {
    LocationDTO location = registro.datosClima().location();
    var current = registro.datosClima().current();

    return """
        Se detectaron condiciones climaticas criticas.

        Fecha de la consulta: %s

        --- Ubicacion ---
        Nombre: %s
        Region: %s
        Pais: %s
        Hora local: %s

        --- Detalle completo del clima ---
        Temperatura: %.1f C
        Humedad: %d%%

        Este es un correo generado automaticamente por ClimaAlert.
        """.formatted(
        FORMATO_FECHA.format(registro.fechaConsulta()),
        location != null ? location.name() : "N/D",
        location != null ? location.region() : "N/D",
        location != null ? location.country() : "N/D",
        location != null ? location.localtime() : "N/D",
        current.temperatura(),
        current.humedad()
    );
  }
}
