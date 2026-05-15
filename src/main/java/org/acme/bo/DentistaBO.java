package org.acme.bo;

import org.acme.dao.DentistaDAO;
import org.acme.domain.Dentista;
import org.acme.domain.Endereco;
import org.acme.service.ViaCepService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class DentistaBO {

    DentistaDAO dentistaDao;

    public ArrayList<Dentista> selecionarBo() throws ClassNotFoundException, SQLException {
        dentistaDao = new DentistaDAO();

        return (ArrayList<Dentista>) dentistaDao.selecionar();
    }

    public Dentista buscarDentPorIdBo(Long id) throws SQLException, ClassNotFoundException {
        DentistaDAO dentistaDAO = new DentistaDAO();

        return dentistaDAO.buscarDentPorId(id);
    }

    public void inserirDentistaBo(Dentista d) throws SQLException, ClassNotFoundException, IOException {
        String cep = d.getEndereco().getCep();

        ViaCepService viaCepService = new ViaCepService();
        Endereco enderecoCompleto = viaCepService.getEndereco(cep);

        enderecoCompleto.setNumero(d.getEndereco().getNumero());
        enderecoCompleto.setComplemento(d.getEndereco().getComplemento());

        d.setEndereco(enderecoCompleto);

        DentistaDAO dentDao = new DentistaDAO();

        dentDao.inserirDent(d);
    }

    public void atualizarDentistaBo(Dentista d) throws SQLException, ClassNotFoundException{
        DentistaDAO dentDao = new DentistaDAO();

        dentDao.atualizarDent(d);
    }

    public void deletarDentistaBo(Long id) throws SQLException, ClassNotFoundException{
        DentistaDAO dentDao = new DentistaDAO();

        dentDao.deletarDent(id);
    }
}
