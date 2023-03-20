package br.com.bb.musica;

import br.com.bb.genero.Genero;
import br.com.bb.pessoa.Pessoa;
import br.com.bb.playlist.PlayList;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name="musica")
@Getter
@Setter
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String uid;
    @NotBlank
    @Size(min = 3, message = "Informe Nome Maior")
    private String nome;
    @ManyToOne(fetch = FetchType.LAZY)
    private Genero genero;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "musica_likes",
            joinColumns = { @JoinColumn(name = "musica_id") },
            inverseJoinColumns = { @JoinColumn(name = "pessoa_id") },
            uniqueConstraints = {@UniqueConstraint(columnNames = {"musica_id", "pessoa_id"})}
    )
    private Collection<Pessoa> pessoasLike = new HashSet<>();

    @ManyToMany(mappedBy = "musicas")
    private Collection<PlayList> playlistMusica = new HashSet<>();

    public void addLike(Pessoa pessoa) {
        if (pessoa != null) {
            this.pessoasLike.add(pessoa);
        }
    }

}
