package org.acme.bo;

import org.acme.dao.VoluntarioDAO;
import org.acme.domain.Voluntario;
import org.acme.domain.Endereco;
import org.acme.service.ViaCepService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class VoluntarioBO {

    VoluntarioDAO voluntarioDao;

    public ArrayList<Voluntario> selecionarBo() throws ClassNotFoundException, SQLException {
        voluntarioDao = new VoluntarioDAO();

        return (ArrayList<Voluntario>) voluntarioDao.selecionar();
    }

    public Voluntario buscarVolPorIdBo(Long id) throws SQLException, ClassNotFoundException {
        VoluntarioDAO volistaDAO = new VoluntarioDAO();

        return volistaDAO.buscarVolPorId(id);
    }

    public void inserirVoluntarioBo(Voluntario v) throws SQLException, ClassNotFoundException, IOException {
        ValidacaoBO.validarEmail(v.getEmail());
        ValidacaoBO.validarCpf(v.getCpf());
        ValidacaoBO.validarTelefone(v.getTelefone());
        ValidacaoBO.validarNome(v.getNome());
        ValidacaoBO.validarCep(v.getEndereco().getCep());
        ValidacaoBO.validarDataNasc(v.getDtNasc());

        ViaCepService viaCepService = new ViaCepService();
        Endereco enderecoCompleto = viaCepService.getEndereco(v.getEndereco().getCep());

        enderecoCompleto.setNumero(v.getEndereco().getNumero());
        enderecoCompleto.setComplemento(v.getEndereco().getComplemento());

        v.setEndereco(enderecoCompleto);

        VoluntarioDAO volDao = new VoluntarioDAO();

        volDao.inserirVol(v);
    }

    public void atualizarVoluntarioBo(Voluntario v) throws SQLException, ClassNotFoundException, IOException {
        ValidacaoBO.validarEmail(v.getEmail());
        ValidacaoBO.validarTelefone(v.getTelefone());
        ValidacaoBO.validarNome(v.getNome());
        ValidacaoBO.validarCep(v.getEndereco().getCep());
        ValidacaoBO.validarDataNasc(v.getDtNasc());

        ViaCepService viaCepService = new ViaCepService();
        Endereco enderecoCompleto = viaCepService.getEndereco(v.getEndereco().getCep());

        enderecoCompleto.setNumero(v.getEndereco().getNumero());
        enderecoCompleto.setComplemento(v.getEndereco().getComplemento());

        v.setEndereco(enderecoCompleto);

        VoluntarioDAO volDao = new VoluntarioDAO();
        volDao.atualizarVol(v);
    }

    public void deletarVoluntarioBo(Long id) throws SQLException, ClassNotFoundException{
        VoluntarioDAO volDao = new VoluntarioDAO();

        volDao.deletarVol(id);
    }
}
