package com.example.ddd.application.controllers.usuariocontroller;

import com.example.ddd.application.inputDto.usuario.UsuarioForm;
import com.example.ddd.application.outputDto.usuario.UsuarioDto;
import com.example.ddd.core.usuario.Usuario;
import com.example.ddd.core.usuario.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("user")
    public String createUser(@RequestBody UsuarioForm usuarioForm){
        Usuario user = usuarioForm.converte();
        usuarioService.createUser(user);
        return "ok";
    }

    @GetMapping("user")
    public List<UsuarioDto> findAllUser(){
        return usuarioService.findAllUser();
    }

    @PutMapping("user/{usuarioId}")
    public String updateUser(@RequestBody UsuarioForm usuarioForm, @PathVariable("usuarioId") String usuarioId){
        usuarioService.updateUser(usuarioForm, usuarioId);
        return "atualizado";
    }

    @GetMapping("user/{usuarioId}")
    public UsuarioDto updateUser(@PathVariable("usuarioId") String usuarioId){
        return usuarioService.findByUserId(usuarioId);
    }

    @DeleteMapping("user/{usuarioId}")
    public void deleteUser(@PathVariable("usuarioId") String usuarioId){
        usuarioService.deleteUser(usuarioId);
    }
}
