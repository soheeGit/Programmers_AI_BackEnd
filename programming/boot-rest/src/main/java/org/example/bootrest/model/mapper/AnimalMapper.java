package org.example.bootrest.model.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.bootrest.model.domain.Animal;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AnimalMapper {
    @Select("SELECT * FROM animal")
    List<Animal> findAll();

    @Insert("INSERT INTO animal (name, description, story) VALUES (#{name}, #{description}, #{story})")
    void insert(Animal animal);
}
