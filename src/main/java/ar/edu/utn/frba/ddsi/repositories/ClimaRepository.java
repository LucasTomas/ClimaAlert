package ar.edu.utn.frba.ddsi.repositories;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ClimaRepository {
  private List<RegistroClima> registroClimas;

  public ClimaRepository() {
    registroClimas = new ArrayList<>();
  }

  public void agregar(RegistroClima registro) { registroClimas.add(registro); }

  public Optional<RegistroClima> obtenerUltimo() {
    if (registroClimas.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(registroClimas.get(registroClimas.size() - 1));
  }

  public List<RegistroClima> obtenerTodos() {
    return List.copyOf(registroClimas);
  }

}
