package br.com.zupacademy.osmarjunior.mercadolivre.repository;

import br.com.zupacademy.osmarjunior.mercadolivre.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByLogin(String login);
}
