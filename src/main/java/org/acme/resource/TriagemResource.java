package org.acme.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.acme.bo.TriagemBO;
import org.acme.domain.Triagem;

import java.sql.SQLException;
import java.util.ArrayList;

@Path("/triagem")
public class TriagemResource {

    private TriagemBO triagemBo = new TriagemBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Triagem> selecionarRs() throws ClassNotFoundException, SQLException {
        return triagemBo.selecionarBo();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Triagem buscarTriagemPorIdRs(@PathParam("id") Long id) throws ClassNotFoundException, SQLException {
        return triagemBo.buscarTriagemPorIdBo(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirRs(Triagem triagem, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException {
        try {
            triagemBo.inserirTriagemBo(triagem);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Long.toString(triagem.getId()));
        return Response.created(builder.build()).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarTriagemRs(Triagem triagem) throws ClassNotFoundException, SQLException {
        try {
            triagemBo.atualizarTriagemBo(triagem);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletarTriagemRs(@PathParam("id") Long id) throws ClassNotFoundException, SQLException {
        triagemBo.deletarTriagemBo(id);
        return Response.ok().build();
    }
}