package org.example.bootcontainerreview.repository;

import org.example.bootcontainerreview.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
