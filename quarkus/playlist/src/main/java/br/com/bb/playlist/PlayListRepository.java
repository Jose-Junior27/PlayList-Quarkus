package br.com.bb.playlist;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PlayListRepository implements PanacheRepository<PlayList> {

    public PlayList findByUid(String uid) {
        return find("SELECT p FROM PlayList p WHERE p.uid = ?1", uid).firstResult();
    }

    public List<PlayList> findByName(String name) {
        return find("SELECT p FROM PlayList p WHERE p.nome = ?1", name).list();
    }
}
