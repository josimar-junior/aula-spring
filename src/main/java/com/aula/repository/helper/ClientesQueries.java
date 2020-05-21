package com.aula.repository.helper;

import java.util.List;

import com.aula.model.Cliente;
import com.aula.repository.filter.ClienteFilter;

public interface ClientesQueries {

	List<Cliente> pesquisar(ClienteFilter filter);
}
