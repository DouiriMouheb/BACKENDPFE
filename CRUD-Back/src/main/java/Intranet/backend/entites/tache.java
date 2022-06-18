package Intranet.backend.entites;


import lombok.Data;


import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "tache")
@Data
public class tache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="title")
    private String title;
    @Column(name="dueDate")
    private Date dueDate;
    @Column(name = "description")
    private String description;
    @Column(name="type")
    private  String type;




}
