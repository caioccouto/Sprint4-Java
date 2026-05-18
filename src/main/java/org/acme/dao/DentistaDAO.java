package org.acme.dao;

import org.acme.conexao.ConexaoFactory;
import org.acme.domain.Dentista;
import org.acme.domain.Endereco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DentistaDAO {

    public void inserirDent(Dentista d) throws SQLException, ClassNotFoundException{
        String sql = "INSERT INTO DENTISTA (CRO, NOME, CPF, DT_NASC, EMAIL, TELEFONE, CEP, LOGRADOURO, BAIRRO, UF, LOCALIDADE, NUMERO, COMPLEMENTO) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try(Connection conn = ConexaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, new String[]{"ID"})
        ){
            ps.setString(1, d.getCro());
            ps.setString(2, d.getNome());
            ps.setString(3, d.getCpf());
            ps.setDate(4, java.sql.Date.valueOf(d.getDtNasc()));
            ps.setString(5, d.getEmail());
            ps.setString(6, d.getTelefone());
            ps.setString(7, d.getEndereco().getCep().replaceAll("\\D", ""));
            ps.setString(8, d.getEndereco().getLogradouro());
            ps.setString(9, d.getEndereco().getBairro());
            ps.setString(10, d.getEndereco().getUf());
            ps.setString(11, d.getEndereco().getLocalidade());
            ps.setString(12, d.getEndereco().getNumero());
            ps.setString(13, d.getEndereco().getComplemento());
            ps.executeUpdate();

            try(ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()){
                    d.setId(rs.getLong(1));
                }
            }
        }
    }

    public void deletarDent(Long id) throws SQLException, ClassNotFoundException{
        String sql = "DELETE FROM DENTISTA where ID = ?";

        try(Connection conn = ConexaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ){
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    public void atualizarDent(Dentista d) throws SQLException, ClassNotFoundException{
        String sql = "UPDATE DENTISTA SET NOME=?, DT_NASC=?, EMAIL=?, TELEFONE=?, CEP=?, LOGRADOURO=?, BAIRRO=?, UF=?, LOCALIDADE=?, NUMERO=?, COMPLEMENTO=? WHERE ID=?";

        try(Connection conn = ConexaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ){
            ps.setString(1, d.getNome());
            ps.setDate(2, java.sql.Date.valueOf(d.getDtNasc()));
            ps.setString(3, d.getEmail());
            ps.setString(4, d.getTelefone());
            ps.setString(5, d.getEndereco().getCep().replaceAll("\\D", ""));
            ps.setString(6, d.getEndereco().getLogradouro());
            ps.setString(7, d.getEndereco().getBairro());
            ps.setString(8, d.getEndereco().getUf());
            ps.setString(9, d.getEndereco().getLocalidade());
            ps.setString(10, d.getEndereco().getNumero());
            ps.setString(11, d.getEndereco().getComplemento());
            ps.setLong(12, d.getId());
            ps.executeUpdate();
        }
    }

    public List<Dentista> selecionar() throws SQLException, ClassNotFoundException{
        List<Dentista> listaDentistas = new ArrayList<>();
        String sql = "SELECT * FROM DENTISTA ORDER BY ID";

        try(Connection conn = ConexaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ){
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Dentista dentista = new Dentista();
                    Endereco endereco = new Endereco();

                    dentista.setId(rs.getLong(1));
                    dentista.setCro(rs.getString(2));
                    dentista.setNome(rs.getString(3));
                    dentista.setCpf(rs.getString(4));
                    dentista.setDtNasc(rs.getDate(5).toLocalDate());
                    dentista.setEmail(rs.getString(6));
                    dentista.setTelefone(rs.getString(7));
                    endereco.setCep(rs.getString(8));
                    endereco.setLogradouro(rs.getString(9));
                    endereco.setBairro(rs.getString(10));
                    endereco.setUf(rs.getString(11));
                    endereco.setLocalidade(rs.getString(12));
                    endereco.setNumero(rs.getString(13));
                    endereco.setComplemento(rs.getString(14));
                    dentista.setEndereco(endereco);

                    listaDentistas.add(dentista);
                }
                return listaDentistas;
            }
        }
    }

    public Dentista buscarDentPorId(Long id) throws SQLException, ClassNotFoundException{
        Dentista dentista = null;

        String sql = "SELECT * FROM DENTISTA WHERE ID = ?";

        try(Connection conn = ConexaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ){
            ps.setLong(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    dentista = new Dentista();
                    Endereco endereco = new Endereco();

                    dentista.setId(rs.getLong(1));
                    dentista.setCro(rs.getString(2));
                    dentista.setNome(rs.getString(3));
                    dentista.setCpf(rs.getString(4));
                    dentista.setDtNasc(rs.getDate(5).toLocalDate());
                    dentista.setEmail(rs.getString(6));
                    dentista.setTelefone(rs.getString(7));
                    endereco.setCep(rs.getString(8));
                    endereco.setLogradouro(rs.getString(9));
                    endereco.setBairro(rs.getString(10));
                    endereco.setUf(rs.getString(11));
                    endereco.setLocalidade(rs.getString(12));
                    endereco.setNumero(rs.getString(13));
                    endereco.setComplemento(rs.getString(14));
                    dentista.setEndereco(endereco);
                }
                return dentista;
            }
        }
    }
}
