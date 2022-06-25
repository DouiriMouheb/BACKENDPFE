package Intranet.backend.Controllers;


import Intranet.backend.dto.message;
import Intranet.backend.entites.personnel;
import Intranet.backend.exception.ResourceNotFoundException;
import Intranet.backend.repositories.personnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/")
public class personnelController {
    @Autowired
    private personnelRepository PersonnelRepository;
    // get all personnel
    @GetMapping("/personnels")
    public List<personnel> getAllPersonnel(){
        return PersonnelRepository.findAll();
    }
    // create personnel rest api
    @PostMapping("/personnels")
  /*  public personnel createpersonnel(@RequestBody personnel Personnel) {
        return PersonnelRepository.save(Personnel);
    }*/
    public message cretepersonnel(@RequestBody personnel Personnel )
    {
        if (PersonnelRepository.existsBynumeroCin(Personnel.getNumeroCin())){
            return new message("This ID Number Exisits") ;
        }

        PersonnelRepository.save(Personnel);
        return new message("New Employee added successfully !");
    }

    // get personnel by id rest api
    @GetMapping("/personnels/{id}")
    public ResponseEntity<personnel> getPersonnelById(@PathVariable Long id) {
        personnel personnel = PersonnelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Personnel n'existe pas :" + id));
        return ResponseEntity.ok(personnel);
    }

    // update personnel rest api

    @PutMapping("/personnels/{id}")
    public ResponseEntity<personnel> updatePersonnel(@PathVariable Long id, @RequestBody personnel personnelDetails){
        personnel personnel = PersonnelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));

        personnel.setNom(personnelDetails.getNom());
        personnel.setPrenom(personnelDetails.getPrenom());
        personnel.setSpecialite(personnelDetails.getSpecialite());
        personnel.setDiplome(personnelDetails.getDiplome());
        personnel.setDateDeNaissance(personnelDetails.getDateDeNaissance());
        personnel.setNumeroCin(personnelDetails.getNumeroCin());
        personnel.setSalaire(personnelDetails.getSalaire());
        personnel.setEmail(personnelDetails.getEmail());

        personnel updatedPersonnel = PersonnelRepository.save(personnel);
        return ResponseEntity.ok(updatedPersonnel);
    }

    // delete personnel rest api
    @DeleteMapping("/personnels/{id}")
    public ResponseEntity<Map<String, Boolean>> deletepersonnel(@PathVariable Long id){
        personnel personnel = PersonnelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));

        PersonnelRepository.delete(personnel);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
