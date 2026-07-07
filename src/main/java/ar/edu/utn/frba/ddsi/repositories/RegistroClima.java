package ar.edu.utn.frba.ddsi.repositories;

import ar.edu.utn.frba.ddsi.dto.ClimaReponseDTO;

import java.time.Instant;

public record RegistroClima(
    ClimaReponseDTO datosClima,
    Instant fechaConsulta
) {}
