package br.com.bb;

import br.com.bb.genero.Genero;
import br.com.bb.genero.GeneroService;
import br.com.bb.musica.Musica;
import br.com.bb.musica.MusicaService;
import br.com.bb.pessoa.Pessoa;
import br.com.bb.pessoa.PessoaService;
import br.com.bb.playlist.PlayList;
import br.com.bb.playlist.PlayListService;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.time.LocalDate;

@ApplicationScoped
public class LifecycleBean {

    @Inject
    public GeneroService generoService;
    @Inject
    public MusicaService musicaService;
    @Inject
    public PessoaService pessoaService;

    @Inject
    public PlayListService playListService;

    private static final Logger LOGGER = Logger.getLogger("MyLifecycleBean");

    public void onStart(@Observes StartupEvent ev) {

        Genero genero1 = new Genero();
        genero1.setNome("Sertanejo");
        genero1.setUid("7a582795-3797-46ec-9a0a-ea0981320923");
        generoService.create(genero1);

        Genero genero2 = new Genero();
        genero2.setNome("Popular");
        genero2.setUid("001d22d6-28e0-4aff-a103-456c5b2cbfc9");
        generoService.create(genero2);

        Genero genero3 = new Genero();
        genero3.setNome("Rock");
        genero3.setUid("be5a943e-29e5-4cf5-b014-aa09907f4f8d");
        generoService.create(genero3);

        Genero genero4 = new Genero();
        genero4.setNome("Gospel");
        genero4.setUid("e0dfe368-3c5f-4502-b9be-efc46098c6e4");
        generoService.create(genero4);

        Genero genero5 = new Genero();
        genero5.setNome("Funk");
        genero5.setUid("cfdb2913-8af5-4ac7-aa24-407b10316e4a");
        generoService.create(genero5);

        Genero genero6 = new Genero();
        genero6.setNome("Samba");
        genero6.setUid("377e064a-c2de-11ed-8112-325096b39f47");
        generoService.create(genero6);

        Genero genero7 = new Genero();
        genero7.setNome("Rap");
        genero7.setUid("5b5158d8-c2de-11ed-a52a-325096b39f47");
        generoService.create(genero7);

        Genero genero8 = new Genero();
        genero8.setNome("MPB");
        genero8.setUid("5b5158d8-c2de-11ed-a52a-325854139f47");
        generoService.create(genero8);

        Genero genero9 = new Genero();
        genero9.setNome("Pagode");
        genero9.setUid("8fabad5e-c2de-11ed-a696-325096b39f47");
        generoService.create(genero9);

        Genero genero10 = new Genero();
        genero10.setNome("Forro");
        genero10.setUid("8fabad5e-c2de-11ed-a696-325074128f47");
        generoService.create(genero10);

        Genero genero11 = new Genero();
        genero11.setNome("Jazz");
        genero11.setUid("b1cbf9d4-c2de-11ed-aab0-325096b39f47");
        generoService.create(genero11);

        Genero genero12 = new Genero();
        genero12.setNome("Eletrônica");
        genero12.setUid("b1cbf9d4-c2de-11ed-aab0-325085726662");
        generoService.create(genero12);

        Genero genero13 = new Genero();
        genero13.setNome("Axé");
        genero13.setUid("ecd75f96-c2de-11ed-a514-325096b39f47");
        generoService.create(genero13);

        Musica musica = new Musica();
        musica.setNome("Vida Boa");
        musica.setGenero(genero1);
        musica.setUid("c5c9dced-cd71-4dcd-b96d-86139a5728c0");
        musicaService.create(musica);

        Musica musica2 = new Musica();
        musica2.setNome("Agita Ai");
        musica2.setGenero(genero1);
        musica2.setUid("74deedcd-531e-4e2f-90c1-7ac83a703faa");
        musicaService.create(musica2);

        Musica musica3 = new Musica();
        musica3.setNome("Aleluia");
        musica3.setGenero(genero4);
        musica3.setUid("02742253-88f0-4d6f-830f-6791eae9b838");
        musicaService.create(musica3);

        Pessoa pessoa = new Pessoa();
        pessoa.setNome("José Santos");
        pessoa.setEmail("josejunior@gmail.com");
        pessoa.setNascimento(LocalDate.of(1990,11,20));
        pessoa.setUid("8859dced-cd71-4dcd-b96d-86139a5728ty");
        pessoaService.create(pessoa);

        PlayList playList = new PlayList();
        playList.setNome("As Mais Tocadas de 2022");
        playList.setDescricao("Top 100 Sertanejas");
        playList.setUid("77b04f89-86a9-4ea4-9c82-60d93052b8f0");
        playList.add(musica);
        playList.add(musica2);
        playListService.create(playList);


        LOGGER.error("QUARKUS INICIOU!!!");
    }

    public void onStop(@Observes ShutdownEvent ev) {
        LOGGER.fatal("FINALIZOU!!!");
    }


}
