package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Artista;
import br.com.fiap.domain.repository.factory.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArtistaRepository implements Repository<Artista, Long> {

    private static List<Artista> artistas;

    static {

        artistas = new ArrayList<>();

        Artista mcGp = new Artista(1L, "Mc GP");
        Artista kaue = new Artista(2L, "kaue");
        Artista kayBlack = new Artista(3L, "KayBlack");

        artistas.addAll(Arrays.asList(mcGp, kaue, kayBlack));
    }

    public List<Artista> findAll(){
        return  artistas;
    }

    public Artista findById(Long id){
        for (int i = 0; i < artistas.size(); i++){
            if (artistas.get(i).getId().equals(id)){
                return artistas.get(i);
            }
        }
        return null;
    }

    public List<Artista> findByName(String texto){
        List<Artista> artistasEncontrados = new ArrayList<>();
        for (Artista a : artistas) {
            if (a.getNome().equalsIgnoreCase(texto)){
                artistasEncontrados.add(a);
            }
        }
        return artistasEncontrados;
    }

    public Artista persist(Artista a){
        a.setId(artistas.size() + 1L);
        artistas.add(a);
        return a;
    }

}