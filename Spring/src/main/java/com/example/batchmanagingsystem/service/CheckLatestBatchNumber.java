package com.example.batchmanagingsystem.service;

import com.example.batchmanagingsystem.event.EmailSendEvent;
import com.example.batchmanagingsystem.info.Info;
import com.example.batchmanagingsystem.repository.BatchRepository;
import com.example.batchmanagingsystem.entity.BatchEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.ApplicationEventPublisher;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CheckLatestBatchNumber
{
    final BatchRepository batchRepository;
    final ReturnBatchEntity returnBatchEntity;
    final ApplicationEventPublisher eventPublisher;

    @Transactional
    public Boolean isBatchNumberChanged(BatchEntity batchEntity)
    {
        BatchEntity entityRecord = batchRepository.findFirstByPartNameAndBatchNumberOrderByCreatedAtDesc(batchEntity.getPartName(), batchEntity.getBatchNumber());

        if(entityRecord == null)
        {
            followingWork(batchEntity);

            mailEventPublisher();

            return true;
        }
        else
        {
            if(entityRecord.getIsLatest())
            {
                return false;
            }
            else
            {
                //batch 관련 DB function.
                followingWork(batchEntity);

                System.out.println("following work 다음");

                //mailing 관련 function
                mailEventPublisher();

                return true;
            }
        }
    }

    private void followingWork(BatchEntity batchEntity)
    {
        batchEntity.setIsLatest(true); // 입력 받은 배치가 최신 정보 일 때,
        BatchEntity latestEntityRecord = batchRepository.findByPartNameAndIsLatest(batchEntity.getPartName(), true); // 현재 DB record 중 최신 정보
        latestEntityRecord.setFinishedAt(LocalDateTime.now());
        latestEntityRecord.setIsLatest(false);
        batchRepository.save(latestEntityRecord);
        batchRepository.save(batchEntity);
    }

    private void mailEventPublisher()
    {
        final List<Info> infos = this.batchRepository.findByIsLatest(true)
                .stream().map(BatchEntity::toMailInfoEntity).collect(Collectors.toList());
        final EmailSendEvent event = new EmailSendEvent("peartora@gmail.com", "Batch Information", infos);
        this.eventPublisher.publishEvent(event);
    }
}
