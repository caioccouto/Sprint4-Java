package org.acme.dao;

import org.acme.conexao.ConexaoFactory;
import org.acme.domain.Beneficiario;
import org.acme.domain.Endereco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BeneficiarioDAO {

    public void inserirBenef(Beneficiario b) throws SQLException, ClassNotFoundException{
        String sql = "INSERT INTO BENEFICIARIO (NOME, CPF, DT_NASC, EMAIL, TELEFONE, CEP, LOGRADOURO, BAIRRO, UF, LOCALIDADE, NUMERO, COMPLEMENTO) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

        try(Connection conn = ConexaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, new String[]{"ID"})
        ){
            ps.setString(1, b.getNome());
            ps.setString(2, b.getCpf());
            ps.setDate(3, java.sql.Date.valueOf(b.getDtNasc()));
            ps.setString(4, b.getEmail());
            ps.setString(5, b.getTelefone());
            ps.setString(6, b.getEndereco().getCep().replaceAll("\\D", ""));
            ps.setString(7, b.getEndereco().getLogradouro());
            ps.setString(8, b.getEndereco().getBairro());
            ps.setString(9, b.getEndereco().getUf());
            ps.setString(10, b.getEndereco().getLocalidade());
            ps.setString(11, b.getEndereco().getNumero());
            ps.setString(12, b.getEndereco().getComplemento());
            ps.executeUpdate();

            try(ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()){
                    b.setId(rs.getLong(1));
                }
            }
        }
    }

    public void deletarBenef(Long id) throws SQLException, ClassNotFoundException{
        String sql = "DELETE FROM BENEFICIARIO WHERE ID = ?";

        try(Connection conn = ConexaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ){
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    public void atualizarBenef(Beneficiario b) throws SQLException, ClassNotFoundException{
        String sql = "UPDATE BENEFICIARIO SET NOME=?, DT_NASC=?, EMAIL=?, TELEFONE=?, CEP=?, LOGRADOURO=?, BAIRRO=?, UF=?, LOCALIDADE=?, NUMERO=?, COMPLEMENTO=? WHERE ID=?";

        try(Connection conn = ConexaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ){
            ps.setString(1, b.getNome());
            ps.setDate(2, java.sql.Date.valueOf(b.getDtNasc()));
            ps.setString(3, b.getEmail());
            ps.setString(4, b.getTelefone());
            ps.setString(5, b.getEndereco().getCep().replaceAll("\\D", ""));
            ps.setString(6, b.getEndereco().getLogradouro());
            ps.setString(7, b.getEndereco().getBairro());
            ps.setString(8, b.getEndereco().getUf());
            ps.setString(9, b.getEndereco().getLocalidade());
            ps.setString(10, b.getEndereco().getNumero());
            ps.setString(11, b.getEndereco().getComplemento());
            ps.setLong(12, b.getId());
            ps.executeUpdate();
        }
    }

    public List<Beneficiario> selecionar() throws SQLException, ClassNotFoundException{
        List<Beneficiario> lista = new ArrayList<>();
        String sql = "SELECT * FROM BENEFICIARIO ORDER BY ID";

        try(Connection conn = ConexaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ){
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    Beneficiario b = new Beneficiario();
                    Endereco e = new Endereco();

                    b.setId(rs.getLong(1));
                    b.setNome(rs.getString(2));
                    b.setCpf(rs.getString(3));
                    b.setDtNasc(rs.getDate(4).toLocalDate());
                    b.setEmail(rs.getString(5));
                    b.setTelefone(rs.getString(6));
                    e.setCep(rs.getString(7));
                    e.setLogradouro(rs.getString(8));
                    e.setBairro(rs.getString(9));
                    e.setUf(rs.getString(10));
                    e.setLocalidade(rs.getString(11));
                    e.setNumero(rs.getString(12));
                    e.setComplemento(rs.getString(13));
                    b.setEndereco(e);

                    lista.add(b);
                }
                return lista;
            }
        }
    }

    public Beneficiario buscarBenefPorId(Long id) throws SQLException, ClassNotFoundException{
        Beneficiario b = null;
        String sql = "SELECT * FROM BENEFICIARIO WHERE ID = ?";

        try(Connection conn = ConexaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ){
            ps.setLong(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    b = new Beneficiario();
                    Endereco e = new Endereco();

                    b.setId(rs.getLong(1));
                    b.setNome(rs.getString(2));
                    b.setCpf(rs.getString(3));
                    b.setDtNasc(rs.getDate(4).toLocalDate());
                    b.setEmail(rs.getString(5));
                    b.setTelefone(rs.getString(6));
                    e.setCep(rs.getString(7));
                    e.setLogradouro(rs.getString(8));
                    e.setBairro(rs.getString(9));
                    e.setUf(rs.getString(10));
                    e.setLocalidade(rs.getString(11));
                    e.setNumero(rs.getString(12));
                    e.setComplemento(rs.getString(13));
                    b.setEndereco(e);
                }
                return b;
            }
        }
    }
}
