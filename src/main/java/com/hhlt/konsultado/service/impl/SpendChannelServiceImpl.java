package com.hhlt.konsultado.service.impl;

import com.hhlt.konsultado.entity.SpendChannel;
import com.hhlt.konsultado.mapper.SpendChannelMapper;
import com.hhlt.konsultado.service.SpendChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpendChannelServiceImpl implements SpendChannelService {

    @Autowired
    private SpendChannelMapper mapper;


    @Override
    public SpendChannel selectByChannel(String channel) {
        return mapper.selectByChannel(channel);
    }
}
