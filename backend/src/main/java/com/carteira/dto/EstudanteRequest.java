package com.carteira.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class EstudanteRequest {
    @NotBlank(message = "Nome completo é obrigatório")
    @Size(min = 3, max = 200, message = "Nome deve ter entre 3 e 200 caracteres")
    private String nomeCompleto;

    @NotBlank(message = "Instituição é obrigatória")
    private String instituicao;

    @NotBlank(message = "Curso é obrigatório")
    private String curso;

    @NotBlank(message = "Nível de ensino é obrigatório")
    private String nivelEnsino;

    @NotBlank(message = "Matrícula é obrigatória")
    private String matricula;

    @NotBlank(message = "CPF é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos")
    private String cpf;

    @NotNull(message = "Data de nascimento é obrigatória")
    @Past(message = "Data de nascimento deve ser no passado")
    private LocalDate dataNascimento;

    @NotNull(message = "Validade da carteira é obrigatória")
    @Future(message = "Validade deve ser uma data futura")
    private LocalDate validadeCarteira;
}