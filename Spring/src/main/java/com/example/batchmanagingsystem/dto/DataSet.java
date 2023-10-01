package com.example.batchmanagingsystem.dto;

import com.example.batchmanagingsystem.entity.BatchEntity;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Setter
@Component
public class DataSet
{
    private String material;
    private String batchNumber;

    public BatchEntity toDataSetEntity(PartName partName)
    {
        return BatchEntity.builder()
                .partType(partName.partType)
                .partName(partName.partName)
                .partNumber(partName.partNumber)
                .partNumber(this.material)
                .batchNumber(this.batchNumber)
                .printTimes(partName.printTimes)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public String getMaterial() {
        return this.material;
    }

    public String getBatchNumber() {
        return this.batchNumber;
    }
}
