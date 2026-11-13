package com.igorgodoi.bff_agendador_tarefas.business;


import com.igorgodoi.bff_agendador_tarefas.business.dto.in.LoginRequestDTO;
import com.igorgodoi.bff_agendador_tarefas.business.dto.out.TarefasDTOResponse;
import com.igorgodoi.bff_agendador_tarefas.business.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j

public class CronService {

    private final TarefasService tarefasService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;

    @Scheduled(cron = "${cron.horario}")

    public void buscaTarefasProximaHora(){
        String token = login(converterLoginRequestDTO());

        log.info("iniciada a busca de tarefas");

        LocalDateTime horaAtual = LocalDateTime.now();
        LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);
        List<TarefasDTOResponse> listaDeTarefas = tarefasService.buscaTarefasAgendadasPorPeriodo(horaAtual, horaFutura, token);

        log.info("tarefas encontradas" + listaDeTarefas);

        listaDeTarefas.forEach( tarefa -> {
            emailService.enviaEmail(tarefa);
            log.info("email enviado para o usuario " + tarefa.getEmailUsuario());
            tarefasService.alteraStatus(StatusNotificacaoEnum.NOTIFICADO, tarefa.getId(),
                    token);

        });
        log.info("finalizada a busca e a notificao de tarefas");

    }

    public String login(LoginRequestDTO dto){
        return usuarioService.loginUsuario(dto);
    }

    public LoginRequestDTO converterLoginRequestDTO (){
        return LoginRequestDTO.builder()
                .email(email)
                .senha(senha)
                .build();
    }
}
