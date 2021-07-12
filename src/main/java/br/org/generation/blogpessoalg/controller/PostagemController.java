package br.org.generation.blogpessoalg.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.blogpessoalg.model.Postagem;
import br.org.generation.blogpessoalg.repository.PostagemRepository;
import br.org.generation.blogpessoalg.service.PostagemService;


@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {
	
	@Autowired
	private PostagemRepository repository;
	
	@Autowired
	private PostagemService postagemService;
	
	@GetMapping // ("/postagens")
	public ResponseEntity<List<Postagem>>GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}
		// buscar por titulo
		@GetMapping("/titulo/{titulo}")
		public ResponseEntity<List<Postagem>> getByTitulo (@PathVariable String titulo){
			return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
		}
		
		@GetMapping("/{id}")
		public ResponseEntity<Postagem> getById(@PathVariable long id) {
			return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
		}
	
		//novo post
		@PostMapping ("/postar")
		public ResponseEntity<Postagem> post (@RequestBody Postagem postagem){
			return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
		}
		// put - editar postagem
		@PutMapping 
		public ResponseEntity<Postagem> putPostagem (@RequestBody Postagem postagens){
			return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagens));
		}
		// deletar postagem
		@DeleteMapping ("/{id}")
		public void deletePostagem (@PathVariable long id) {
			repository.deleteById(id);
		}

			@PutMapping("/curtir/{id}")
			public ResponseEntity<Postagem> putCurtirPostagemId (@PathVariable Long id){
	
				return ResponseEntity.status(HttpStatus.OK).body(postagemService.curtir(id));

			}

			/**
			 * 
			 * Descurtir postagem
			 * 
			 */

			@PutMapping("/descurtir/{id}")
			public ResponseEntity<Postagem> putDescurtirPostagemId (@PathVariable Long id){
	
				return ResponseEntity.status(HttpStatus.OK).body(postagemService.descurtir(id));

	}
}