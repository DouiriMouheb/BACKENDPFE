package Intranet.backend.entites;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "personnel")
@Getter
@Setter
public class personnel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name="nom")
    private String nom;
    @Column(name="prenom")
    private String prenom;
    @Column(name="specialite")
    private  String specialite;
    @Column(name="diplome")
    private  String diplome;
    @Column(name="dateDeNaissance")
    private Date dateDeNaissance;

    @Column(name="numeroCin", unique = true)
    private  Long numeroCin;
    @Column(name="salaire")
    private  Long salaire;
    @Column(name="email")
    private  String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "personnel_id", referencedColumnName = "id")
    private List<tache> taches;

}
