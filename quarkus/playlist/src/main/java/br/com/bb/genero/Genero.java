package br.com.bb.genero;

import br.com.bb.musica.Musica;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name="genero")
@Getter
@Setter
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String uid;
    private String nome;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "genero")
    private Collection<Musica> musicas = new ArrayList<>();



}
