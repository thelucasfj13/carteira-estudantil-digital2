package com.carteira.repository;

import com.carteira.model.Estudante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface EstudanteRepository extends JpaRepository<Estudante, Long> {
    Optional<Estudante> findByCodigoCarteira(String codigoCarteira);
    Optional<Estudante> findByCpf(String cpf);
    Optional<Estudante> findByMatricula(String matricula);
    boolean existsByCpf(String cpf);
    boolean existsByMatricula(String matricula);
    
    @Query("SELECT DISTINCT e.instituicao FROM Estudante e ORDER BY e.instituicao")
    List<String> findDistinctInstituicoes();
    
    @Query("SELECT DISTINCT e.curso FROM Estudante e ORDER BY e.curso")
    List<String> findDistinctCursos();
    
    List<Estudante> findByNomeCompletoContainingIgnoreCase(String nome);
    List<Estudante> findByInstituicaoContainingIgnoreCase(String instituicao);
    List<Estudante> findByStatus(Estudante.StatusCarteira status);
}