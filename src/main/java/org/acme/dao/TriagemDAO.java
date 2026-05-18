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
        String sql = "INSERT INTO TRIAGEM (ID_BENEF, ID_VOLUN, DT_INICIO, DT_FIM, RESULTADO_TRIAGEM) VALUES (?,?,?,?,?)";

        try(Connection conn = ConexaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, new String[]{"ID"})
        ){
            ps.setLong(1, t.getIdBenef());
            ps.setLong(2, t.getIdVolun());
            ps.setDate(3, java.sql.Date.valueOf(t.getDtInicio()));
            ps.setDate(3, java.sql.Date.valueOf(t.getDtFim()));
            ps.setString(4, t.getResultadoTriagem().name());
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
        String sql = "UPDATE TRIAGEM SET ID_BENEF=?, ID_VOLUN=?, DT_INICIO=?, DT_FIM=?, RESULTADO_TRIAGEM=? WHERE ID=?";

        try(Connection conn = ConexaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ){
            ps.setLong(1, t.getIdBenef());
            ps.setLong(2, t.getIdVolun());
            ps.setDate(3, java.sql.Date.valueOf(t.getDtInicio()));
            ps.setDate(3, java.sql.Date.valueOf(t.getDtFim()));
            ps.setString(4, t.getResultadoTriagem().name());
            ps.setLong(5, t.getId());
            ps.executeUpdate();
        }
    }

    public List<Triagem> selecionar() throws SQLException, ClassNotFoundException{
        List<Triagem> lista = new ArrayList<>();
        String sql = "SELECT * FROM TRIAGEM";
        try(Connection conn = ConexaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ){
            try(ResultSet rs = ps.executeQuery()){
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
                    t.setId(rs.getLong(1));
                    t.setIdBenef(rs.getLong(2));
                    t.setIdVolun(rs.getLong(3));
                    t.setDtInicio(rs.getDate(4).toLocalDate());
                    t.setDtFim(rs.getDate(4).toLocalDate());
                    t.setResultadoTriagem(ResultadoTriagem.valueOf(rs.getString(5)));
                }
                return t;
            }
        }
    }
}