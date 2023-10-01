package com.example.batchmanagingsystem.service;

import com.example.batchmanagingsystem.info.Info;
import com.example.batchmanagingsystem.entity.BatchEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReturnAnswer
{
    public Info returnAnswer(Boolean result, BatchEntity entity)
    {
        if(result)
        {
            Info infoEntity = entity.toInfoEntity();
            return infoEntity;
        }

        else
        {
            return new Info(null, null, null, 0, null, false);
        }
    }
}