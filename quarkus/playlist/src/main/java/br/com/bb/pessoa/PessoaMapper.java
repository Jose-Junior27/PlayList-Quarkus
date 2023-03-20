package br.com.bb.pessoa;

import java.util.ArrayList;
import java.util.Collection;

import static br.com.bb.playlist.PlayListMapper.collectionPlayListToDTO;

public class PessoaMapper {

    public static PessoaDTO pessoaToDTO(Pessoa pessoa){
        if (pessoa == null){
            return null;
        }
        return new PessoaDTO(pessoa.getUid(), pessoa.getNome(), pessoa.getEmail(), pessoa.getNascimento(), collectionPlayListToDTO(pessoa.getPlayLists()) );
        //return new PessoaDTO(pessoa.getUid(), pessoa.getNome(), pessoa.getEmail(), pessoa.getNascimento() );
    }

    public static Pessoa dtoToPessoa(PessoaDTO pessoaDTO){
        var pessoa = new Pessoa();
        pessoa.setUid(pessoaDTO.getUid());
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setEmail(pessoaDTO.getEmail());
        pessoa.setNascimento(pessoaDTO.getNascimento());
        return pessoa;
    }

    public static Pessoa dtoToPessoaUID(PessoaResumoDTO pessoaDTO){
        var pessoa = new Pessoa();
        pessoa.setUid(pessoaDTO.getUid());
        return pessoa;
    }

    public static Collection<PessoaResumoDTO> collectionPessoaToDTO(Collection<Pessoa> pessoas){
        if (pessoas == null){
            return null;
        }
        Collection<PessoaResumoDTO> list = new ArrayList<>();
        pessoas.stream().forEach(p -> {
            list.add(new PessoaResumoDTO(p.getUid(), p.getNome() ) );
        });
        return list;

    }
}
