package br.com.bb.pessoa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PessoaResumoDTO {
    private String uid;
    private String nome;

}
