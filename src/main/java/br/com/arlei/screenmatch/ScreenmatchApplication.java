package br.com.arlei.screenmatch;

import br.com.arlei.screenmatch.principal.Principal;
import br.com.arlei.screenmatch.principal.PrincipalProduto;
import br.com.arlei.screenmatch.repository.produto.CategoriaProdutoRepository;
import br.com.arlei.screenmatch.repository.SerieRepository;
import br.com.arlei.screenmatch.repository.produto.FornecedorRepository;
import br.com.arlei.screenmatch.repository.produto.PedidoRepository;
import br.com.arlei.screenmatch.repository.produto.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	@Autowired
	private SerieRepository repositorio;

//	@Autowired
//	private CategoriaProdutoRepository categoriaProdutoRepository;
//
//	@Autowired
//	private FornecedorRepository fornecedorRepository;
//
//	@Autowired
//	private PedidoRepository pedidoRepository;
//
//	@Autowired
//	private ProdutoRepository produtoRepository;

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositorio);
		principal.exibeMenu();

//		PrincipalProduto principalProduto = new PrincipalProduto(categoriaProdutoRepository
//		,fornecedorRepository,pedidoRepository,produtoRepository);
//		principalProduto.principal();
	}
}
