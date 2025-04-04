package psichiatria.be.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import psichiatria.be.model.enums.Roles;


import java.io.Serializable;
import java.util.Date;


@Data
@Entity
@Table(name = "User")
public class User extends DynamicDataEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER")
    @SequenceGenerator(name = "SEQ_USER", sequenceName = "SEQ_USER", initialValue = 1, allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CF")
    private String cf;

    @Column(name = "NAME")
    private String nome;

    @Column(name = "SURNAME")
    private String cognome;

    @Enumerated(EnumType.STRING)
    @Column(name = "CODICE", unique = true, nullable = false)
    private Roles role;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "DT_FINE")
    private Date dtFine;

}
