package Intranet.backend.repositories;

import Intranet.backend.entites.Task;
import Intranet.backend.entites.personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("http://localhost:4200")
public interface personnelRepository extends JpaRepository<personnel,Long> {
    @Query(value="Select Count * from personnel",nativeQuery = true)

    public List<personnel> getAllPersonnel();
     boolean existsBynumeroCin(Long numeroCin) ;
}
