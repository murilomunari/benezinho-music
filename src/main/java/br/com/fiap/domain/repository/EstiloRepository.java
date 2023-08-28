package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Estilo;
import br.com.fiap.domain.repository.factory.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EstiloRepository implements Repository<Estilo, Long> {

    private static List<Estilo> estilos;

    static {
        estilos = new ArrayList<>();

        Estilo funk = new Estilo(1L, "Funk");
        Estilo trap = new Estilo(2L, "Trap");
        Estilo rap = new Estilo(3L, "Rap");

        estilos.addAll(Arrays.asList(trap, rap, funk));
    }

    public List<Estilo> findAll(){
        return estilos;
    }

    public Estilo findById(Long id){
        for (int i = 0; i < estilos.size(); i++){
            if(estilos.get(i).getId().equals(id)) {
                return estilos.get(i);
            }
        }
        return null;
    }

    public List<Estilo> findByName(String texto){
        List<Estilo> estilosEncontrados = new ArrayList<>();
        for (Estilo e : estilos){
            if (e.getNome().equalsIgnoreCase(texto)){
                estilosEncontrados.add(e);
            }
        }
        return estilosEncontrados;
    }

    public Estilo persist(Estilo e){
        e.setId(estilos.size()+1L);
        estilos.add(e);
        return e;
    }

}