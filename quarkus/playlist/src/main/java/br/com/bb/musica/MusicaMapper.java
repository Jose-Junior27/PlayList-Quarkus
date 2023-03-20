package br.com.bb.musica;

import java.util.ArrayList;
import java.util.Collection;

import static br.com.bb.genero.GeneroMapper.dtoToGenero;
import static br.com.bb.genero.GeneroMapper.generoToDTO;
import static br.com.bb.pessoa.PessoaMapper.collectionPessoaToDTO;

public class MusicaMapper {

    public static MusicaDTO musicaToDTO(Musica musica){
        if (musica == null){
            return null;
        }
        return new MusicaDTO(musica.getUid(), musica.getNome(), generoToDTO(musica.getGenero()), collectionPessoaToDTO(musica.getPessoasLike()) );
        //return new MusicaDTO(musica.getUid(), musica.getNome(), generoToDTO(musica.getGenero()) );
    }

    public static Musica dtoToMusica(MusicaDTO musicaDTO){
        var musica = new Musica();
        musica.setUid(musicaDTO.getUid());
        musica.setNome(musicaDTO.getNome());
        musica.setGenero(dtoToGenero( musicaDTO.getGenero()) );
        return musica;
    }

    public static Musica dtoToMusicaUID(MusicaDTO musicaDTO){
        var musica = new Musica();
        musica.setUid(musicaDTO.getUid());
        return musica;
    }

    public static Collection<MusicaDTO> collectionMusicaToDTO(Collection<Musica> musicas){
        if (musicas == null){
            return null;
        }
        Collection<MusicaDTO> list = new ArrayList<>();
        musicas.stream().forEach(musica -> {
            list.add(new MusicaDTO(musica.getUid(), musica.getNome(), generoToDTO(musica.getGenero()), collectionPessoaToDTO(musica.getPessoasLike()) ));
            //list.add(new MusicaDTO(musica.getUid(), musica.getNome(), generoToDTO(musica.getGenero()) ));
        });

        return list;

    }
}
