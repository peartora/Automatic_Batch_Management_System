package com.example.batchmanagingsystem.info;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Info // IDEA에서 아래 4개 변수들을 final keyword를 쓸 것을 추천 하는데 왜 그런지?
{
    private final String partName;
    private final String partNumber;
    private final String batchNumber;
    private final int printTimes;
    private final LocalDateTime now;
    private final boolean isBatchUpdated;
}