package br.com.bb.pessoa;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PessoaRepository implements PanacheRepository<Pessoa> {

    public Pessoa findByUid(String uid) {
        return find("SELECT m FROM Pessoa m WHERE m.uid = ?1", uid).firstResult();

    }

    public List<Pessoa> findByName(String name) {
        return find("SELECT m FROM Pessoa m WHERE m.nome = ?1", name).list();
    }

    public Pessoa findByMail(String email) {
        return find("SELECT m FROM Pessoa m WHERE m.email = ?1", email).firstResult();

    }
}
