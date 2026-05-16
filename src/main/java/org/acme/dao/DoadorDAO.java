package org.acme.dao;

import org.acme.conexao.ConexaoFactory;
import org.acme.domain.Doador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoadorDAO {

    public Connection conn;

    public DoadorDAO() throws SQLException, ClassNotFoundException{
        super();
        this.conn = new ConexaoFactory().getConnection();
    }

    public void inserirDoador(Doador d) throws SQLException{
        String sql = "INSERT INTO DOADOR (NOME, DOCUMENTO, DT_NASC, EMAIL, TELEFONE, CEP, LOGRADOURO, BAIRRO, UF, LOCALIDADE, NUMERO, COMPLEMENTO) values (?,?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, d.getNome());
        ps.setString(2, d.getDocumento());
        ps.setDate(3, java.sql.Date.valueOf(d.getDtNasc()));
        ps.setString(4, d.getEmail());
        ps.setString(5, d.getTelefone());
        ps.setString(6, d.getEndereco().getCep());
        ps.setString(7, d.getEndereco().getLogradouro());
        ps.setString(8, d.getEndereco().getBairro());
        ps.setString(9, d.getEndereco().getUf());
        ps.setString(10, d.getEndereco().getLocalidade());
        ps.setString(11, d.getEndereco().getNumero());
        ps.setString(12, d.getEndereco().getComplemento());
        ps.executeUpdate();
        ps.close();
    }

    public void deletarDoador(Long id) throws SQLException{
        String sql = "DELETE FROM DOADOR where ID = ?";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setLong(1, id);
        ps.executeUpdate();
        ps.close();
    }

    public void atualizarDoador(Doador d) throws SQLException{
        String sql = "UPDATE DOADOR SET NOME=?, DT_NASC=?, EMAIL=?, TELEFONE=?, CEP=?, LOGRADOURO=?, BAIRRO=?, UF=?, LOCALIDADE=?, NUMERO=?, COMPLEMENTO=? WHERE ID=?";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, d.getNome());
        ps.setDate(2, java.sql.Date.valueOf(d.getDtNasc()));
        ps.setString(3, d.getEmail());
        ps.setString(4, d.getTelefone());
        ps.setString(5, d.getEndereco().getCep());
        ps.setString(6, d.getEndereco().getLogradouro());
        ps.setString(7, d.getEndereco().getBairro());
        ps.setString(8, d.getEndereco().getUf());
        ps.setString(9, d.getEndereco().getLocalidade());
        ps.setString(10, d.getEndereco().getNumero());
        ps.setString(11, d.getEndereco().getComplemento());
        ps.setLong(12, d.getId());

        ps.executeUpdate();
        ps.close();
    }

    public List<Doador> selecionar() throws SQLException {
        List<Doador> listaDoadores = new ArrayList<>();
        String sql = "SELECT * FROM DOADOR";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            Doador doador = new Doador();

            doador.setId(rs.getLong(1));
            doador.setNome(rs.getString(2));
            doador.setDocumento(rs.getString(3));
            doador.setDtNasc(rs.getDate(4).toLocalDate());
            doador.setEmail(rs.getString(5));
            doador.setTelefone(rs.getString(6));
            doador.getEndereco().setCep(rs.getString(7));
            doador.getEndereco().setLogradouro(rs.getString(8));
            doador.getEndereco().setBairro(rs.getString(9));
            doador.getEndereco().setUf(rs.getString(10));
            doador.getEndereco().setLocalidade(rs.getString(11));
            doador.getEndereco().setNumero(rs.getString(12));
            doador.getEndereco().setComplemento(rs.getString(13));

            listaDoadores.add(doador);
        }
        return listaDoadores;
    }

    public Doador buscarDoadorPorId(Long id) throws SQLException {
        Doador doador = null;

        String sql = "SELECT * FROM DOADOR WHERE ID = ?";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setLong(1, id);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            doador = new Doador();

            doador.setId(rs.getLong(1));
            doador.setNome(rs.getString(2));
            doador.setDocumento(rs.getString(3));
            doador.setDtNasc(rs.getDate(4).toLocalDate());
            doador.setEmail(rs.getString(5));
            doador.setTelefone(rs.getString(6));
            doador.getEndereco().setCep(rs.getString(7));
            doador.getEndereco().setLogradouro(rs.getString(8));
            doador.getEndereco().setBairro(rs.getString(9));
            doador.getEndereco().setUf(rs.getString(10));
            doador.getEndereco().setLocalidade(rs.getString(11));
            doador.getEndereco().setNumero(rs.getString(12));
            doador.getEndereco().setComplemento(rs.getString(13));
        }

        rs.close();
        ps.close();

        return doador;
    }
}
