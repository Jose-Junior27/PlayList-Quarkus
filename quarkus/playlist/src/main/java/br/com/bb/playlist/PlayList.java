package br.com.bb.playlist;

import br.com.bb.musica.Musica;
import br.com.bb.pessoa.Pessoa;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name="playlist")
@Getter
@Setter
public class PlayList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String uid;
    @NotBlank
    @Size(min = 3, message = "Informe Nome Maior")
    private String nome;
    @NotBlank
    @Size(min = 3, message = "Informe Descrição Maior")
    private String descricao;

    @ManyToMany(mappedBy = "playLists")
    private Collection<Pessoa> pessoaPlayLists = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "playlist_musicas",
            joinColumns = { @JoinColumn(name = "playlist_id") },
            inverseJoinColumns = { @JoinColumn(name = "musica_id") },
            uniqueConstraints = {@UniqueConstraint(columnNames = {"playlist_id", "musica_id"})}
    )
    private Collection<Musica> musicas = new HashSet<>();

    public void add(Musica musica) {
        if (musica != null) {
            this.musicas.add(musica);
        }
    }
}
