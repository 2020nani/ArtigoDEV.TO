package com.example.ddd.core.usuario;

import com.example.ddd.application.inputDto.usuario.UsuarioForm;
import com.example.ddd.application.outputDto.usuario.UsuarioDto;

import java.util.List;


public interface UsuarioService {
    public Usuario createUser(Usuario usuario);
    public String updateUser(UsuarioForm usuarioForm, String userId);
    public List<UsuarioDto> findAllUser();
    public UsuarioDto findByUserId(String usuarioId);
    public void deleteUser(String usuarioId);
}
