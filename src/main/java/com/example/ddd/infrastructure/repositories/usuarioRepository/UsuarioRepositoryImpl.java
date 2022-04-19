package com.example.ddd.infrastructure.repositories.usuarioRepository;

import com.example.ddd.core.usuario.Usuario;
import com.example.ddd.core.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UsuarioRepositoryImpl implements UsuarioRepository {

    @Autowired
    private UsuarioAbstractRepositoryDB usuarioAbstractRepositoryDB;

    public UsuarioRepositoryImpl(UsuarioAbstractRepositoryDB usuarioAbstractRepositoryDB) {
        this.usuarioAbstractRepositoryDB = usuarioAbstractRepositoryDB;
    }

    @Override
    public Usuario createUser(Usuario usuario) {
        return usuarioAbstractRepositoryDB.criaUsuario(usuario);
    }

    @Override
    public Usuario updateUser(Usuario usuario) {
        return usuarioAbstractRepositoryDB.updateUsuario(usuario.getUserId(), usuario);
    }

    @Override
    public Optional<Usuario> findByUserId(String usuarioId) {
        return Optional.ofNullable(usuarioAbstractRepositoryDB.buscaUsuarioPorId(usuarioId));
    }

    @Override
    public List<Usuario> findAllUser() {
        return usuarioAbstractRepositoryDB.buscaUsuarios();
    }

    @Override
    public void deleteUser(Usuario user) {
        usuarioAbstractRepositoryDB.deletaUsuario(user.getUserId());
    }
}
