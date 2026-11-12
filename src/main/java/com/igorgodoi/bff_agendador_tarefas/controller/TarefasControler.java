package com.igorgodoi.bff_agendador_tarefas.controller;


import com.igorgodoi.bff_agendador_tarefas.business.TarefasService;
import com.igorgodoi.bff_agendador_tarefas.business.dto.in.TarefasDTORequest;
import com.igorgodoi.bff_agendador_tarefas.business.dto.out.TarefasDTOResponse;
import com.igorgodoi.bff_agendador_tarefas.business.enums.StatusNotificacaoEnum;
import com.igorgodoi.bff_agendador_tarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Cadastra tarefas de usuarios")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)

public class TarefasControler {
    private final TarefasService tarefasService;

    @PostMapping
    @Operation(summary = "salvar tarefas de usuarios" , description = "Cria um nova tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa salva com sucesso")
    @ApiResponse(responseCode = "500", description = "Error de servidor")
    public ResponseEntity<TarefasDTOResponse> gravarTarefas(@RequestBody TarefasDTORequest dto,
                                                            @RequestHeader(value = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.gravarTarefa(token, dto));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Busca tarefas por periodo" , description = "Busca tarefas cadastrada por periodo")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500", description = "Error de servidor")
    public ResponseEntity<List<TarefasDTOResponse>> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(name = "Authorization", required = false) String token) {

        return ResponseEntity.ok(tarefasService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal, token));
    }

    @GetMapping
    @Operation(summary = "Busca lista tarefas por email de usuario" ,
                description = "Busca tarefas cadastrada por usuario")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500", description = "Error de servidor")
    public ResponseEntity<List<TarefasDTOResponse>> buscaPorTarefasEmail(@RequestHeader(name = "Authorization", required = false) String token) {
        List<TarefasDTOResponse> tarefas = tarefasService.buscaTarefasPorEmail(token);
        return ResponseEntity.ok(tarefas);
    }

    @DeleteMapping
    @Operation(summary = "Deleta tarefas por Id" , description = "Deleta tarefas cadastras por Id")
    @ApiResponse(responseCode = "200", description = "Tarefas deletadas")
    @ApiResponse(responseCode = "500", description = "Error de servidor")
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id,
                                                  @RequestHeader(name = "Authorization", required = false) String token) {

        tarefasService.deletaTarefaPorID(id, token);

        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Altera status de tarefas" , description = "Altera status das tarefas cadastradas")
    @ApiResponse(responseCode = "200", description = "Status da tarefa alterado")
    @ApiResponse(responseCode = "500", description = "Error de servidor")
    public ResponseEntity<TarefasDTOResponse> alteraStatusNotificao(@RequestParam("status") StatusNotificacaoEnum status,
                                                                    @RequestParam("id") String id,
                                                                    @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.alteraStatus(status, id, token));
    }

    @PutMapping
    @Operation(summary = "Altera dados de tarefas" , description = "Altera dados das tarefas cadastradas")
    @ApiResponse(responseCode = "200", description = "Tarefas alteradas")
    @ApiResponse(responseCode = "500", description = "Error de servidor")
    public ResponseEntity<TarefasDTOResponse> updateDeTarefas (@RequestBody TarefasDTORequest dto,
                                                               @RequestParam("id") String id,
                                                               @RequestHeader(name = "Authorization", required = false) String token){
    return ResponseEntity.ok(tarefasService.updateTarefas(dto, id, token));
    }
}

