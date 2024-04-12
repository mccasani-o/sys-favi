package pe.com.mcc.sysfavi.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TBL_USUARIOS")
public class UsuarioEntity implements Serializable {

    private static final long serialVersionUID = 2405172041950251807L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUsuario;
    @Column(unique = true)
    private String usuario;
    private String clave;
    private String nombre;
    private String estado;
    private boolean mfaEnabled;
    @Enumerated(EnumType.STRING)
    private Role roles;


}
