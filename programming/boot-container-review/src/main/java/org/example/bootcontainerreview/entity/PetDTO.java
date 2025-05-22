package org.example.bootcontainerreview.entity;

public record PetDTO(String name) {
    public Pet toEntity() {
        Pet pet = new Pet();
        pet.setName(name);
        return pet;
    }
}
