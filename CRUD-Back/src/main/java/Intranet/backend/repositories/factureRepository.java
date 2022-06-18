package Intranet.backend.repositories;

import Intranet.backend.entites.facture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface factureRepository extends JpaRepository<facture,Long> {
}
