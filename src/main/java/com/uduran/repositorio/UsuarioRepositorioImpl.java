package com.uduran.repositorio;

import com.uduran.models.Usuario;
import com.uduran.util.ConexionBBDD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UsuarioRepositorioImpl implements Repositorio<Usuario>{

    private Connection getConnection() throws SQLException {
        return ConexionBBDD.getInstance();
    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios")) {
            while(rs.next()){
                Usuario u = getUsuario(rs);
                usuarios.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public Usuario porId(Long id) {
        Usuario u = null;
        try(Connection conn = getConnection();
            PreparedStatement stmt = conn
                    .prepareStatement("SELECT * FROM usuarios WHERE id=?")){
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    u = getUsuario(rs);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public void guardar(Usuario usuario) {
        String sql;
        if (!Objects.isNull(usuario.getId()) && usuario.getId()>0){
            sql = "UPDATE usuarios SET username=?, password=?, email=? WHERE id=?";
        }else {
            sql = "INSERT INTO usuarios(username, password, email) VALUES(?,?,?)";
        }
        try(Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getPassword());
            stmt.setString(3, usuario.getMail());
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Long id) {
        try(Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM usuarios WHERE id=?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Usuario getUsuario(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setId(rs.getLong("id"));
        u.setUsername(rs.getString("username"));
        u.setPassword(rs.getString("password"));
        u.setMail(rs.getString("email"));
        return u;
    }
}
