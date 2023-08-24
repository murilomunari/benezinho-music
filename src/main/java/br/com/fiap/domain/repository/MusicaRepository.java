package br.com.fiap.domain.repository;


import br.com.fiap.domain.entity.Musica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MusicaRepository implements Repository<Musica, Long> {

    private static List<Musica> musicas;

    static {
        Musica drill = new Musica(1L, "drill");
        Musica funk = new Musica(2L, "funk");

        musicas = new ArrayList<>();

        musicas.addAll(Arrays.asList(drill, funk));
    }
    public List<Musica> findAll(){
        return musicas;
    }

    public Musica findById(Long id){
        for (Musica m : musicas){
            if(m.getId().equals(id)){
                return m;
            }
        }
        return null;
    }



    public List<Musica> findByName(String texto){
        List<Musica> retorno = new ArrayList<>();
        for (Musica m : musicas){
            if (m.getNome().equalsIgnoreCase(texto)){
                retorno.add(m);
            }
        }
        return retorno;
    }

    public Musica persist(Musica m){
        m.setId(musicas.size() + 1L);
        musicas.add(m);
        return m;
    }

}
