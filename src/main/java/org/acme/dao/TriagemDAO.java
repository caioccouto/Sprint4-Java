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

    public boolean beneficiarioExiste(Long idBenef) throws SQLException, ClassNotFoundException{
        String sql = "SELECT ID FROM BENEFICIARIO WHERE ID = ?";
        try(Connection conn = ConexaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ){
            ps.setLong(1, idBenef);
            try(ResultSet rs = ps.executeQuery()){
                return rs.next();
            }
        }
    }

    public boolean voluntarioExiste(Long idVolun) throws SQLException, ClassNotFoundException{
        String sql = "SELECT ID FROM VOLUNTARIO WHERE ID = ?";
        try(Connection conn = ConexaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ){
            ps.setLong(1, idVolun);
            try(ResultSet rs = ps.executeQuery()){
                return rs.next();
            }
        }
    }

    public void inserirTriagem(Triagem t) throws SQLException, ClassNotFoundException{
        String sql = "INSERT INTO TRIAGEM (ID_BENEFICIARIO, ID_VOLUNTARIO, DATA_INICIO, DATA_FIM, RESULTADO) VALUES (?,?,?,?,?)";

        try(Connection conn = ConexaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, new String[]{"ID"})
        ){
            ps.setLong(1, t.getIdBenef());
            ps.setLong(2, t.getIdVolun());
            ps.setDate(3, java.sql.Date.valueOf(t.getDtInicio()));
            if (t.getDtFim() != null) {
                ps.setDate(4, java.sql.Date.valueOf(t.getDtFim()));
            } else {
                ps.setNull(4, java.sql.Types.DATE);
            }
            ps.setString(5, t.getResultado().name());
            ps.executeUpdate();

            try(ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()){
                    t.setId(rs.getLong(1));
                }
            }
        }
    }

    public void deletarTriagem(Long id) throws SQLException, ClassNotFoundException{
        String sql = "DELETE FROM TRIAGEM WHERE ID = ?";

        try(Connection conn = ConexaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ){
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    public void atualizarTriagem(Triagem t) throws SQLException, ClassNotFoundException{
        String sql = "UPDATE TRIAGEM SET ID_BENEFICIARIO=?, ID_VOLUNTARIO=?, DATA_INICIO=?, DATA_FIM=?, RESULTADO=? WHERE ID=?";

        try(Connection conn = ConexaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ){
            ps.setLong(1, t.getIdBenef());
            ps.setLong(2, t.getIdVolun());
            ps.setDate(3, java.sql.Date.valueOf(t.getDtInicio()));
            if (t.getDtFim() != null) {
                ps.setDate(4, java.sql.Date.valueOf(t.getDtFim()));
            } else {
                ps.setNull(4, java.sql.Types.DATE);
            }
            ps.setString(5, t.getResultado().name());
            ps.setLong(6, t.getId());
            ps.executeUpdate();
        }
    }

    public List<Triagem> selecionar() throws SQLException, ClassNotFoundException{
        List<Triagem> lista = new ArrayList<>();
        String sql = "SELECT * FROM TRIAGEM ORDER BY ID";
        try(Connection conn = ConexaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ){
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    Triagem t = new Triagem();
                    t.setId(rs.getLong("ID"));
                    t.setIdBenef(rs.getLong("ID_BENEFICIARIO"));
                    t.setIdVolun(rs.getLong("ID_VOLUNTARIO"));
                    t.setDtInicio(rs.getDate("DATA_INICIO").toLocalDate());
                    java.sql.Date dataFim = rs.getDate("DATA_FIM");
                    t.setDtFim(dataFim != null ? dataFim.toLocalDate() : null);
                    t.setResultado(ResultadoTriagem.valueOf(rs.getString("RESULTADO")));
                    lista.add(t);
                }
                return lista;
            }
        }
    }

    public Triagem buscarTriagemPorId(Long id) throws SQLException, ClassNotFoundException{
        Triagem t = null;
        String sql = "SELECT * FROM TRIAGEM WHERE ID = ?";
        try(Connection conn = ConexaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ){
            ps.setLong(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    t = new Triagem();
                    t.setId(rs.getLong("ID"));
                    t.setIdBenef(rs.getLong("ID_BENEFICIARIO"));
                    t.setIdVolun(rs.getLong("ID_VOLUNTARIO"));
                    t.setDtInicio(rs.getDate("DATA_INICIO").toLocalDate());
                    java.sql.Date dataFim = rs.getDate("DATA_FIM");
                    t.setDtFim(dataFim != null ? dataFim.toLocalDate() : null);
                    t.setResultado(ResultadoTriagem.valueOf(rs.getString("RESULTADO")));
                }
                return t;
            }
        }
    }
}