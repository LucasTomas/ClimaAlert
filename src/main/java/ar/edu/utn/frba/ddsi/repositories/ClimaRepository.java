package ar.edu.utn.frba.ddsi.repositories;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClimaRepository {
  private List<RegistroClima> registroClimas;

  public ClimaRepository() {
    registroClimas = new ArrayList<>();
  }
}
