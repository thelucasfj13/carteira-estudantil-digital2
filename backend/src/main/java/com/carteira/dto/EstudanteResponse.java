package com.carteira.dto;

import com.carteira.model.Estudante;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class EstudanteResponse {
    private Long id;
    private String codigoCarteira;
    private String nomeCompleto;
    private String instituicao;
    private String curso;
    private String nivelEnsino;
    private String matricula;
    private String cpfMascarado;
    private LocalDate dataNascimento;
    private LocalDate validadeCarteira;
    private String fotoUrl;
    private String qrCodeUrl;
    private String status;
    private LocalDateTime dataEmissao;
    private LocalDateTime dataAtualizacao;

    public static EstudanteResponse fromEntity(Estudante e) {
        EstudanteResponse r = new EstudanteResponse();
        r.setId(e.getId());
        r.setCodigoCarteira(e.getCodigoCarteira());
        r.setNomeCompleto(e.getNomeCompleto());
        r.setInstituicao(e.getInstituicao());
        r.setCurso(e.getCurso());
        r.setNivelEnsino(e.getNivelEnsino());
        r.setMatricula(e.getMatricula());
        r.setCpfMascarado(mascararCpf(e.getCpf()));
        r.setDataNascimento(e.getDataNascimento());
        r.setValidadeCarteira(e.getValidadeCarteira());
        r.setFotoUrl(e.getFotoUrl());
        r.setQrCodeUrl(e.getQrCodeUrl());
        r.setStatus(e.getStatus().name());
        r.setDataEmissao(e.getDataEmissao());
        r.setDataAtualizacao(e.getDataAtualizacao());
        return r;
    }

    private static String mascararCpf(String cpf) {
        if (cpf == null || cpf.length() != 11) return "***.***.***-**";
        return "***." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-**";
    }
}