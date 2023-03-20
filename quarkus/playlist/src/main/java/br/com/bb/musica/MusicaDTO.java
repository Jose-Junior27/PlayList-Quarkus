package br.com.bb.musica;

import br.com.bb.genero.GeneroDTO;
import br.com.bb.pessoa.PessoaResumoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.HashSet;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MusicaDTO {
    private String uid;
    private String nome;
    private GeneroDTO genero;
    private Collection<PessoaResumoDTO> pessoasLike = new HashSet<>();
}
