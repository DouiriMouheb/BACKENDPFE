package Intranet.backend.repositories;


import Intranet.backend.entites.project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public interface projectRepository extends JpaRepository<project,Long> {
}
