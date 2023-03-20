package br.com.bb.genero;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GeneroDTO {
    private String uid;
    @NotEmpty(message = "Nome obrigatório")
    @Length(min = 3, message = "nome inválido, menos de 3 caracteres")
    private String nome;
}
