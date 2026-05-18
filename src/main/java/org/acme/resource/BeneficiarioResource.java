package org.acme.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.acme.bo.BeneficiarioBO;
import org.acme.domain.Beneficiario;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/beneficiario")
public class BeneficiarioResource {

    private BeneficiarioBO benefBo = new BeneficiarioBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Beneficiario> selecionarRs() throws ClassNotFoundException, SQLException {
        return benefBo.selecionarBo();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Beneficiario buscarBenefPorIdRs(@PathParam("id") Long id) throws ClassNotFoundException, SQLException {
        return benefBo.buscarBenefPorIdBo(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserirRs(Beneficiario beneficiario, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException, IOException {
        try {
            benefBo.inserirBenefBo(beneficiario);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Long.toString(beneficiario.getId()));
        return Response.created(builder.build()).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarBenefRs(Beneficiario beneficiario) throws ClassNotFoundException, SQLException, IOException {
        try {
            benefBo.atualizarBenefBo(beneficiario);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletarBenefRs(@PathParam("id") Long id) throws ClassNotFoundException, SQLException {
        benefBo.deletarBenefBo(id);
        return Response.ok().build();
    }
}
