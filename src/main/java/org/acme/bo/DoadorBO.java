package org.acme.bo;

import org.acme.dao.DoadorDAO;
import org.acme.domain.Doador;
import org.acme.domain.Endereco;
import org.acme.service.ViaCepService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class DoadorBO {

    public ArrayList<Doador> selecionarBo() throws ClassNotFoundException, SQLException {
        DoadorDAO dao = new DoadorDAO();

        return (ArrayList<Doador>) dao.selecionar();
    }

    public Doador buscarDoadorPorIdBo(Long id) throws SQLException, ClassNotFoundException {
        DoadorDAO dao = new DoadorDAO();

        return dao.buscarDoadorPorId(id);
    }

    public void inserirDoadorBo(Doador d) throws SQLException, ClassNotFoundException, IOException {
        ValidacaoBO.validarEmail(d.getEmail());
        ValidacaoBO.validarCpfOuCnpj(d.getDocumento());
        ValidacaoBO.validarTelefone(d.getTelefone());
        ValidacaoBO.validarNome(d.getNome());
        ValidacaoBO.validarCep(d.getEndereco().getCep());
        ValidacaoBO.validarDataNasc(d.getDtNasc());

        ViaCepService viaCepService = new ViaCepService();
        Endereco enderecoCompleto = viaCepService.getEndereco(d.getEndereco().getCep());
        enderecoCompleto.setNumero(d.getEndereco().getNumero());
        enderecoCompleto.setComplemento(d.getEndereco().getComplemento());
        d.setEndereco(enderecoCompleto);

        DoadorDAO dao = new DoadorDAO();
        dao.inserirDoador(d);
    }

    public void atualizarDoadorBo(Doador d) throws SQLException, ClassNotFoundException, IOException {
        ValidacaoBO.validarEmail(d.getEmail());
        ValidacaoBO.validarTelefone(d.getTelefone());
        ValidacaoBO.validarNome(d.getNome());
        ValidacaoBO.validarCep(d.getEndereco().getCep());
        ValidacaoBO.validarDataNasc(d.getDtNasc());

        ViaCepService viaCepService = new ViaCepService();
        Endereco enderecoCompleto = viaCepService.getEndereco(d.getEndereco().getCep());

        enderecoCompleto.setNumero(d.getEndereco().getNumero());
        enderecoCompleto.setComplemento(d.getEndereco().getComplemento());

        d.setEndereco(enderecoCompleto);

        DoadorDAO dao = new DoadorDAO();
        dao.atualizarDoador(d);
    }

    public void deletarDoadorBo(Long id) throws SQLException, ClassNotFoundException {
        DoadorDAO dao = new DoadorDAO();

        dao.deletarDoador(id);
    }
}