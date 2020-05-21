package com.aula.repository.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.util.StringUtils;

import com.aula.model.Cliente;
import com.aula.repository.filter.ClienteFilter;

public class ClientesImpl implements ClientesQueries {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Cliente> pesquisar(ClienteFilter filter) {
		StringBuilder jpql = new StringBuilder("select c from Cliente c where 1=1 ");
		Map<String, Object> parametros = new HashMap<>();
		
		adicionarWhere(filter, jpql, parametros);
		
		TypedQuery<Cliente> query = manager.createQuery(jpql.toString(), Cliente.class);
		
		adicionarParametros(query, parametros);
		
		return query.getResultList();
	}

	private void adicionarParametros(TypedQuery<Cliente> query, Map<String, Object> parametros) {
		parametros.forEach((k, v) -> query.setParameter(k, v));
	}

	private void adicionarWhere(ClienteFilter filter, StringBuilder jpql, Map<String, Object> parametros) {
		if(!StringUtils.isEmpty(filter.getNome())) {
			jpql.append("and lower(c.nome) like :nome");
			parametros.put("nome", "%" + filter.getNome() + "%");
		}
		
		if(filter.getSexo() != null) {
			jpql.append("and c.sexo = :sexo");
			parametros.put("sexo", filter.getSexo());
		}
	}

}
