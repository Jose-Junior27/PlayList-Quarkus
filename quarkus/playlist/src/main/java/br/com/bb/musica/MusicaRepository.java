package br.com.bb.musica;

import br.com.bb.genero.Genero;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class MusicaRepository implements PanacheRepository<Musica> {
    public Musica findByUid(String uid) {

        return find("SELECT m FROM Musica m JOIN FETCH m.genero g WHERE m.uid = ?1", uid).firstResult();

    }

    public List<Musica> findByName(String name) {

        return find("SELECT m FROM Musica m JOIN FETCH m.genero g WHERE m.nome = ?1", name).list();
    }
}
