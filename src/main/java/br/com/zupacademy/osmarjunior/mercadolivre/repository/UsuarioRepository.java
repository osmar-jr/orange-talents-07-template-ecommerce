package br.com.zupacademy.osmarjunior.mercadolivre.repository;

import br.com.zupacademy.osmarjunior.mercadolivre.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
