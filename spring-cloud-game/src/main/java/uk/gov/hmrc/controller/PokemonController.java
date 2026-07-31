package uk.gov.hmrc.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uk.gov.hmrc.entity.Pokemon;
import uk.gov.hmrc.service.PokemonService;

@RestController
@RequestMapping("/api/catch")

public class PokemonController {

    private final PokemonService service;

    public PokemonController(PokemonService service) {
        this.service = service;
    }

    @GetMapping
    public List<Pokemon> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Pokemon getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public Pokemon create(@RequestBody Pokemon pokemon) {
        return service.create(pokemon);
    }

    @PutMapping("/{id}")
    public Pokemon update(
            @PathVariable Integer id,
            @RequestBody Pokemon pokemon) {

        return service.update(id, pokemon);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}