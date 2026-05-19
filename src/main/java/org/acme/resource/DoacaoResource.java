package org.acme.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.acme.bo.DoacaoBO;
import org.acme.domain.Doacao;

import java.sql.SQLException;
import java.util.ArrayList;

@Path("/doacao")
public class DoacaoResource {

    private DoacaoBO doacaoBo = new DoacaoBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Doacao> selecionarRs() throws ClassNotFoundException, SQLException {
        return doacaoBo.selecionarBo();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Doacao buscarDoacaoPorIdRs(@PathParam("id") Long id) throws ClassNotFoundException, SQLException {
        return doacaoBo.buscarDoacaoPorIdBo(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirRs(Doacao doacao, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException {
        try {
            doacaoBo.inserirDoacaoBo(doacao);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Long.toString(doacao.getId()));
        return Response.created(builder.build()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarDoacaoRs(@PathParam("id") Long id, Doacao doacao) throws ClassNotFoundException, SQLException {
        try {
            doacao.setId(id);
            doacaoBo.atualizarDoacaoBo(doacao);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletarDoacaoRs(@PathParam("id") Long id) throws ClassNotFoundException, SQLException {
        doacaoBo.deletarDoacaoBo(id);
        return Response.ok().build();
    }
}