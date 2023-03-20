package br.com.bb.playlist;

import br.com.bb.musica.Musica;
import br.com.bb.musica.MusicaService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class PlayListService {

    @Inject
    PlayListRepository playListRepository;
    @Inject
    MusicaService musicaService;

    public List<PlayList> findAll() {

        return playListRepository.findAll().list();
    }

    public PlayList findByUid(String uuid) {

        return playListRepository.findByUid(uuid);
    }

    public List<PlayList> findByName(String nome) {

        return playListRepository.findByName(nome);
    }

    @Transactional
    public void create(PlayList playList) {
        playList.setUid(UUID.randomUUID().toString());
        playListRepository.persistAndFlush(playList);
    }

    @Transactional
    public void addMusic(String uidPlayList, Musica musica){
        PlayList playListdb = findByUid(uidPlayList);
        var music = musicaService.findByUid(musica.getUid());
        playListdb.add(music);
        playListRepository.persistAndFlush(playListdb);
    }

    @Transactional
    public void update(PlayList playList) {
        PlayList playListdb = findByUid(playList.getUid());
        playListdb.setNome(playList.getNome());
        playListdb.setDescricao(playList.getDescricao());
        playListRepository.persistAndFlush(playListdb);
    }

    @Transactional
    public void delete(String uid)  {
        PlayList playlistdb = findByUid(uid);
        playListRepository.deleteById(playlistdb.getId());
    }
}
