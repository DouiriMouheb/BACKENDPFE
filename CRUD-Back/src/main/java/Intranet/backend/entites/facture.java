package Intranet.backend.entites;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "facture")
@Getter
@Setter
public class facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="numero")
    private String numero;
    @Column(name="date")
    private Date date;
    @Column(name="montant")
    private String montant;
}
