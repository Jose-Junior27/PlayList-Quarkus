package br.com.bb.playlist;

import br.com.bb.musica.MusicaDTO;
import br.com.bb.musica.MusicaMapper;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("/playlist")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "PlayList", description = "Gerenciamento das PlayLists.")
public class PlayListController {
    @Inject
    PlayListService playListService;

    @GET
    @Path("/list")
    @Operation(summary = "Lista todas as PlayLists, suporta filtro por nome.")
    public Response findAll(@QueryParam("nome") String nome) {
        if (nome == null || nome.isEmpty()) {
            return Response.ok(
                    playListService.findAll()
                            .stream()
                            .map(PlayListMapper::playListToDTO)
                            .collect(Collectors.toList())
            ).build();
        }
        return Response.ok(
                playListService.findByName(nome)
                        .stream()
                        .map(PlayListMapper::playListToDTO)
                        .collect(Collectors.toList())
        ).build();
    }

    @GET
    @Path("/{uid}")
    @Operation(summary = "Filtra uma PlayList específico por uid.")
    public Response findOne(@PathParam("uid") String uid) {
        if (uid == null || uid.isEmpty()) {
            return Response.serverError().build();
        }
        return Response.ok(PlayListMapper.playListToDTO(
                playListService.findByUid(uid)
        )).build();
    }

    @POST
    @Operation(summary = "Cadastra uma nova PlayList.")
    public Response create(@Valid PlayListDTO playListDTO) {
        playListService.create(PlayListMapper.dtoToPlayList(playListDTO));
        return Response.noContent().build();
    }

    @POST
    @Operation(summary = "Adiciona Música à PlayList.")
    @Path("/{uid}/musica/")
    public Response add(  @PathParam("uid") String uid, MusicaDTO musicaDTO) {
        if (uid == null || uid.isEmpty()) {
            return Response.serverError().build();
        }
        var music = MusicaMapper.dtoToMusicaUID(musicaDTO);
        playListService.addMusic(uid, music);
        return Response.noContent().build();
    }

    @PUT
    @Operation(summary = "Realiza alterações na playList.")
    public Response update(@Valid PlayListDTO playListDTO) {
        playListService.update(PlayListMapper.dtoToPlayList(playListDTO));
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{uid}")
    @Operation(summary = "Remove uma PlayList com base no UID.")
    public Response remove(@PathParam("uid") String uid) {
        if (uid == null || uid.isEmpty()) {
            return Response.serverError().build();
        }
        playListService.delete(uid);
        return Response.accepted().build();

    }

}
