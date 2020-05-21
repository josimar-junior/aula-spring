package com.aula.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aula.model.Cliente;
import com.aula.model.Sexo;
import com.aula.repository.filter.ClienteFilter;
import com.aula.service.ClienteService;

@Controller
@RequestMapping("/clientes")
public class ClientesController {
	
	@Autowired
	private ClienteService clienteService;

	@GetMapping("/novo")
	public ModelAndView novo(Cliente cliente) {
		ModelAndView mv = new ModelAndView("cliente/cadastrar-cliente");
		mv.addObject("sexos", Sexo.values());
		return mv;
	}
	
	@PostMapping( { "novo", "{\\d+}" })
	public ModelAndView salvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return novo(cliente);
		}
		
		boolean isEdicao = cliente.isEdicao();
		
		clienteService.salvar(cliente);

		if(isEdicao) {
			attributes.addFlashAttribute("mensagem", "Cliente atulizado com sucesso");
			return new ModelAndView("redirect:/clientes");
		} else {
			attributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso");
			return new ModelAndView("redirect:/clientes/novo");
		}
		
	}
	
	@GetMapping
	public ModelAndView pesquisar(ClienteFilter clienteFilter) {
		ModelAndView mv = new ModelAndView("cliente/pesquisar-cliente");
		mv.addObject("sexos", Sexo.values());
		mv.addObject("clientes", clienteService.pesquisar(clienteFilter));
		return mv;
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable Long id) {
		try {
			clienteService.excluir(id);
			return ResponseEntity.ok().build();
		} catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/{id}")
	public ModelAndView prepararEdicao(@PathVariable Long id) {
		Cliente cliente = clienteService.getPorId(id);
		ModelAndView mv = novo(cliente);
		mv.addObject(cliente);
		return mv;
	}
}
