package pe.com.mcc.sysfavi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.mcc.sysfavi.model.entity.UsuarioEntity;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByUsuario(String username);
}
