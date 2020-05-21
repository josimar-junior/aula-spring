package com.aula.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aula.model.Cliente;
import com.aula.repository.Clientes;
import com.aula.repository.filter.ClienteFilter;

@Service
public class ClienteService {

	@Autowired
	private Clientes clientes;
	
	@Transactional
	public void salvar(Cliente cliente) {
		clientes.save(cliente);
	}
	
	public List<Cliente> listar() {
		return clientes.findAll();
	}
	
	public List<Cliente> pesquisar(ClienteFilter filter) {
		return clientes.pesquisar(filter);
	}
	
	public Cliente getPorId(Long id) {
		return clientes.findById(id).get();
	}
	
	@Transactional
	public void excluir(Long id) {
		clientes.deleteById(id);
		clientes.flush();
	}
}
