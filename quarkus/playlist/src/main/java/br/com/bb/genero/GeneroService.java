package br.com.bb.genero;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class GeneroService {

    @Inject
    public GeneroRepository generoRespository;

    public List<Genero> findAll() {

        return generoRespository.findAll().list();
    }

    public Genero findByUid(String uuid) {

        return generoRespository.findByUid(uuid);
    }

    public List<Genero> findByName(String nome) {

        return generoRespository.findByName(nome);
    }

    @Transactional
    public void create(Genero genero) {
        genero.setUid(UUID.randomUUID().toString());
        generoRespository.persistAndFlush(genero);
    }

    @Transactional
    public void update(Genero genero) {
        Genero generoBase = findByUid(genero.getUid());
        generoBase.setNome(genero.getNome());
        generoRespository.persistAndFlush(generoBase);
    }

    @Transactional
    public void remove(String uid) {
        generoRespository.delete(findByUid(uid));
    }
}
