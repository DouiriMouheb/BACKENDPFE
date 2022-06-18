package Intranet.backend.Controllers;

import Intranet.backend.entites.project;
import Intranet.backend.entites.tache;
import Intranet.backend.exception.ResourceNotFoundException;
import Intranet.backend.repositories.tacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/")
public class tacheController {
    @Autowired
    private tacheRepository TacheRepository;
    // get all projects
    @GetMapping("/taches")
    public List<tache> getAllTaches(){
        return TacheRepository.findAll();
    }
    // create project rest api
    @PostMapping("/taches")
    public tache createTache(@RequestBody tache Tache) {
        return TacheRepository.save(Tache);
    }

    // get tache by id rest api
    @GetMapping("/taches/{id}")
    public ResponseEntity<tache> getTacheById(@PathVariable Long id) {
        tache tache = TacheRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Personnel n'existe pas :" + id));
        return ResponseEntity.ok(tache);
    }

    // update tache rest api

    @PutMapping("/taches/{id}")
    public ResponseEntity<tache> updatetache(@PathVariable Long id, @RequestBody tache tacheDetails){
        tache tache = TacheRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("project n'exsite pas avec id :" + id));

        tache.setTitle(tacheDetails.getTitle());
        tache.setDueDate(tacheDetails.getDueDate());
        tache.setDescription(tacheDetails.getDescription());
        tache.setType(tacheDetails.getType());


        tache updatedTache = TacheRepository.save(tache);
        return ResponseEntity.ok(updatedTache);
    }

    // delete tache rest api
    @DeleteMapping("/taches/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteTache(@PathVariable Long id){
        tache tache = TacheRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project n'exsite pas avec id:" + id));

        TacheRepository.delete(tache);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
