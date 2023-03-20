package br.com.bb.pessoa;

import br.com.bb.musica.MusicaDTO;
import br.com.bb.musica.MusicaMapper;
import br.com.bb.playlist.PlayListDTO;
import br.com.bb.playlist.PlayListMapper;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("/pessoa")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Pessoa", description = "Gerenciamento do Cadastro de Usuários.")
public class PessoaController {

    @Inject
    PessoaService pessoaService;

    @GET
    @Path("/list")
    @Operation(summary = "Lista todos os usuários, suporta filtro por nome.")
    public Response findAll(@QueryParam("nome") String nome) {
        if (nome == null || nome.isEmpty()) {
            return Response.ok(
                    pessoaService.findAll()
                            .stream()
                            .map(PessoaMapper::pessoaToDTO)
                            .collect(Collectors.toList())
            ).build();
        }
        return Response.ok(
                pessoaService.findByName(nome)
                        .stream()
                        .map(PessoaMapper::pessoaToDTO)
                        .collect(Collectors.toList())
        ).build();
    }

    @GET
    @Path("/{uid}")
    @Operation(summary = "Filtra um usuário específico por uid.")
    public Response findOne(@PathParam("uid") String uid) {
        if (uid == null || uid.isEmpty()) {
            return Response.serverError().build();
        }
        return Response.ok(PessoaMapper.pessoaToDTO(
                pessoaService.findByUid(uid)
        )).build();
    }

    @GET
    @Path("/mail/{email}")
    @Operation(summary = "Filtra um usuário específico por email.")
    public Response findMail(@PathParam("email") String email) {
        if (email == null || email.isEmpty()) {
            return Response.serverError().build();
        }
        return Response.ok(PessoaMapper.pessoaToDTO(
                pessoaService.findByMail(email)
        )).build();
    }

    @POST
    @Operation(summary = "Cadastra um novo usuário.")
    public Response create(@Valid PessoaDTO pessoaDTO) {
        pessoaService.create(PessoaMapper.dtoToPessoa(pessoaDTO));
        return Response.noContent().build();
    }

    @POST
    @Operation(summary = "Adiciona uma PlayList para o Usuário.")
    @Path("/{uid}/playlist/")
    public Response add(  @PathParam("uid") String uid, PlayListDTO playListDTO) {
        if (uid == null || uid.isEmpty()) {
            return Response.serverError().build();
        }
        var play = PlayListMapper.dtoToPlayListUID(playListDTO);
        pessoaService.addPlayList(uid, play);
        return Response.noContent().build();
    }

    @PUT
    @Operation(summary = "Realiza a alteração de um usuário.")
    public Response update(@Valid PessoaDTO pessoaDTO) {
        pessoaService.update(PessoaMapper.dtoToPessoa(pessoaDTO));
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{uid}")
    @Operation(summary = "Remove um usuário com base no UID.")
    public Response remove(@PathParam("uid") String uid) {
        if (uid == null || uid.isEmpty()) {
            return Response.serverError().build();
        }
        pessoaService.delete(uid);
        return Response.accepted().build();

    }

}
