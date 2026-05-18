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

    public boolean doadorExiste(Long idDoador) throws SQLException, ClassNotFoundException{
        String sql = "SELECT ID FROM DOADOR WHERE ID = ?";

        try(Connection conn = ConexaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ){
            ps.setLong(1, idDoador);
            try(ResultSet rs = ps.executeQuery()){
                return rs.next();
            }
        }
    }

    public void inserirDoacao(Doacao d) throws SQLException, ClassNotFoundException{
        String sql = "INSERT INTO DOACAO (VALOR, DESCRICAO, DOADOR_ID) VALUES (?,?,?)";

        try(Connection conn = ConexaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, new String[]{"ID"})
        ){
            ps.setDouble(1, d.getValor());
            ps.setString(2, d.getDescricao());
            ps.setLong(3, d.getDoadorId());
            ps.executeUpdate();

            try(ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()){
                    d.setId(rs.getLong(1));
                }
            }
        }
    }

    public void deletarDoacao(Long id) throws SQLException, ClassNotFoundException{
        String sql = "DELETE FROM DOACAO WHERE ID = ?";

        try(Connection conn = ConexaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ){
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    public void atualizarDoacao(Doacao d) throws SQLException, ClassNotFoundException{
        String sql = "UPDATE DOACAO SET VALOR=?, DESCRICAO=? WHERE ID=?";

        try(Connection conn = ConexaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ){
            ps.setDouble(1, d.getValor());
            ps.setString(2, d.getDescricao());
            ps.setLong(3, d.getId());
            ps.executeUpdate();
        }
    }

    public List<Doacao> selecionar() throws SQLException, ClassNotFoundException{
        List<Doacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM DOACAO ORDER BY ID";

        try(Connection conn = ConexaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ){
            try(ResultSet rs = ps.executeQuery()){
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
        }
    }

    public Doacao buscarDoacaoPorId(Long id) throws SQLException, ClassNotFoundException{
        Doacao d = null;
        String sql = "SELECT * FROM DOACAO WHERE ID = ?";

        try(Connection conn = ConexaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ){
            ps.setLong(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    d = new Doacao();
                    d.setId(rs.getLong(1));
                    d.setValor(rs.getDouble(2));
                    d.setDescricao(rs.getString(3));
                    d.setDoadorId(rs.getLong(4));
                }
                return d;
            }
        }
    }
}