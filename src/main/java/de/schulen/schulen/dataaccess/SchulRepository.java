package de.schulen.schulen.dataaccess;

import de.schulen.schulen.model.Schulen;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SchulRepository extends CrudRepository<Schulen, Long> {
    List<Schulen> findAll();
}
