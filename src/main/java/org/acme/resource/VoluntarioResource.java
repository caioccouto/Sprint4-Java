package org.acme.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.acme.bo.VoluntarioBO;
import org.acme.domain.Voluntario;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/voluntarios")
public class VoluntarioResource {

    private VoluntarioBO volBo = new VoluntarioBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Voluntario> selecionarRs() throws ClassNotFoundException, SQLException {
        return volBo.selecionarBo();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Voluntario buscarVolPorIdRs(@PathParam("id") Long id) throws ClassNotFoundException, SQLException {
        return volBo.buscarVolPorIdBo(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirRs(Voluntario voluntario, @Context UriInfo uriInfo ) throws ClassNotFoundException, SQLException, IOException{
        try {
            volBo.inserirVoluntarioBo(voluntario);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Long.toString(voluntario.getId()));
        return Response.created(builder.build()).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarVolRs(Voluntario voluntario) throws ClassNotFoundException, SQLException {
        try {
            volBo.atualizarVoluntarioBo(voluntario);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletarVolRs(@PathParam("id") Long id) throws ClassNotFoundException, SQLException {
        volBo.deletarVoluntarioBo(id);
        return Response.ok().build();
    }
}
