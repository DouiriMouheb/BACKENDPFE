package Intranet.backend.Controllers;


import Intranet.backend.entites.project;
import Intranet.backend.exception.ResourceNotFoundException;
import Intranet.backend.repositories.projectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/")
public class projectController {
    @Autowired
    private projectRepository ProjectRepository;
    // get all projects
    @GetMapping("/projects")
    public List<project> getAllProjects(){
        return ProjectRepository.findAll();
    }
    // create project rest api
    @PostMapping("/projects")
    public project createProject(@RequestBody project Project) {
        return ProjectRepository.save(Project);
    }

    // get project by id rest api
    @GetMapping("/projects/{id}")
    public ResponseEntity<project> getProjectById(@PathVariable Long id) {
        project project = ProjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Personnel n'existe pas :" + id));
        return ResponseEntity.ok(project);
    }

    // update project rest api

    @PutMapping("/projects/{id}")
    public ResponseEntity<project> updateProject(@PathVariable Long id, @RequestBody project projectDetails){
        project project = ProjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("project n'exsite pas avec id :" + id));

        project.setNom_de_projet(projectDetails.getNom_de_projet());
        project.setDate_de_debut(projectDetails.getDate_de_debut());
        project.setDate_de_fin(projectDetails.getDate_de_fin());


        project updatedProject = ProjectRepository.save(project);
        return ResponseEntity.ok(updatedProject);
    }

    // delete project rest api
    @DeleteMapping("/projects/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteProject(@PathVariable Long id){
        project project = ProjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project n'exsite pas avec id:" + id));

        ProjectRepository.delete(project);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
