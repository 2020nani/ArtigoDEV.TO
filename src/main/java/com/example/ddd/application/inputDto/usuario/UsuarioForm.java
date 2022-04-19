package com.example.ddd.application.inputDto.usuario;

import com.example.ddd.core.usuario.Usuario;

import java.util.UUID;

public class UsuarioForm {

    private String name;

    private String email;

    private String cpf;

    public UsuarioForm(String name, String email, String cpf) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
    }

    public Usuario converte() {
        Usuario usuario = new Usuario(name,email,cpf);
        return usuario;
    }
}
