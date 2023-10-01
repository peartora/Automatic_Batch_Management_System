package com.example.batchmanagingsystem.entity;

import com.example.batchmanagingsystem.info.Info;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="batch_information")
@Getter
@Setter
public class BatchEntity
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="part_type")
    private String partType;

    @Column(name="part_name")
    private String partName; // odd case?

    @Column(name="part_number")
    private String partNumber;

    @Column(name="batch_number")
    private String batchNumber;

    @Column(name="print_times")
    private int printTimes;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="finished_at")
    private LocalDateTime finishedAt;

    @Column(name="is_latest")
    private Boolean isLatest;

    public Info toInfoEntity()
    {
        return Info.builder()
                .partName(this.partName)
                .partNumber(this.partNumber)
                .batchNumber(this.batchNumber)
                .printTimes(this.printTimes)
                .now(this.createdAt)
                .isBatchUpdated(true)
                .build();
    }

    public Info toMailInfoEntity()
    {
        return Info.builder()
                .partName(this.partName)
                .partNumber(this.partNumber)
                .batchNumber(this.batchNumber)
                .build();
    }
}
