package pe.com.mcc.sysfavi;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import pe.com.mcc.sysfavi.repository.UsuarioRepository;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class Application implements CommandLineRunner {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        this.usuarioRepository.findAll().forEach(x -> log.info(x.toString()));

		/* Create PERMISSIONS
		PermissionEntity createPermission = PermissionEntity.builder()
				.name("CREATE")
				.build();

		PermissionEntity readPermission = PermissionEntity.builder()
				.name("READ")
				.build();

		PermissionEntity updatePermission = PermissionEntity.builder()
				.name("UPDATE")
				.build();

		PermissionEntity deletePermission = PermissionEntity.builder()
				.name("DELETE")
				.build();

		PermissionEntity refactorPermission = PermissionEntity.builder()
				.name("REFACTOR")
				.build();

		Create ROLES
        RoleEntity roleAdmin = RoleEntity.builder()
                .roleEnum(RoleEnum.ADMIN)
                .permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission))
                .build();

        RoleEntity roleUser = RoleEntity.builder()
                .roleEnum(RoleEnum.USER)
                .permissionList(Set.of(createPermission, readPermission))
                .build();

        RoleEntity roleInvited = RoleEntity.builder()
                .roleEnum(RoleEnum.INVITED)
                .permissionList(Set.of(readPermission))
                .build();

        RoleEntity roleDeveloper = RoleEntity.builder()
                .roleEnum(RoleEnum.DEVELOPER)
                .permissionList(Set.of(createPermission, readPermission, updatePermission, deletePermission, refactorPermission))
                .build();

        CREATE USERS
		UsuarioEntity aminMauricio = UsuarioEntity.builder()
				.usuario("Mauricio")
				.clave(passwordEncoder.encode("123"))
				.nombre("Mauricio Ccasani Olivares")
				.estado("1")
				.mfaEnabled(false)
				.roles(Set.of(roleAdmin))
				.build();

		UsuarioEntity userDoris = UsuarioEntity.builder()
				.usuario("Doris")
				.clave(passwordEncoder.encode("123"))
				.nombre("Doris Zedano")
				.estado("1")
				.mfaEnabled(false)
				.roles(Set.of(roleUser))
				.build();

		UsuarioEntity invMary = UsuarioEntity.builder()
				.usuario("Marina")
				.clave(passwordEncoder.encode("123"))
				.nombre("Marina Zedano")
				.estado("1")
				.mfaEnabled(false)
				.roles(Set.of(roleInvited))
				.build();

		UsuarioEntity devFavi = UsuarioEntity.builder()
				.usuario("Fabricio")
				.clave(passwordEncoder.encode("123"))
				.nombre("Fabricio Zedano")
				.estado("1")
				.mfaEnabled(false)
				.roles(Set.of(roleDeveloper))
				.build();

		usuarioRepository.saveAll(List.of(aminMauricio, userDoris, invMary, devFavi));
		*/
    }
}
