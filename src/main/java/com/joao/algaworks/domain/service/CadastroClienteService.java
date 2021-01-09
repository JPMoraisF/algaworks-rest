package com.joao.algaworks.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joao.algaworks.domain.exception.NegocioException;
import com.joao.algaworks.domain.model.Cliente;
import com.joao.algaworks.domain.model.OrdemServico;
import com.joao.algaworks.domain.repository.ClienteRepository;
import com.joao.algaworks.domain.repository.OrdemServicoRepository;

//alo lalo
@Service
public class CadastroClienteService {

	@Autowired OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
		
	public Cliente salvar(Cliente cliente) {
		Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());
		
		if(clienteExistente != null && !clienteExistente.equals(cliente)) {
			throw new NegocioException("Já existe um cliente cadastrado com este e-mail");
		}
		return clienteRepository.save(cliente);
	}
	
	public void excluir(Long clienteId) { 
		List<OrdemServico> ordemAberta = ordemServicoRepository.findByCliente_Id(clienteId);

		//TODO APENAS EXCLUIR CLIENTE QUE POSSUI TODAS AS ORDENS DE SERVICO FINALIZADAS
		
		if(!ordemAberta.isEmpty()) {
			throw new NegocioException("Este cliente possui ordem de serviço em aberto!");
		}
		clienteRepository.deleteById(clienteId);
	}
	
}
