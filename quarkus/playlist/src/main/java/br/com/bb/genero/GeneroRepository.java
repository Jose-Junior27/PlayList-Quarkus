package br.com.bb.genero;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class GeneroRepository implements PanacheRepository<Genero> {
    public Genero findByUid(String uid) {
        return find("SELECT g FROM Genero g WHERE g.uid = ?1", uid).firstResult();
    }

    public List<Genero> findByName(String name) {

        return find("SELECT g FROM Genero g WHERE g.nome = ?1", name).list();
    }
}
