package br.gov.rj.faeterj.estoque.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.gov.rj.faeterj.estoque.model.Produto;

@Controller
public class ProdutosController {
	             // (1) Método que devolve o nome da view
	             // e para o DispatcherServlet encontrar o controller
	             // ele deverá estar mapeado
	private static final Logger logger = LoggerFactory.getLogger(ProdutosController.class);
	
	@RequestMapping("/produtos/novo") // Configurando a URL
	public String novo(Produto produto) {
		
		logger.error("Aqui é log nível erro!");
		logger.info("Aqui é um log nível info");
		
		return "produto/CadastroProduto"; // Retorno do nome da view
	}
	// A partir deste controller será possível renderizar o HTML
	
	// Método POST
	@RequestMapping(value = "/produtos/novo", method = RequestMethod.POST)
	public String cadastrar(@Valid Produto produto, BindingResult result, 
			Model model, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return novo(produto);
		}
		//Salvar no banco de dados
		System.out.println(">>> sku: " + produto.getSku());
		System.out.println(">>> nome: " + produto.getNome());
		System.out.println(">>> descricao: " + produto.getDescricao());
		attributes.addFlashAttribute("mensagem", "Formulário enviado com sucesso!");
		return "redirect:/produtos/novo";
	}
	
}