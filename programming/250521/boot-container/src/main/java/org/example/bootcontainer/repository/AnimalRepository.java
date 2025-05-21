package org.example.bootcontainer.repository;

import org.example.bootcontainer.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
