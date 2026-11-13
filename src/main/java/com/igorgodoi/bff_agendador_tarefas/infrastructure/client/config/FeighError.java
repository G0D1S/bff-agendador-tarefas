package com.igorgodoi.bff_agendador_tarefas.infrastructure.client.config;

import com.igorgodoi.bff_agendador_tarefas.infrastructure.exceptions.BusinessException;
import com.igorgodoi.bff_agendador_tarefas.infrastructure.exceptions.ConflictException;
import com.igorgodoi.bff_agendador_tarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.igorgodoi.bff_agendador_tarefas.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeighError implements ErrorDecoder {


    @Override
    public Exception decode(String methodKey, Response response) {

        switch (response.status()){
            case 409:
                return new ConflictException("Erro atributo ja existente ");
            case 403:
                return new ResourceNotFoundException("Erro atributo nao encontrado ");
            case 401:
                return new UnauthorizedException("Erro usuario nao autorizado");
            default:
                return new BusinessException("Erro de servidor ");

        }
    }
}
