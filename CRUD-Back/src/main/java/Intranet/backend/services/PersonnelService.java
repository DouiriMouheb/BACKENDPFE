package Intranet.backend.services;


import Intranet.backend.entites.personnel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonnelService {
    private Intranet.backend.repositories.personnelRepository personnelRepository;

    public List<personnel> getpersonneltotal() {
        return personnelRepository.getAllPersonnel();

    }
}
