package com.example.ddd.application.outputDto.usuario;

public class UsuarioDto {

    private String id;

    private String name;

    private String email;

    private String cpf;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public UsuarioDto(String id, String name, String email, String cpf) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
    }
}
