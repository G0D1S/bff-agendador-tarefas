package com.igorgodoi.bff_agendador_tarefas.business;


import com.igorgodoi.bff_agendador_tarefas.business.dto.in.TarefasDTORequest;
import com.igorgodoi.bff_agendador_tarefas.business.dto.out.TarefasDTOResponse;
import com.igorgodoi.bff_agendador_tarefas.business.enums.StatusNotificacaoEnum;
import com.igorgodoi.bff_agendador_tarefas.infrastructure.client.TarefasClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasClient tarefasClient;

    public TarefasDTOResponse gravarTarefa(String token, TarefasDTORequest dto){
        return tarefasClient.gravarTarefas(dto, token);
    }

    public List<TarefasDTOResponse> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial,
                                                                    LocalDateTime dataFinal,
                                                                    String token) {
        return tarefasClient.buscaListaDeTarefasPorPeriodo(dataInicial,dataFinal,token);
    }

    public List<TarefasDTOResponse> buscaTarefasPorEmail(String token) {
        return tarefasClient.buscaPorTarefasEmail(token);
    }

    public void deletaTarefaPorID(String id, String token) {
        tarefasClient.deletaTarefaPorId(id, token);
    }

    public TarefasDTOResponse alteraStatus(StatusNotificacaoEnum status, String id, String token) {
        return tarefasClient.alteraStatusNotificao(status, id, token);
    }

    public TarefasDTOResponse updateTarefas(TarefasDTORequest dto, String id, String token){
       return  tarefasClient.updateDeTarefas(dto, id, token);

    }

}
