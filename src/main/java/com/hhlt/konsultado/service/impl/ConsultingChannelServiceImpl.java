package com.hhlt.konsultado.service.impl;

import com.hhlt.konsultado.entity.ConsultingChannel;
import com.hhlt.konsultado.mapper.ConsultingChannelMapper;
import com.hhlt.konsultado.service.ConsultingChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultingChannelServiceImpl implements ConsultingChannelService {

    @Autowired
    private ConsultingChannelMapper channelMapper;

    @Override
    public List<ConsultingChannel> list() {
        return channelMapper.list();
    }

    @Override
    public ConsultingChannel selectByChannel(String channel) {
        return channelMapper.selectByChannel(channel);
    }
}
