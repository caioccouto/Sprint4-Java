package org.acme.bo;

import org.acme.dao.TriagemDAO;
import org.acme.domain.Triagem;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TriagemBO {

    public ArrayList<Triagem> selecionarBo() throws ClassNotFoundException, SQLException {
        TriagemDAO dao = new TriagemDAO();
        return (ArrayList<Triagem>) dao.selecionar();
    }

    public Triagem buscarTriagemPorIdBo(Long id) throws SQLException, ClassNotFoundException {
        TriagemDAO dao = new TriagemDAO();
        return dao.buscarTriagemPorId(id);
    }

    public void inserirTriagemBo(Triagem t) throws SQLException, ClassNotFoundException {
        validar(t);

        TriagemDAO dao = new TriagemDAO();

        if (!dao.beneficiarioExiste(t.getIdBenef())) {
            throw new IllegalArgumentException("Beneficiário com ID " + t.getIdBenef() + " não encontrado.");
        }
        if (!dao.voluntarioExiste(t.getIdVolun())) {
            throw new IllegalArgumentException("Voluntário com ID " + t.getIdVolun() + " não encontrado.");
        }

        dao.inserirTriagem(t);
    }

    public void atualizarTriagemBo(Triagem t) throws SQLException, ClassNotFoundException {
        validar(t);
        TriagemDAO dao = new TriagemDAO();
        dao.atualizarTriagem(t);
    }

    public void deletarTriagemBo(Long id) throws SQLException, ClassNotFoundException {
        TriagemDAO dao = new TriagemDAO();
        dao.deletarTriagem(id);
    }

    private void validar(Triagem t) {
        if (t.getIdBenef() == null) {
            throw new IllegalArgumentException("ID do beneficiário não pode ser nulo.");
        }
        if (t.getIdVolun() == null) {
            throw new IllegalArgumentException("ID do voluntário não pode ser nulo.");
        }
        if (t.getDtInicio() == null) {
            throw new IllegalArgumentException("Data de início da triagem não pode ser nula.");
        }
        if (t.getDtInicio().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de início da triagem não pode ser uma data futura.");
        }
        if (t.getResultado() == null) {
            throw new IllegalArgumentException("Resultado da triagem não pode ser nulo.");
        }
    }
}