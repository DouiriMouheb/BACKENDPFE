package Intranet.backend.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(name="title")
    private String title;
    @Column(name="type")
    private String type;
    @Column(name="dueDate")
    private Date dueDate;
    @Column(name="description")
    private String description;
}