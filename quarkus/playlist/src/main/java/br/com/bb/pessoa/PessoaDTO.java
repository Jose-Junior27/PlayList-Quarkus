package br.com.bb.pessoa;

import br.com.bb.playlist.PlayListDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PessoaDTO {
    private String uid;
    @NotBlank
    @Size(min = 3, message = "Informe Nome Maior")
    private String nome;
    @Email(message = "e-mail inválido.")
    private String email;
    @NotNull(message = "Campo Data de Nascimento é obrigatório")
    @Past(message = "Data precisa ser menor que atual")
    private LocalDate nascimento;
    private Collection<PlayListDTO> playLists = new HashSet<>();
}
