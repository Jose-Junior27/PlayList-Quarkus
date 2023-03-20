package br.com.bb.pessoa;

import br.com.bb.playlist.PlayList;
import br.com.bb.playlist.PlayListService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class PessoaService {
    @Inject
    public PessoaRepository pessoaRepository;
    @Inject
    public PlayListService playListService;

    public List<Pessoa> findAll() {

        return pessoaRepository.findAll().list();
    }

    public Pessoa findByUid(String uuid) {

        return pessoaRepository.findByUid(uuid);
    }

    public List<Pessoa> findByName(String nome) {

        return pessoaRepository.findByName(nome);
    }

    public Pessoa findByMail(String mail) {

        return pessoaRepository.findByMail(mail);
    }

    @Transactional
    public void create(Pessoa pessoa) {
        pessoa.setUid(UUID.randomUUID().toString());
        pessoaRepository.persistAndFlush(pessoa);
    }

    @Transactional
    public void addPlayList(String uidPessoa, PlayList playList){
        Pessoa pessoadb = findByUid(uidPessoa);
        var play = playListService.findByUid(playList.getUid());
        pessoadb.add(play);
        pessoaRepository.persistAndFlush(pessoadb);
    }

    @Transactional
    public void update(Pessoa pessoa) {
        Pessoa pessoadb = findByUid(pessoa.getUid());
        pessoadb.setNome(pessoa.getNome());
        pessoadb.setEmail(pessoa.getEmail());
        pessoadb.setNascimento(pessoa.getNascimento());
        pessoaRepository.persistAndFlush(pessoadb);
    }

    @Transactional
    public void delete(String uid)  {
        Pessoa pessoadb = findByUid(uid);
        pessoaRepository.deleteById(pessoadb.getId());
    }
}
