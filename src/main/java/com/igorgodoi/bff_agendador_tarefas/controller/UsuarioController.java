package com.igorgodoi.bff_agendador_tarefas.controller;


import com.igorgodoi.bff_agendador_tarefas.business.UsuarioService;
import com.igorgodoi.bff_agendador_tarefas.business.dto.in.EnderecoDTORequest;
import com.igorgodoi.bff_agendador_tarefas.business.dto.in.LoginRequestDTO;
import com.igorgodoi.bff_agendador_tarefas.business.dto.in.TelefoneDTORequest;
import com.igorgodoi.bff_agendador_tarefas.business.dto.in.UsuarioDTORequest;
import com.igorgodoi.bff_agendador_tarefas.business.dto.out.EnderecoDTOResponse;
import com.igorgodoi.bff_agendador_tarefas.business.dto.out.TelefoneDTOResponse;
import com.igorgodoi.bff_agendador_tarefas.business.dto.out.UsuarioDTOResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuario", description = "Cadastro e login de usuario")


public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "salvar usuarios" , description = "Cria um novo usuario")
    @ApiResponse(responseCode = "200", description = "Usuario salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuario ja cadastrado")
    @ApiResponse(responseCode = "500", description = "Error de servidor")


    public ResponseEntity<UsuarioDTOResponse> salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO){
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Login de usuarios" , description = "login do usuario")
    @ApiResponse(responseCode = "200", description = "Usuario logado")
    @ApiResponse(responseCode = "401", description = "Credenciais invalidas")
    @ApiResponse(responseCode = "500", description = "Error de servidor")
    public String login(@RequestBody LoginRequestDTO usuarioDTO) {

        return usuarioService.loginUsuario(usuarioDTO);
    }

    @GetMapping
    @Operation(summary = "Buscar dados de usuarios por email" , description = "buscar dados do usuario")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado ")
    @ApiResponse(responseCode = "500", description = "Error de servidor")
    public ResponseEntity<UsuarioDTOResponse> buscaUsuarioPorEmail(@RequestParam("email") String email,
                                                                   @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deleta usuario por ID" , description = "Deleta usuario ")
    @ApiResponse(responseCode = "200", description = "Usuario deletado")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Error de servidor")
    public ResponseEntity<Void>deletaUsuarioPorEmail(@PathVariable String email,
                                                     @RequestHeader(name = "Authorization", required = false) String token) {
        usuarioService.deletaUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atulizar dados do Usuario" ,
            description = "Atulizar dados do Usuario")
    @ApiResponse(responseCode = "200", description = "Usuario atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Error de servidor")
    public ResponseEntity<UsuarioDTOResponse> atualizaDadosUsuario(@RequestBody UsuarioDTORequest dto,
                                                                   @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.autalizaDadosUsuario(token, dto));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atulizar endereco do Usuario" ,
            description = "Atulizar endereco do Usuario")
    @ApiResponse(responseCode = "200", description = "Endereco atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Error de servidor")
    public ResponseEntity<EnderecoDTOResponse> atualizaEndereco(@RequestBody EnderecoDTORequest dto,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, dto, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atulizar telefone do Usuario" ,
            description = "Atulizar telefone do Usuario")
    @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Error de servidor")
    public ResponseEntity<TelefoneDTOResponse> atualizaTelefone(@RequestBody TelefoneDTORequest dto,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atulizaTelefone(id, dto, token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Salva endereco do Usuario" ,
            description = "Salva endereco do Usuario")
    @ApiResponse(responseCode = "200", description = "Endereco salvo com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Error de servidor")
    public ResponseEntity<EnderecoDTOResponse> cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                                                @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token, dto));
}
    @PostMapping("/telefone")
    @Operation(summary = "Salva telefone do Usuario" ,
            description = "Salva telefone do Usuario")
    @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario nao encontrado")
    @ApiResponse(responseCode = "500", description = "Error de servidor")
    public ResponseEntity<TelefoneDTOResponse> cadastraTelefone(@RequestBody TelefoneDTORequest dto,
                                                                @RequestHeader(name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token, dto));
    }

    }