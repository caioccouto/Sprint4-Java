package org.acme.dao;

import org.acme.conexao.ConexaoFactory;
import org.acme.domain.Voluntario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VoluntarioDAO {

    public Connection conn;

    public VoluntarioDAO() throws SQLException, ClassNotFoundException{
        super();
        this.conn = new ConexaoFactory().getConnection();
    }

    public void inserirVol(Voluntario v) throws SQLException{
        String sql = "INSERT INTO VOLUNTARIO (CRO, NOME, CPF, DT_NASC, EMAIL, TELEFONE, CEP, LOGRADOURO, BAIRRO, UF, LOCALIDADE, NUMERO, COMPLEMENTO, DATA_CADASTRO) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement ps = conn.prepareStatement(sql, new String[]{"ID"});

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

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()){
            v.setId(rs.getLong(1));
        }

        rs.close();
        ps.close();
    }

    public void deletarVol(Long id) throws SQLException{
        String sql = "DELETE FROM VOLUNTARIO where ID = ?";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setLong(1, id);
        ps.executeUpdate();
        ps.close();
    }

    public void atualizarVol(Voluntario v) throws SQLException{
        String sql = "UPDATE VOLUNTARIO SET NOME=?, DT_NASC=?, EMAIL=?, TELEFONE=?, CEP=?, LOGRADOURO=?, BAIRRO=?, UF=?, LOCALIDADE=?, NUMERO=?, COMPLEMENTO=?, DATA_CADASTRO=? WHERE ID=?";

        PreparedStatement ps = conn.prepareStatement(sql);

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
        ps.close();
    }

    public List<Voluntario> selecionar() throws SQLException {
        List<Voluntario> listaVoluntarios = new ArrayList<>();
        String sql = "SELECT * FROM VOLUNTARIOS";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            Voluntario voluntario = new Voluntario();

            voluntario.setId(rs.getLong(1));
            voluntario.setCro(rs.getString(2));
            voluntario.setNome(rs.getString(3));
            voluntario.setCpf(rs.getString(4));
            voluntario.setDtNasc(rs.getDate(5).toLocalDate());
            voluntario.setEmail(rs.getString(6));
            voluntario.setTelefone(rs.getString(7));
            voluntario.getEndereco().setCep(rs.getString(8));
            voluntario.getEndereco().setLogradouro(rs.getString(9));
            voluntario.getEndereco().setBairro(rs.getString(10));
            voluntario.getEndereco().setUf(rs.getString(11));
            voluntario.getEndereco().setLocalidade(rs.getString(12));
            voluntario.getEndereco().setNumero(rs.getString(13));
            voluntario.getEndereco().setComplemento(rs.getString(14));
            voluntario.setDtCadastro(rs.getDate(15).toLocalDate());

            listaVoluntarios.add(voluntario);
        }
        return listaVoluntarios;
    }

    public Voluntario buscarVolPorId(Long id) throws SQLException {
        Voluntario voluntario = null;

        String sql = "SELECT * FROM VOLUNTARIO WHERE ID = ?";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setLong(1, id);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            voluntario = new Voluntario();

            voluntario.setId(rs.getLong(1));
            voluntario.setCro(rs.getString(2));
            voluntario.setNome(rs.getString(3));
            voluntario.setCpf(rs.getString(4));
            voluntario.setDtNasc(rs.getDate(5).toLocalDate());
            voluntario.setEmail(rs.getString(6));
            voluntario.setTelefone(rs.getString(7));
            voluntario.getEndereco().setCep(rs.getString(8));
            voluntario.getEndereco().setLogradouro(rs.getString(9));
            voluntario.getEndereco().setBairro(rs.getString(10));
            voluntario.getEndereco().setUf(rs.getString(11));
            voluntario.getEndereco().setLocalidade(rs.getString(12));
            voluntario.getEndereco().setNumero(rs.getString(13));
            voluntario.getEndereco().setComplemento(rs.getString(14));
            voluntario.setDtCadastro(rs.getDate(15).toLocalDate());
        }

        rs.close();
        ps.close();

        return voluntario;
    }
}
