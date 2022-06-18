package Intranet.backend.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Date;

import java.util.List;


@Entity
@Table(name = "projet")
@Data
public class project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="nom_de_projet")
    private String nom_de_projet;
    @Column(name="date_de_debut")
    private Date date_de_debut;
    @Column(name="date_de_fin")
    private Date date_de_fin;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private List<tache> taches;



}
