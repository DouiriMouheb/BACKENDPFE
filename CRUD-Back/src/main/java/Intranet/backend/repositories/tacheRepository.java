package Intranet.backend.repositories;

import Intranet.backend.entites.tache;
import org.springframework.data.jpa.repository.JpaRepository;

public interface tacheRepository extends JpaRepository<tache,Long> {
}
