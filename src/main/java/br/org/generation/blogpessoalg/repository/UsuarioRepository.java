package br.org.generation.blogpessoalg.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.generation.blogpessoalg.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public Optional<Usuario> findByUsuario(String usuario);
//optional - pq ele pode retornar nulo, <devolve o usuario> procura o usuario.
	
	public List <Usuario> findAllByNomeContainingIgnoreCase (String nome);
	
	public Usuario findByNome(String nome);
}