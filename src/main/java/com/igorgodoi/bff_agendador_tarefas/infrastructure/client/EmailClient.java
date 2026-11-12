package com.igorgodoi.bff_agendador_tarefas.infrastructure.client;

import com.igorgodoi.bff_agendador_tarefas.business.dto.in.EnderecoDTORequest;
import com.igorgodoi.bff_agendador_tarefas.business.dto.in.LoginRequestDTO;
import com.igorgodoi.bff_agendador_tarefas.business.dto.in.TelefoneDTORequest;
import com.igorgodoi.bff_agendador_tarefas.business.dto.in.UsuarioDTORequest;
import com.igorgodoi.bff_agendador_tarefas.business.dto.out.EnderecoDTOResponse;
import com.igorgodoi.bff_agendador_tarefas.business.dto.out.TarefasDTOResponse;
import com.igorgodoi.bff_agendador_tarefas.business.dto.out.TelefoneDTOResponse;
import com.igorgodoi.bff_agendador_tarefas.business.dto.out.UsuarioDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "notificao", url = "${notificacao.url}") // quando quiser apontar a url utiliza o "$" e aponta no application.properties
public interface EmailClient {

    void enviarEmail(@RequestBody TarefasDTOResponse dto);

    }
