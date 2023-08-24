package br.com.fiap.domain.service;

import br.com.fiap.domain.entity.Artista;
import br.com.fiap.domain.repository.ArtistaRepository;

import java.util.Objects;

public class ArtistaService {

    public static Artista persist(Artista a){
        ArtistaRepository artistaRepository = new ArtistaRepository();

        if (Objects.isNull(a.getNome())){
            return null;
        }
        return artistaRepository.persist(a);
    }
}
