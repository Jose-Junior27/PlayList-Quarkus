package br.com.bb.musica;

import br.com.bb.genero.Genero;
import br.com.bb.genero.GeneroService;
import br.com.bb.pessoa.Pessoa;
import br.com.bb.pessoa.PessoaService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class MusicaService {

    @Inject
    public MusicaRepository musicaRepository;
    @Inject
    public GeneroService generoService;
    @Inject
    public PessoaService pessoaService;

    public List<Musica> findAll() {

        return musicaRepository.findAll().list();
    }

    public Musica findByUid(String uuid) {

        return musicaRepository.findByUid(uuid);
    }

    public List<Musica> findByName(String nome) {

        return musicaRepository.findByName(nome);
    }

    @Transactional
    public void create(Musica musica) {
        musica.setUid(UUID.randomUUID().toString());
        Genero genero = generoService.findByUid(musica.getGenero().getUid());
        musica.setGenero(genero);
        musicaRepository.persistAndFlush(musica);
    }

    @Transactional
    public void addLike(String uidMusica, Pessoa pessoa){
        Musica musicadb = findByUid(uidMusica);
        var pessoadb = pessoaService.findByUid(pessoa.getUid());
        musicadb.addLike(pessoadb);
        musicaRepository.persistAndFlush(musicadb);
    }

    @Transactional
    public void update(Musica musica) {
        Musica musicadb = findByUid(musica.getUid());
        musicadb.setNome(musica.getNome());
        Genero generodb = generoService.findByUid(musica.getGenero().getUid());
        musicadb.setGenero(generodb);
        musicaRepository.persistAndFlush(musicadb);
    }

    @Transactional
    public void delete(String uid)  {
        Musica musicadb = findByUid(uid);
        musicaRepository.deleteById(musicadb.getId());
    }
}
