package com.joao.algaworks.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.joao.algaworks.api.model.Comentario;
import com.joao.algaworks.api.model.ComentarioInput;
import com.joao.algaworks.api.model.ComentarioModel;
import com.joao.algaworks.domain.exception.EntidadeNaoEncontradaException;
import com.joao.algaworks.domain.model.OrdemServico;
import com.joao.algaworks.domain.repository.OrdemServicoRepository;
import com.joao.algaworks.domain.service.GestaoOrdemServicoService;

//poderia ser dentro de OrdemServicoController, mas assim fica mais separado

@RestController
@RequestMapping("/api/ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {

	@Autowired private GestaoOrdemServicoService ordemServico;
	
	@Autowired private ModelMapper modelMapper;
	
	@Autowired private OrdemServicoRepository ordemServicoRepository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ComentarioModel adicionar(@PathVariable Long ordemServicoId, 
			@Valid @RequestBody ComentarioInput comentarioInput) {
		Comentario comentario = ordemServico.adicionarComentario(ordemServicoId, 
				comentarioInput.getDescricao());
		
		return toModel(comentario);
	
	}
	
	@GetMapping
	public List<ComentarioModel> listar(@PathVariable Long ordemServicoId) {
		OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada"));
		
		return toCollectionModel(ordemServico.getComentarios());
	}
	
	public ComentarioModel toModel(Comentario comentario) {
		return modelMapper.map(comentario, ComentarioModel.class);
	}
	
	public List<ComentarioModel> toCollectionModel(List<Comentario> comentarios) {
		return comentarios.stream()
				.map(comentario -> toModel(comentario))
				.collect(Collectors.toList());
	}

}
