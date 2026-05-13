package org.acme.resource;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.domain.Dentista;
import org.acme.service.DentistaService;

@Path("/dentista")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DentistaResource {

    DentistaService dentService = new DentistaService();

    @POST
    public Dentista cadastrarDent(Dentista d){
        return dentService.criar(d.getNome(), d.getIdade(), d.getCpf(), d.getEmail(), d.getTelefone(), d.getEndereco(), d.getCro());
    }
}
