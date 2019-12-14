package com.hhlt.konsultado.controller;

import com.hhlt.konsultado.service.ConsultDataService;
import com.hhlt.konsultado.service.SpendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import static com.hhlt.konsultado.util.DownUtil.downLoad;

@Controller
@RequestMapping("download")
public class DownFileController {

    @Autowired
    private ConsultDataService dataService;
    @Autowired
    private SpendService spendService;

    @RequestMapping("exportFile")
    public void exportFile(@RequestParam Map<String, Object> map, HttpServletResponse response) throws IOException {
        String filepath = dataService.exportFile(map) + ".xlsx";
        String filename = "咨询数据下载_" + System.nanoTime();
        downLoad(filename, filepath, response, "xlsx");
    }

//    @RequestMapping("exportSpendFile")
//    public void exportSpendFile(@RequestParam Map<String, Object> map, HttpServletResponse response) throws IOException {
//        String filepath = spendService.exportFile(map) + ".xlsx";
//        String filename = "花费数据下载_" + System.nanoTime();
//        downLoad(filename, filepath, response, "xlsx");
//    }
}
