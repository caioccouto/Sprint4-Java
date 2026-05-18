package org.acme.bo;

import org.acme.dao.BeneficiarioDAO;
import org.acme.domain.Beneficiario;
import org.acme.domain.Endereco;
import org.acme.service.ViaCepService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class BeneficiarioBO {

    public ArrayList<Beneficiario> selecionarBo() throws ClassNotFoundException, SQLException {
        BeneficiarioDAO dao = new BeneficiarioDAO();

        return (ArrayList<Beneficiario>) dao.selecionar();
    }

    public Beneficiario buscarBenefPorIdBo(Long id) throws SQLException, ClassNotFoundException {
        BeneficiarioDAO dao = new BeneficiarioDAO();

        return dao.buscarBenefPorId(id);
    }

    public void inserirBenefBo(Beneficiario b) throws SQLException, ClassNotFoundException, IOException {
        ValidacaoBO.validarEmail(b.getEmail());
        ValidacaoBO.validarCpf(b.getCpf());
        ValidacaoBO.validarTelefone(b.getTelefone());
        ValidacaoBO.validarNome(b.getNome());
        ValidacaoBO.validarCep(b.getEndereco().getCep());
        ValidacaoBO.validarDataNasc(b.getDtNasc());

        ViaCepService viaCepService = new ViaCepService();
        Endereco enderecoCompleto = viaCepService.getEndereco(b.getEndereco().getCep());

        enderecoCompleto.setNumero(b.getEndereco().getNumero());
        enderecoCompleto.setComplemento(b.getEndereco().getComplemento());

        b.setEndereco(enderecoCompleto);

        BeneficiarioDAO dao = new BeneficiarioDAO();
        dao.inserirBenef(b);
    }

    public void atualizarBenefBo(Beneficiario b) throws SQLException, ClassNotFoundException {
        ValidacaoBO.validarEmail(b.getEmail());
        ValidacaoBO.validarTelefone(b.getTelefone());
        ValidacaoBO.validarNome(b.getNome());
        ValidacaoBO.validarCep(b.getEndereco().getCep());
        ValidacaoBO.validarDataNasc(b.getDtNasc());

        BeneficiarioDAO dao = new BeneficiarioDAO();
        dao.atualizarBenef(b);
    }

    public void deletarBenefBo(Long id) throws SQLException, ClassNotFoundException {
        BeneficiarioDAO dao = new BeneficiarioDAO();

        dao.deletarBenef(id);
    }
}
