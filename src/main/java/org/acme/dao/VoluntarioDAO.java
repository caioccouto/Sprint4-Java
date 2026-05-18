package org.acme.dao;

import org.acme.conexao.ConexaoFactory;
import org.acme.domain.Endereco;
import org.acme.domain.Voluntario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VoluntarioDAO {

    public void inserirVol(Voluntario v) throws SQLException, ClassNotFoundException{
        String sql = "INSERT INTO VOLUNTARIO (CRO, NOME, CPF, DT_NASC, EMAIL, TELEFONE, CEP, LOGRADOURO, BAIRRO, UF, LOCALIDADE, NUMERO, COMPLEMENTO, DATA_CADASTRO) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try(Connection conn = ConexaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql, new String[]{"ID"})
        ){
            ps.setString(1, v.getCro());
            ps.setString(2, v.getNome());
            ps.setString(3, v.getCpf());
            ps.setDate(4, java.sql.Date.valueOf(v.getDtNasc()));
            ps.setString(5, v.getEmail());
            ps.setString(6, v.getTelefone());
            ps.setString(7, v.getEndereco().getCep().replaceAll("\\D", ""));
            ps.setString(8, v.getEndereco().getLogradouro());
            ps.setString(9, v.getEndereco().getBairro());
            ps.setString(10, v.getEndereco().getUf());
            ps.setString(11, v.getEndereco().getLocalidade());
            ps.setString(12, v.getEndereco().getNumero());
            ps.setString(13, v.getEndereco().getComplemento());
            ps.setDate(14, java.sql.Date.valueOf(v.getDtCadastro()));
            ps.executeUpdate();

            try(ResultSet rs = ps.getGeneratedKeys()){
                if (rs.next()){
                    v.setId(rs.getLong(1));
                }
            }
        }
    }

    public void deletarVol(Long id) throws SQLException, ClassNotFoundException{
        String sql = "DELETE FROM VOLUNTARIO where ID = ?";

        try(Connection conn = ConexaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ){
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    public void atualizarVol(Voluntario v) throws SQLException, ClassNotFoundException{
        String sql = "UPDATE VOLUNTARIO SET NOME=?, DT_NASC=?, EMAIL=?, TELEFONE=?, CEP=?, LOGRADOURO=?, BAIRRO=?, UF=?, LOCALIDADE=?, NUMERO=?, COMPLEMENTO=?, DATA_CADASTRO=? WHERE ID=?";

        try(Connection conn = ConexaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ){
            ps.setString(1, v.getNome());
            ps.setDate(2, java.sql.Date.valueOf(v.getDtNasc()));
            ps.setString(3, v.getEmail());
            ps.setString(4, v.getTelefone());
            ps.setString(5, v.getEndereco().getCep().replaceAll("\\D", ""));
            ps.setString(6, v.getEndereco().getLogradouro());
            ps.setString(7, v.getEndereco().getBairro());
            ps.setString(8, v.getEndereco().getUf());
            ps.setString(9, v.getEndereco().getLocalidade());
            ps.setString(10, v.getEndereco().getNumero());
            ps.setString(11, v.getEndereco().getComplemento());
            ps.setDate(12, java.sql.Date.valueOf(v.getDtCadastro()));
            ps.setLong(13, v.getId());
            ps.executeUpdate();
        }
    }

    public List<Voluntario> selecionar() throws SQLException, ClassNotFoundException{
        List<Voluntario> listaVoluntarios = new ArrayList<>();
        String sql = "SELECT * FROM VOLUNTARIOS";
        try(Connection conn = ConexaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ){
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Voluntario voluntario = new Voluntario();
                    Endereco endereco = new Endereco();

                    voluntario.setId(rs.getLong(1));
                    voluntario.setCro(rs.getString(2));
                    voluntario.setNome(rs.getString(3));
                    voluntario.setCpf(rs.getString(4));
                    voluntario.setDtNasc(rs.getDate(5).toLocalDate());
                    voluntario.setEmail(rs.getString(6));
                    voluntario.setTelefone(rs.getString(7));
                    endereco.setCep(rs.getString(8));
                    endereco.setLogradouro(rs.getString(9));
                    endereco.setBairro(rs.getString(10));
                    endereco.setUf(rs.getString(11));
                    endereco.setLocalidade(rs.getString(12));
                    endereco.setNumero(rs.getString(13));
                    endereco.setComplemento(rs.getString(14));
                    voluntario.setDtCadastro(rs.getDate(15).toLocalDate());
                    voluntario.setEndereco(endereco);
                    listaVoluntarios.add(voluntario);
                }
                return listaVoluntarios;
            }
        }
    }

    public Voluntario buscarVolPorId(Long id) throws SQLException, ClassNotFoundException{
        Voluntario voluntario = null;

        String sql = "SELECT * FROM VOLUNTARIO WHERE ID = ?";

        try(Connection conn = ConexaoFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ){
            ps.setLong(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    voluntario = new Voluntario();
                    Endereco endereco = new Endereco();

                    voluntario.setId(rs.getLong(1));
                    voluntario.setCro(rs.getString(2));
                    voluntario.setNome(rs.getString(3));
                    voluntario.setCpf(rs.getString(4));
                    voluntario.setDtNasc(rs.getDate(5).toLocalDate());
                    voluntario.setEmail(rs.getString(6));
                    voluntario.setTelefone(rs.getString(7));
                    endereco.setCep(rs.getString(8));
                    endereco.setLogradouro(rs.getString(9));
                    endereco.setBairro(rs.getString(10));
                    endereco.setUf(rs.getString(11));
                    endereco.setLocalidade(rs.getString(12));
                    endereco.setNumero(rs.getString(13));
                    endereco.setComplemento(rs.getString(14));
                    voluntario.setDtCadastro(rs.getDate(15).toLocalDate());
                    voluntario.setEndereco(endereco);
                }
                return voluntario;
            }
        }
    }
}
