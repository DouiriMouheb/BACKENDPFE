package Intranet.backend.Controllers;
import Intranet.backend.entites.facture;
import Intranet.backend.exception.ResourceNotFoundException;
import Intranet.backend.repositories.factureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/")
public class factureController {
    @Autowired
        private factureRepository FactureRepository;

        // get all facture
        @GetMapping("/factures")
        public List<facture> getAllFacture() {
            return FactureRepository.findAll();
        }

        // create facture rest api
        @PostMapping("/factures")
        public facture createFacture(@RequestBody facture Facture) {
            return FactureRepository.save(Facture);
        }

        // get facture by id rest api
        @GetMapping("/factures/{id}")
        public ResponseEntity<facture> getFactureById(@PathVariable Long id) {
            facture facture = FactureRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(" Facture n'existe pas :" + id));
            return ResponseEntity.ok(facture);
        }

        // update facture rest api

        @PutMapping("/factures/{id}")
        public ResponseEntity<facture> updateFacture(@PathVariable Long id, @RequestBody facture factureDetails) {
            facture facture = FactureRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("facture not exist with id :" + id));

            facture.setNumero(factureDetails.getNumero());
            facture.setMontant(factureDetails.getMontant());
            facture.setDate(factureDetails.getDate());


            facture updatedFacture = FactureRepository.save(facture);
            return ResponseEntity.ok(updatedFacture);
        }

        // delete facture rest api
        @DeleteMapping("/factures/{id}")
        public ResponseEntity<Map<String, Boolean>> deleteFacture(@PathVariable Long id) {
            facture facture = FactureRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("facture n'existe pas avec id :" + id));

            FactureRepository.delete(facture);
            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);
            return ResponseEntity.ok(response);
        }
    }


