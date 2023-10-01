package com.example.batchmanagingsystem.service;

import com.example.batchmanagingsystem.info.Info;
import com.example.batchmanagingsystem.repository.BatchRepository;
import com.example.batchmanagingsystem.entity.BatchEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ReturnBatchEntity
{
    final BatchRepository batchRepository;
    private ArrayList<Info> infoEntities;

    @Transactional(readOnly = true)
    public List<Info> findAllLatestRecords()
    {
        infoEntities = new ArrayList<>();

        List<BatchEntity> entities = batchRepository.findByIsLatest(true);

        for(BatchEntity entity : entities)
        {
            Info infoEntity = entity.toInfoEntity();
            infoEntities.add(infoEntity);
        }
        return infoEntities;
    }
}