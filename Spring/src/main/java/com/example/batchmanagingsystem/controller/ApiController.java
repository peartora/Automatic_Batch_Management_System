package com.example.batchmanagingsystem.controller;

import com.example.batchmanagingsystem.info.Info;
import com.example.batchmanagingsystem.repository.BatchRepository;
import com.example.batchmanagingsystem.dto.DataSet;
import com.example.batchmanagingsystem.dto.PartName;
import com.example.batchmanagingsystem.entity.BatchEntity;
import com.example.batchmanagingsystem.service.CheckLatestBatchNumber;
import com.example.batchmanagingsystem.service.ReturnAnswer;
import com.example.batchmanagingsystem.service.ReturnBatchEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/batchInformation")
public class ApiController {
    final CheckLatestBatchNumber checkLatestBatchNumber;
    final ReturnAnswer returnAnswer;
    final ReturnBatchEntity returnBatchEntity;
    final BatchRepository batchRepository;

    @PostMapping("/handling-service")
    public Info batchNumberHandling(@RequestBody DataSet dataSet) // body에 담아 보냈기 때문에,
    {
        PartName partName = PartName.checkPartName(dataSet.getMaterial());
        BatchEntity batchEntity = dataSet.toDataSetEntity(partName);
        Boolean result = checkLatestBatchNumber.isBatchNumberChanged(batchEntity);
        return returnAnswer.returnAnswer(result, batchEntity);
    }

    @GetMapping("/display-service")
    public List<Info> returnBatchEntity() {
        return returnBatchEntity.findAllLatestRecords(); // Entity는 relation의 문제로 return이 되면 성능에 영향을 준다.
    }

    @GetMapping("/update-options")
    public List<Info> returnBathEntityForOptionsOfSelectTag() {
        return returnBatchEntity.findAllLatestRecords();
    }
}