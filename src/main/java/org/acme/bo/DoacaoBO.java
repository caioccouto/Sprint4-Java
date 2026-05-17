package org.acme.bo;

import org.acme.dao.DoacaoDAO;
import org.acme.dao.DoadorDAO;
import org.acme.domain.Doacao;

import java.sql.SQLException;
import java.util.ArrayList;

public class DoacaoBO {

    public ArrayList<Doacao> selecionarBo() throws ClassNotFoundException, SQLException {
        DoacaoDAO dao = new DoacaoDAO();

        return (ArrayList<Doacao>) dao.selecionar();
    }

    public Doacao buscarDoacaoPorIdBo(Long id) throws SQLException, ClassNotFoundException {
        DoacaoDAO dao = new DoacaoDAO();

        return dao.buscarDoacaoPorId(id);
    }

    public void inserirDoacaoBo(Doacao d) throws SQLException, ClassNotFoundException {
        DoacaoDAO dao = new DoacaoDAO();

        if (!dao.doadorExiste(d.getDoadorId())) {
            throw new IllegalArgumentException("Doador com ID " + d.getDoadorId() + " não encontrado.");
        }

        dao.inserirDoacao(d);
    }

    public void atualizarDoacaoBo(Doacao d) throws SQLException, ClassNotFoundException {
        DoacaoDAO dao = new DoacaoDAO();

        dao.atualizarDoacao(d);
    }

    public void deletarDoacaoBo(Long id) throws SQLException, ClassNotFoundException {
        DoacaoDAO dao = new DoacaoDAO();

        dao.deletarDoacao(id);
    }
}