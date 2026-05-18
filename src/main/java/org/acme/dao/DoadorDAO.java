package org.acme.dao;

import org.acme.conexao.ConexaoFactory;
import org.acme.domain.Doador;
import org.acme.domain.Endereco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoadorDAO {

    public void inserirDoador(Doador d) throws SQLException, ClassNotFoundException{
        String sql = "INSERT INTO DOADOR (NOME, DOCUMENTO, DT_NASC, EMAIL, TELEFONE, CEP, LOGRADOURO, BAIRRO, UF, LOCALIDADE, NUMERO, COMPLEMENTO) values (?,?,?,?,?,?,?,?,?,?,?,?)";

        try(Connection conn = ConexaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, new String[]{"ID"})
        ) {
            ps.setString(1, d.getNome());
            ps.setString(2, d.getDocumento());
            ps.setDate(3, java.sql.Date.valueOf(d.getDtNasc()));
            ps.setString(4, d.getEmail());
            ps.setString(5, d.getTelefone());
            ps.setString(6, d.getEndereco().getCep().replaceAll("\\D", ""));
            ps.setString(7, d.getEndereco().getLogradouro());
            ps.setString(8, d.getEndereco().getBairro());
            ps.setString(9, d.getEndereco().getUf());
            ps.setString(10, d.getEndereco().getLocalidade());
            ps.setString(11, d.getEndereco().getNumero());
            ps.setString(12, d.getEndereco().getComplemento());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    d.setId(rs.getLong(1));
                }
            }
        }
    }

    public void deletarDoador(Long id) throws SQLException, ClassNotFoundException{
        String sql = "DELETE FROM DOADOR where ID = ?";

        try(Connection conn = ConexaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ){
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    public void atualizarDoador(Doador d) throws SQLException, ClassNotFoundException{
        String sql = "UPDATE DOADOR SET NOME=?, DT_NASC=?, EMAIL=?, TELEFONE=?, CEP=?, LOGRADOURO=?, BAIRRO=?, UF=?, LOCALIDADE=?, NUMERO=?, COMPLEMENTO=? WHERE ID=?";

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

    public List<Doador> selecionar() throws SQLException, ClassNotFoundException{
        List<Doador> listaDoadores = new ArrayList<>();
        String sql = "SELECT * FROM DOADOR ORDER BY ID";

        try(Connection conn = ConexaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ){
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Doador doador = new Doador();
                    Endereco endereco = new Endereco();

                    doador.setId(rs.getLong(1));
                    doador.setNome(rs.getString(2));
                    doador.setDocumento(rs.getString(3));
                    doador.setDtNasc(rs.getDate(4).toLocalDate());
                    doador.setEmail(rs.getString(5));
                    doador.setTelefone(rs.getString(6));
                    endereco.setCep(rs.getString(7));
                    endereco.setLogradouro(rs.getString(8));
                    endereco.setBairro(rs.getString(9));
                    endereco.setUf(rs.getString(10));
                    endereco.setLocalidade(rs.getString(11));
                    endereco.setNumero(rs.getString(12));
                    endereco.setComplemento(rs.getString(13));
                    doador.setEndereco(endereco);

                    listaDoadores.add(doador);
                }
                return listaDoadores;
            }
        }
    }

    public Doador buscarDoadorPorId(Long id) throws SQLException, ClassNotFoundException{
        Doador doador = null;

        String sql = "SELECT * FROM DOADOR WHERE ID = ?";

        try(Connection conn = ConexaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ){
            ps.setLong(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    doador = new Doador();
                    Endereco endereco = new Endereco();

                    doador.setId(rs.getLong(1));
                    doador.setNome(rs.getString(2));
                    doador.setDocumento(rs.getString(3));
                    doador.setDtNasc(rs.getDate(4).toLocalDate());
                    doador.setEmail(rs.getString(5));
                    doador.setTelefone(rs.getString(6));
                    endereco.setCep(rs.getString(7));
                    endereco.setLogradouro(rs.getString(8));
                    endereco.setBairro(rs.getString(9));
                    endereco.setUf(rs.getString(10));
                    endereco.setLocalidade(rs.getString(11));
                    endereco.setNumero(rs.getString(12));
                    endereco.setComplemento(rs.getString(13));
                    doador.setEndereco(endereco);
                }
                return doador;
            }
        }
    }
}
