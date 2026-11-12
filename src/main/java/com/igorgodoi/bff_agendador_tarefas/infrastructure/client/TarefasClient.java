package com.igorgodoi.bff_agendador_tarefas.infrastructure.client;

import com.igorgodoi.bff_agendador_tarefas.business.dto.in.TarefasDTORequest;
import com.igorgodoi.bff_agendador_tarefas.business.dto.out.TarefasDTOResponse;
import com.igorgodoi.bff_agendador_tarefas.business.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

    @FeignClient(name = "agendador-tarefas", url = "${agendador-tarefas.url}") // quando quiser apontar a url utiliza o "$" e aponta no application.properties
    public interface TarefasClient {

        @PostMapping
        TarefasDTOResponse gravarTarefas(@RequestBody TarefasDTORequest dto,
                                         @RequestHeader("Authorization") String token);


    @GetMapping("/eventos")
    List<TarefasDTOResponse> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal, // @DATE... faz a conversao das data para o padrao que o DB consegue ler
            @RequestHeader("Authorization") String token);

    @GetMapping
    List<TarefasDTOResponse> buscaPorTarefasEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping
   void deletaTarefaPorId(@RequestParam("id") String id,
                            @RequestHeader("Authorization") String token);

    @PatchMapping
    TarefasDTOResponse alteraStatusNotificao(@RequestParam("status") StatusNotificacaoEnum status,
                                             @RequestParam("id") String id,
                                             @RequestHeader("Authorization") String token);

    @PutMapping
    TarefasDTOResponse updateDeTarefas (@RequestBody TarefasDTORequest dto,
                                        @RequestParam("id") String id,
                                        @RequestHeader("Authorization") String token);
}



