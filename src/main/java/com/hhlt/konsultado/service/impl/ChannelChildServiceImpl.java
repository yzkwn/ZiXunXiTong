package com.hhlt.konsultado.service.impl;

import com.hhlt.konsultado.entity.ChannelChild;
import com.hhlt.konsultado.mapper.ChannelChildMapper;
import com.hhlt.konsultado.service.ChannelChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelChildServiceImpl implements ChannelChildService {

    @Autowired
    private ChannelChildMapper childMapper;

    @Override
    public List<ChannelChild> list() {
        return childMapper.list();
    }
}
