package br.com.bb.genero;

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
@Path("/genero")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Gênero", description = "Gerenciamento de Gêneros Musicais.")
public class GeneroController {
    @Inject
    GeneroService generoService;

    @GET
    @Path("/list")
    @Operation(summary = "Lista todas os gêneros, suporta filtro por nome.")
    public Response findAll(@QueryParam("nome") String nome) {
        if (nome == null || nome.isEmpty()) {
            return Response.ok(
                    generoService.findAll()
                            .stream()
                            .map(GeneroMapper::generoToDTO)
                            .collect(Collectors.toList())
            ).build();
        }
            return Response.ok(
                    generoService.findByName(nome)
                            .stream()
                            .map(GeneroMapper::generoToDTO)
                            .collect(Collectors.toList())
            ).build();
    }

    @GET
    @Path("/{uid}")
    @Operation(summary = "Filtra um Gênero específico.")
    public Response findOne(@PathParam("uid") String uid) {
        if (uid == null || uid.isEmpty()) {
            return Response.serverError().build();
        }
            return Response.ok(GeneroMapper.generoToDTO(
                    generoService.findByUid(uid)
            )).build();
    }

    @POST
    @Operation(summary = "Cria um novo gênero.")
    public Response create(@Valid GeneroDTO generoDTO) {
        generoService.create(GeneroMapper.dtoToGenero(generoDTO));
        return Response.noContent().build();
    }

    @PUT
    @Operation(summary = "Realiza a alteração de um gênero.")
    public Response update(@Valid GeneroDTO generoDTO) {
        generoService.update(GeneroMapper.dtoToGenero(generoDTO));
        return Response.noContent().build();

    }

    @DELETE
    @Path("/{uid}")
    @Operation(summary = "Remove um genero com base no UID.")
    public Response remove(@PathParam("uid") String uid) {
        generoService.remove(uid);
        return Response.accepted().build();

    }

}
