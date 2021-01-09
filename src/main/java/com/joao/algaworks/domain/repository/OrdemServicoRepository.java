package com.joao.algaworks.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joao.algaworks.domain.model.OrdemServico;


@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
	List<OrdemServico> findByCliente_Id(Long clienteId);
}
