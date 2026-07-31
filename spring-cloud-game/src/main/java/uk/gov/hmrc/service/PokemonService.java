package uk.gov.hmrc.service;

import org.springframework.stereotype.Service;
import uk.gov.hmrc.entity.Pokemon;
import uk.gov.hmrc.exception.ResourceNotFoundException;
import uk.gov.hmrc.repository.PokemonRepository;

import java.util.List;

@Service
public class PokemonService {

    private final PokemonRepository repository;

    public PokemonService(PokemonRepository repository) {
        this.repository = repository;
    }

    public List<Pokemon> findAll() {
        return repository.findAll();
    }

    public Pokemon findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Pokemon not found: " + id));
    }

    public Pokemon create(Pokemon pokemon) {
        return repository.save(pokemon);
    }

    public pokemon update(Integer id, pokemon pokemon) {

        pokemon existing = findById(id);

//        existing.setFirstName(pokemon.getFirstName());
//        existing.setLastName(pokemon.getLastName());
//        existing.setEmail(pokemon.getEmail());
//        existing.setPhoneNumber(pokemon.getPhoneNumber());
//        existing.setSalary(pokemon.getSalary());

        return repository.save(existing);
    }

    public void delete(Integer id) {
        pokemon pokemon = findById(id);
        repository.delete(pokemon);
    }
}