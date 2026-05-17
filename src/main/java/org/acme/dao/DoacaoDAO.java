package org.acme.dao;

import org.acme.conexao.ConexaoFactory;
import org.acme.domain.Doacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoacaoDAO {

    public Connection conn;

    public DoacaoDAO() throws SQLException, ClassNotFoundException {
        super();
        this.conn = new ConexaoFactory().getConnection();
    }

    public boolean doadorExiste(Long idDoador) throws SQLException {
        String sql = "SELECT ID FROM DOADOR WHERE ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, idDoador);
        ResultSet rs = ps.executeQuery();
        boolean existe = rs.next();
        rs.close();
        ps.close();
        return existe;
    }

    public void inserirDoacao(Doacao d) throws SQLException {
        String sql = "INSERT INTO DOACAO (VALOR, DESCRICAO, DOADOR_ID) VALUES (?,?,?)";

        PreparedStatement ps = conn.prepareStatement(sql, new String[]{"ID"});

        ps.setDouble(1, d.getValor());
        ps.setString(2, d.getDescricao());
        ps.setLong(3, d.getDoadorId());
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()){
            d.setId(rs.getLong(1));
        }

        rs.close();
        ps.close();
    }

    public void deletarDoacao(Long id) throws SQLException {
        String sql = "DELETE FROM DOACAO WHERE ID = ?";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setLong(1, id);
        ps.executeUpdate();
        ps.close();
    }

    public void atualizarDoacao(Doacao d) throws SQLException {
        String sql = "UPDATE DOACAO SET VALOR=?, DESCRICAO=? WHERE ID=?";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setDouble(1, d.getValor());
        ps.setString(2, d.getDescricao());
        ps.setLong(3, d.getId());
        ps.executeUpdate();
        ps.close();
    }

    public List<Doacao> selecionar() throws SQLException {
        List<Doacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM DOACAO";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Doacao d = new Doacao();
            d.setId(rs.getLong(1));
            d.setValor(rs.getDouble(2));
            d.setDescricao(rs.getString(3));
            d.setDoadorId(rs.getLong(4));
            lista.add(d);
        }
        return lista;
    }

    public Doacao buscarDoacaoPorId(Long id) throws SQLException {
        Doacao d = null;
        String sql = "SELECT * FROM DOACAO WHERE ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            d = new Doacao();
            d.setId(rs.getLong(1));
            d.setValor(rs.getDouble(2));
            d.setDescricao(rs.getString(3));
            d.setDoadorId(rs.getLong(4));
        }

        rs.close();
        ps.close();
        return d;
    }
}