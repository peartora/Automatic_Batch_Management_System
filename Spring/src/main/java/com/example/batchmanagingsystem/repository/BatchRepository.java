package com.example.batchmanagingsystem.repository;

import com.example.batchmanagingsystem.entity.BatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BatchRepository extends JpaRepository<BatchEntity, Long>
{
    BatchEntity findFirstByPartNameAndBatchNumberOrderByCreatedAtDesc(String partName, String batchNumber);
    BatchEntity findByPartNameAndIsLatest(String partName, Boolean bool);
    List<BatchEntity> findByIsLatest(Boolean bool);
}