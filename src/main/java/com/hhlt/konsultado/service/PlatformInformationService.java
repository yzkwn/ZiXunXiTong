package com.hhlt.konsultado.service;

import com.hhlt.konsultado.entity.PlatformInformation;

import java.util.List;

public interface PlatformInformationService {

    List<PlatformInformation> list();

    List<PlatformInformation> listNew();
}
