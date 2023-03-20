package br.com.bb.pessoa;

import br.com.bb.musica.Musica;
import br.com.bb.playlist.PlayList;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name="pessoa")
@Getter
@Setter
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String uid;
    @NotBlank
    @Size(min = 3, message = "Informe Nome Maior")
    private String nome;
    @Email(message = "e-mail inválido.")
    private String email;
    @NotNull(message = "Campo Data de Nascimento é obrigatório")
    @Past(message = "Data precisa ser menor que atual")
    private LocalDate nascimento;

    @ManyToMany(mappedBy = "pessoasLike")
    private Collection<Musica> musicasLike = new HashSet<>();

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "pessoa_playlist",
            joinColumns = { @JoinColumn(name = "playlist_id") },
            inverseJoinColumns = { @JoinColumn(name = "pessoa_id") },
            uniqueConstraints = {@UniqueConstraint(columnNames = {"playlist_id", "pessoa_id"})}
    )
    private Collection<PlayList> playLists = new HashSet<>();

    public void add(PlayList playList) {
        if (playList != null) {
            this.playLists.add(playList);
        }
    }
}
