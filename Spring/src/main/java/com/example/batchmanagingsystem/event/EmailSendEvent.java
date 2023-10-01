package com.example.batchmanagingsystem.event;

import com.example.batchmanagingsystem.info.Info;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class EmailSendEvent
{
    private final String to;
    private final String title;
    private final List<Info> infos;
}
