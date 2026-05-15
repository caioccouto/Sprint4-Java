package org.acme.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.acme.bo.DentistaBO;
import org.acme.domain.Dentista;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/dentista")
public class DentistaResource {

    private DentistaBO dentBo = new DentistaBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Dentista> selecionarRs() throws ClassNotFoundException, SQLException {
        return dentBo.selecionarBo();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Dentista buscarDentPorIdRs(@PathParam("id") Long id) throws ClassNotFoundException, SQLException {
        return dentBo.buscarDentPorIdBo(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirRs(Dentista dentista, @Context UriInfo uriInfo ) throws ClassNotFoundException, SQLException, IOException {
        dentBo.inserirDentistaBo(dentista);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Long.toString(dentista.getId()));
        return Response.created(builder.build()).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarDentRs(Dentista dentista, @PathParam("id") Long id) throws ClassNotFoundException, SQLException {
        dentBo.atualizarDentistaBo(dentista);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletarDentRs(@PathParam("id") Long id) throws ClassNotFoundException, SQLException {
        dentBo.deletarDentistaBo(id);
        return Response.ok().build();
    }
}
