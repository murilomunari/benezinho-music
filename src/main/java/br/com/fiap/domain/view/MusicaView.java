package br.com.fiap.domain.view;

import br.com.fiap.domain.entity.Artista;
import br.com.fiap.domain.entity.Estilo;
import br.com.fiap.domain.entity.Musica;
import br.com.fiap.domain.repository.ArtistaRepository;
import br.com.fiap.domain.repository.EstiloRepository;
import br.com.fiap.domain.service.MusicaService;


import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MusicaView {

    List<Artista> artistas = new ArrayList<>();
    List<Estilo> estilos = new ArrayList<>();

    private MusicaService service = new MusicaService();

    public void menu() {

        int escolha = 0;

        do {
            escolha = Integer.parseInt( JOptionPane.showInputDialog( "SELECIONE uma opção: \n" +
                    "1 - musica \n" +
                    "2 - id \n" +
                    "3 - Procurar musica pelo nome \n" +
                    "4 - Encerrar programa" ) );

            switch (escolha) {
                case 1 -> {
                    Musica musica = addMusica();
                    JOptionPane.showMessageDialog( null, musica );
                    menu();
                }

                case 2 -> {
                    System.out.println();
                    menu();
                }

                case 3 -> {
                    String nome = JOptionPane.showInputDialog( "Nome da musica: " );
                    List<Musica> list = service.findByName( nome );
                    list.forEach( m -> {
                        JOptionPane.showMessageDialog( null, m );
                    } );

                    menu();
                }

            }


        } while (escolha != 1 || escolha != 2 || escolha != 3 || escolha != 4);
    }

    public Musica addMusica() {

        Musica m = null;

        ArtistaRepository artistaRepository = new ArtistaRepository();
        EstiloRepository estiloRepository = new EstiloRepository();

        artistas = artistaRepository.findAll();
        estilos = estiloRepository.findAll();

        if (artistas.size() > 0 && estilos.size() > 0) {

            Artista artista = (Artista) JOptionPane.showInputDialog( null, "coloque o Artista", "Seleção de Artistas", JOptionPane.QUESTION_MESSAGE, null, artistas.toArray(), artistas.get( 0 ) );

            if (Objects.isNull( artista )) return m;

            Estilo estilo = (Estilo) JOptionPane.showInputDialog( null, "coloque o  Estilo", "Seleção de Estilos Musicais", JOptionPane.QUESTION_MESSAGE, null, estilos.toArray(), estilos.get( 0 ) );
            if (Objects.isNull( estilo )) return m;

            String musica = "";

            do {
                musica = JOptionPane.showInputDialog( "Qual musica quer adicionar" );
            } while (musica == "");


            m = new Musica();
            m.addArtista( artista ).setEstilo( estilo ).setNome( musica );
            Musica persist = service.persist( m );

            if (Objects.nonNull( persist )) {
                JOptionPane.showMessageDialog( null, "A Musica foi adicionada!!!" );
            } else {
                JOptionPane.showMessageDialog( null, "A musica não foi adcionada!! " );
            }
        }
        return m;
    }

}