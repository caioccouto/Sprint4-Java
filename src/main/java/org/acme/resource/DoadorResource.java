package org.acme.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.acme.bo.DoadorBO;
import org.acme.domain.Doador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/doador")
public class DoadorResource {

    private DoadorBO doadorBo = new DoadorBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Doador> selecionarRs() throws ClassNotFoundException, SQLException {
        return doadorBo.selecionarBo();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Doador buscarDoadorPorIdRs(@PathParam("id") Long id) throws ClassNotFoundException, SQLException {
        return doadorBo.buscarDoadorPorIdBo(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirRs(Doador doador, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException, IOException {
        try {
            doadorBo.inserirDoadorBo(doador);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Long.toString(doador.getId()));
        return Response.created(builder.build()).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarDoadorRs(Doador doador) throws ClassNotFoundException, SQLException {
        try {
            doadorBo.atualizarDoadorBo(doador);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletarDoadorRs(@PathParam("id") Long id) throws ClassNotFoundException, SQLException {
        doadorBo.deletarDoadorBo(id);
        return Response.ok().build();
    }
}