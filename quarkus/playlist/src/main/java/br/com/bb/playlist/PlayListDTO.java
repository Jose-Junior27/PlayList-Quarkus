package br.com.bb.playlist;

import br.com.bb.musica.MusicaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlayListDTO {
    private String uid;
    private String nome;
    private String descricao;
    private Collection<MusicaDTO> musicas = new HashSet<>();
}
