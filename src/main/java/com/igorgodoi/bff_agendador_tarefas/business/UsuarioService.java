package com.igorgodoi.bff_agendador_tarefas.business;



import com.igorgodoi.bff_agendador_tarefas.business.dto.EnderecoDTO;
import com.igorgodoi.bff_agendador_tarefas.business.dto.TelefoneDTO;
import com.igorgodoi.bff_agendador_tarefas.business.dto.UsuarioDTO;
import com.igorgodoi.bff_agendador_tarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient client;

    public UsuarioDTO salvaUsuario (UsuarioDTO usuarioDTO) {

        return client.salvaUsuario(usuarioDTO);
    }


   public String loginUsuario(UsuarioDTO usuarioDTO){
        return client.login(usuarioDTO);
    }


    public UsuarioDTO buscarUsuarioPorEmail(String email, String token) {
        return client.buscaUsuarioPorEmail(email, token);
    }


    public void deletaUsuarioPorEmail(String email, String token) {

            client.deletaUsuarioPorEmail(email, token);
    }


    public  UsuarioDTO autalizaDadosUsuario (String token, UsuarioDTO dto){

        return client.atualizaDadosUsuario(dto, token);
    }


    public EnderecoDTO atualizaEndereco(Long idEndereco, EnderecoDTO enderecoDTO, String token){

        return client.atualizaEndereco(enderecoDTO, idEndereco, token);
    }


    public TelefoneDTO atulizaTelefone(Long idTelefone, TelefoneDTO dto, String token ){
      return client.atualizaTelefone(dto, idTelefone, token);
    }


    public EnderecoDTO cadastraEndereco(String token, EnderecoDTO dto){
        return client.cadastraEndereco(dto, token);

    }


    public TelefoneDTO cadastraTelefone(String token, TelefoneDTO dto){
        return client.cadastraTelefone(dto, token);
    }


}

