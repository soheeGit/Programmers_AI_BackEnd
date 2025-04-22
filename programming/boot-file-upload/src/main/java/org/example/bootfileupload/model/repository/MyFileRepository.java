package org.example.bootfileupload.model.repository;

import org.example.bootfileupload.model.entity.MyFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyFileRepository extends JpaRepository<MyFile, String> {
}