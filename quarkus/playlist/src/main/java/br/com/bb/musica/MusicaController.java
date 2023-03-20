package br.com.bb.musica;

import br.com.bb.pessoa.PessoaMapper;
import br.com.bb.pessoa.PessoaResumoDTO;
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
@Path("/musica")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Musica", description = "Gerenciamento de Musicas.")
public class MusicaController {
    @Inject
    MusicaService musicaService;

    @GET
    @Path("/list")
    @Operation(summary = "Lista todas os musicas, suporta filtro por nome.")
    public Response findAll(@QueryParam("nome") String nome) {
        if (nome == null || nome.isEmpty()) {
            return Response.ok(
                    musicaService.findAll()
                            .stream()
                            .map(MusicaMapper::musicaToDTO)
                            .collect(Collectors.toList())
            ).build();
        }
        return Response.ok(
                musicaService.findByName(nome)
                        .stream()
                        .map(MusicaMapper::musicaToDTO)
                        .collect(Collectors.toList())
        ).build();
    }

    @GET
    @Path("/{uid}")
    @Operation(summary = "Filtra uma musica específica.")
    public Response findOne(@PathParam("uid") String uid) {
        if (uid == null || uid.isEmpty()) {
            return Response.serverError().build();
        }
        return Response.ok(MusicaMapper.musicaToDTO(
                musicaService.findByUid(uid)
        )).build();
    }

    @POST
    @Operation(summary = "Cadastra uma nova música.")
    public Response create(@Valid MusicaDTO musicaDTO) {
        musicaService.create(MusicaMapper.dtoToMusica(musicaDTO));
        return Response.noContent().build();
    }

    @POST
    @Operation(summary = "Adiciona Like de Usuário a uma música.")
    @Path("/{uid}/like/")
    public Response add(  @PathParam("uid") String uid, PessoaResumoDTO pessoaResumoDTO) {
        if (uid == null || uid.isEmpty()) {
            return Response.serverError().build();
        }
        var pessoa = PessoaMapper.dtoToPessoaUID(pessoaResumoDTO);
        musicaService.addLike(uid, pessoa);
        return Response.noContent().build();
    }

    @PUT
    @Operation(summary = "Realiza a alteração de uma musica.")
    public Response update(@Valid MusicaDTO musicaDTO) {
            musicaService.update(MusicaMapper.dtoToMusica(musicaDTO));
            return Response.noContent().build();
    }

    @DELETE
    @Path("/{uid}")
    @Operation(summary = "Remove uma musica com base no UID.")
    public Response remove(@PathParam("uid") String uid) {
        if (uid == null || uid.isEmpty()) {
            return Response.serverError().build();
        }
        musicaService.delete(uid);
        return Response.accepted().build();

    }

}
