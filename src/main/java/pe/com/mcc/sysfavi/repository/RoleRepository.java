package pe.com.mcc.sysfavi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pe.com.mcc.sysfavi.model.entity.RoleEntity;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

    List<RoleEntity> findRoleEntitiesByRolTipoIn(List<String> roleNames);
}
