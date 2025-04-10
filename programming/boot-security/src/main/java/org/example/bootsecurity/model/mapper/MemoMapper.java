package org.example.bootsecurity.model.mapper;

import org.apache.ibatis.annotations.*;
import org.example.bootsecurity.model.domain.Memo;
import org.springframework.stereotype.Repository;

import java.util.List;

// Spring
@Repository
// MyBatis
@Mapper
public interface MemoMapper {
    @Select("SELECT * FROM memo")
    List<Memo> findAll();

    @Insert("INSERT INTO memo(text) VALUES (#{text})")
    //@Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Memo memo);

    @Delete("DELETE FROM memo")
    void deleteAll();

    @Delete("DELETE FROM memo WHERE id = #{id}")
    void deleteById(Long id);

    @Select("SELECT * FROM memo WHERE id = #{id}")
    Memo findById(Long id);

    @Update("UPDATE memo SET text = (#{text}) WHERE id = (#{id})")
    void update(Memo memo);
}
