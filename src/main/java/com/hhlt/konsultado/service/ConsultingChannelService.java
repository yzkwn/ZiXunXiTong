package com.hhlt.konsultado.service;

import com.hhlt.konsultado.entity.ConsultingChannel;

import java.util.List;

public interface ConsultingChannelService {
    List<ConsultingChannel> list();

    ConsultingChannel selectByChannel(String channel);
}
