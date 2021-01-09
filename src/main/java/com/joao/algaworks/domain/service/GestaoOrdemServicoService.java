package com.joao.algaworks.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joao.algaworks.domain.exception.NegocioException;
import com.joao.algaworks.domain.model.Cliente;
import com.joao.algaworks.domain.model.OrdemServico;
import com.joao.algaworks.domain.model.StatusOrdemServico;
import com.joao.algaworks.domain.repository.ClienteRepository;
import com.joao.algaworks.domain.repository.OrdemServicoRepository;

@Service
public class GestaoOrdemServicoService {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired ClienteRepository clienteRepository;
	
	public OrdemServico criar(OrdemServico ordemServico) {
		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Cliente não encontrado"));
		
		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());
		return ordemServicoRepository.save(ordemServico);
	}
}
