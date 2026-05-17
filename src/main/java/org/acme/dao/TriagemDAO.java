package org.acme.dao;

import org.acme.conexao.ConexaoFactory;
import org.acme.domain.ResultadoTriagem;
import org.acme.domain.Triagem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TriagemDAO {

    public Connection conn;

    public TriagemDAO() throws SQLException, ClassNotFoundException {
        super();
        this.conn = new ConexaoFactory().getConnection();
    }

    public boolean beneficiarioExiste(Long idBenef) throws SQLException {
        String sql = "SELECT ID FROM BENEFICIARIO WHERE ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, idBenef);
        ResultSet rs = ps.executeQuery();
        boolean existe = rs.next();
        rs.close();
        ps.close();
        return existe;
    }

    public boolean voluntarioExiste(Long idVolun) throws SQLException {
        String sql = "SELECT ID FROM VOLUNTARIO WHERE ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, idVolun);
        ResultSet rs = ps.executeQuery();
        boolean existe = rs.next();
        rs.close();
        ps.close();
        return existe;
    }

    public void inserirTriagem(Triagem t) throws SQLException {
        String sql = "INSERT INTO TRIAGEM (ID_BENEF, ID_VOLUN, DT_INICIO, DT_FIM, RESULTADO_TRIAGEM) VALUES (?,?,?,?,?)";

        PreparedStatement ps = conn.prepareStatement(sql, new String[]{"ID"});

        ps.setLong(1, t.getIdBenef());
        ps.setLong(2, t.getIdVolun());
        ps.setDate(3, java.sql.Date.valueOf(t.getDtInicio()));
        ps.setDate(3, java.sql.Date.valueOf(t.getDtFim()));
        ps.setString(4, t.getResultadoTriagem().name());
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()){
            t.setId(rs.getLong(1));
        }

        rs.close();
        ps.close();
    }

    public void deletarTriagem(Long id) throws SQLException {
        String sql = "DELETE FROM TRIAGEM WHERE ID = ?";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setLong(1, id);
        ps.executeUpdate();
        ps.close();
    }

    public void atualizarTriagem(Triagem t) throws SQLException {
        String sql = "UPDATE TRIAGEM SET ID_BENEF=?, ID_VOLUN=?, DT_INICIO=?, DT_FIM=?, RESULTADO_TRIAGEM=? WHERE ID=?";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setLong(1, t.getIdBenef());
        ps.setLong(2, t.getIdVolun());
        ps.setDate(3, java.sql.Date.valueOf(t.getDtInicio()));
        ps.setDate(3, java.sql.Date.valueOf(t.getDtFim()));
        ps.setString(4, t.getResultadoTriagem().name());
        ps.setLong(5, t.getId());
        ps.executeUpdate();
        ps.close();
    }

    public List<Triagem> selecionar() throws SQLException {
        List<Triagem> lista = new ArrayList<>();
        String sql = "SELECT * FROM TRIAGEM";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Triagem t = new Triagem();
            t.setId(rs.getLong(1));
            t.setIdBenef(rs.getLong(2));
            t.setIdVolun(rs.getLong(3));
            t.setDtInicio(rs.getDate(4).toLocalDate());
            t.setDtFim(rs.getDate(4).toLocalDate());
            t.setResultadoTriagem(ResultadoTriagem.valueOf(rs.getString(5)));
            lista.add(t);
        }

        rs.close();
        ps.close();
        return lista;
    }

    public Triagem buscarTriagemPorId(Long id) throws SQLException {
        Triagem t = null;
        String sql = "SELECT * FROM TRIAGEM WHERE ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setLong(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            t = new Triagem();
            t.setId(rs.getLong(1));
            t.setIdBenef(rs.getLong(2));
            t.setIdVolun(rs.getLong(3));
            t.setDtInicio(rs.getDate(4).toLocalDate());
            t.setDtFim(rs.getDate(4).toLocalDate());
            t.setResultadoTriagem(ResultadoTriagem.valueOf(rs.getString(5)));
        }

        rs.close();
        ps.close();
        return t;
    }
}