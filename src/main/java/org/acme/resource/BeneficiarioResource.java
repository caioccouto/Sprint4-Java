package org.acme.resource;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.domain.Beneficiario;
import org.acme.service.BeneficiarioService;

@Path("/beneficiario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BeneficiarioResource {

    BeneficiarioService benefService = new BeneficiarioService();

    @POST
    public Beneficiario cadastrarBenef(Beneficiario b){
        return benefService.criar(b.getNome(), b.getIdade(), b.getCpf(), b.getEmail(), b.getTelefone(), b.getEndereco());
    }
}
