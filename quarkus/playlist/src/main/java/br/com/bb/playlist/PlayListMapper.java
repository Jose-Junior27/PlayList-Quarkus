package br.com.bb.playlist;

import java.util.ArrayList;
import java.util.Collection;

import static br.com.bb.musica.MusicaMapper.collectionMusicaToDTO;

public class PlayListMapper {

    public static PlayListDTO playListToDTO(PlayList playList){
        if (playList == null){
            return null;
        }
        return new PlayListDTO(playList.getUid(), playList.getNome(), playList.getDescricao(), collectionMusicaToDTO(playList.getMusicas())) ;
    }

    public static PlayList dtoToPlayList(PlayListDTO playListDTO){
        var playList = new PlayList();
        playList.setUid(playListDTO.getUid());
        playList.setNome(playListDTO.getNome());
        playList.setDescricao(playListDTO.getDescricao());
        //playList.setMusicas(playListDTO.getMusicas());
        return playList;
    }

    public static PlayList dtoToPlayListUID(PlayListDTO playListDTO){
        var playList = new PlayList();
        playList.setUid(playListDTO.getUid());
        return playList;
    }

    public static Collection<PlayListDTO> collectionPlayListToDTO(Collection<PlayList> playLists){
        if (playLists == null){
            return null;
        }
        Collection<PlayListDTO> list = new ArrayList<>();
        playLists.stream().forEach(play -> {
            list.add(new PlayListDTO(play.getUid(), play.getNome(), play.getDescricao(), collectionMusicaToDTO( play.getMusicas()) ) );
        });

        return list;

    }
}
