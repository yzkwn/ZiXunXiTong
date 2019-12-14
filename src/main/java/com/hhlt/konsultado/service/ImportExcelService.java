package com.hhlt.konsultado.service;

import java.io.IOException;
import java.text.ParseException;

public interface ImportExcelService {
    void importDealData(String path) throws IOException, ParseException;
}
