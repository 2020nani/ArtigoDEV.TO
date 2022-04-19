package com.example.ddd.core.usuario;

import com.example.ddd.application.inputDto.usuario.UsuarioForm;
import com.example.ddd.application.outputDto.usuario.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario createUser(Usuario usuario) {
        return usuarioRepository.createUser(usuario);
    }

    @Override
    public String updateUser(UsuarioForm usuarioForm, String userId) {
        Usuario usuario = usuarioRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Nao ha usuario com o id: " + userId.toString()));
        Usuario usuarioUpdate = usuarioForm.converte();
        usuarioUpdate.setUserId(usuario.getUserId());
        usuarioRepository.updateUser(usuarioUpdate);
        return "atualizado";
    }

    @Override
    public List<UsuarioDto> findAllUser() {
        return usuarioRepository.findAllUser().stream().map(it -> {
            return new UsuarioDto(it.getUserId(), it.getName(), it.getEmail(), it.getCpf());
        }).collect(Collectors.toList());
    }

    @Override
    public UsuarioDto findByUserId(String usuarioId) {
        Usuario usuario = usuarioRepository.findByUserId(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Nao ha usuario cadastrado com o id: " + usuarioId.toString()));
        System.out.println(usuario.toString());
        return new UsuarioDto(usuario.getUserId(), usuario.getName(), usuario.getEmail(), usuario.getCpf());
    }

    @Override
    public void deleteUser(String usuarioId) {
      Usuario usuario = usuarioRepository.findByUserId(usuarioId)
              .orElseThrow(() -> new IllegalArgumentException("Nao ha usuario cadastrado com o id: " + usuarioId.toString()));
      usuarioRepository.deleteUser(usuario);
    }
}
