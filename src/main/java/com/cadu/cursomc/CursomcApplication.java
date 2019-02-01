package com.cadu.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cadu.cursomc.domain.Categoria;
import com.cadu.cursomc.domain.Cidade;
import com.cadu.cursomc.domain.Cliente;
import com.cadu.cursomc.domain.Endereco;
import com.cadu.cursomc.domain.Estado;
import com.cadu.cursomc.domain.Produto;
import com.cadu.cursomc.domain.enums.TipoCliente;
import com.cadu.cursomc.repositories.CategoriaRepository;
import com.cadu.cursomc.repositories.CidadeRepository;
import com.cadu.cursomc.repositories.ClienteRepository;
import com.cadu.cursomc.repositories.EnderecoRepository;
import com.cadu.cursomc.repositories.EstadoRepository;
import com.cadu.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2001.00);
		Produto p2 = new Produto(null, "Impressora", 850.00);
		Produto p3 = new Produto(null, "Mouse", 70.00);
		
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null,"São Paulo");
		
		Cidade cid1 = new Cidade(null, "Belo Horizonte", est1);
		Cidade cid2 = new Cidade(null,"Campinas", est2);
		Cidade cid3 = new Cidade(null,"Uberlândia", est1);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().add(p2);
		
		p1.getCategoria().addAll(Arrays.asList(cat1,cat2));
		p2.getCategoria().add(cat1);
		p3.getCategoria().add(cat2);
		
		est1.getCidades().addAll(Arrays.asList(cid1,cid3));
		est2.getCidades().addAll(Arrays.asList(cid2));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(cid1,cid2,cid3));
		
		Cliente cli1 = new Cliente(null,"Carlos Salgado", "carlos@gmail.com", "04010910666", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("32222222","995874541"));
		
		Endereco end1 = new Endereco(null,"Rua da Pedra","100", "Patricia", "AP100", "30300000", cid3, cli1);
		Endereco end2 = new Endereco(null,"Rua da Maria","500", "Jose", "AP10", "30300001", cid2, cli1);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1,end2));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1,end2));
		
		
		
		
	}

}

