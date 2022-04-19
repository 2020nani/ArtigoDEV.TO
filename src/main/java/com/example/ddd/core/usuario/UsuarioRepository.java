package com.example.ddd.core.usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {

    public Usuario createUser(Usuario user);
    public Usuario updateUser(Usuario user);
    public Optional<Usuario> findByUserId(String userId);
    public List<Usuario> findAllUser();
    public void deleteUser(Usuario user);
}
