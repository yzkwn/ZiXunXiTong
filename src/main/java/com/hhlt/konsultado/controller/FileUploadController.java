package com.hhlt.konsultado.controller;

import com.hhlt.konsultado.common.Result;
import com.hhlt.konsultado.entity.BusinessType;
import com.hhlt.konsultado.entity.ConsultingChannel;
import com.hhlt.konsultado.entity.Spend;
import com.hhlt.konsultado.service.BusinessTypeService;
import com.hhlt.konsultado.service.ConsultingChannelService;
import com.hhlt.konsultado.service.SpendService;
import com.hhlt.konsultado.util.DateUtil;
import com.hhlt.konsultado.util.ExcelUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static com.hhlt.konsultado.util.DownUtil.downLoad;

@Controller
@RequestMapping("/uploads")
public class FileUploadController {

    private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    private SpendService spendService;
    @Autowired
    private ConsultingChannelService consultingChannelService;
    @Autowired
    private BusinessTypeService typeService;
    @Autowired
    ExcelUtil excelUtil;

    @Value("${linuxFile}")
    private String linuxFile;
    @Value("${linuxImportFile}")
    private String linuxImportFile;

    @PostMapping("/upload1")
    @ResponseBody
    public Result upload1(@RequestParam("file") MultipartFile file) throws IOException, InvalidFormatException {
        Result result = new Result();
        if (null == file) {
            return null;
        }

        log.info("[文件类型] - [{}]", file.getContentType());
        log.info("[文件大小] - [{}]", file.getSize());
        String fname = file.getOriginalFilename().toLowerCase();
        log.info("[文件名称] - [{}]", fname);

        InputStream inputStream = file.getInputStream();

        List<String> stringTitle = excelUtil.readExcelContent(inputStream);
        stringTitle.forEach(i -> log.info("key:{}", i));
        stringTitle.forEach(i -> {
            String[] split = i.split(",");
            log.info("excel內容的長度：{}", split.length);

            try {
                Spend spend = new Spend();
                spend.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(split[0]));
                ConsultingChannel consultingChannel = consultingChannelService.selectByChannel(split[1]);
                spend.setChannelId(String.valueOf(consultingChannel.getId()));
                BusinessType businessType = typeService.get(split[2]);
                spend.setBusinessTypeId(String.valueOf(businessType.getId()));
//                spend.setAdpv(Long.getLong(Double.valueOf(split[3]).toString()));
//                spend.setClick(Long.getLong(Double.valueOf(split[4]).toString()));
                spend.setAdpv(new BigDecimal(split[3]));
                spend.setClick(new BigDecimal(split[4]));
                spend.setCharge(new BigDecimal(split[5]));
                spend.setCreateTime(DateUtil.getDateTime());
                spendService.insertSelective(spend);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        File localFile = new File(linuxImportFile, fname);
        try {
            file.transferTo(localFile);  //保存文件的方法
            result.setCode(0);
        } catch (IOException e) {
            result.setCode(1);
            log.info("上传失败！");
            e.printStackTrace();
        }
        return result;
    }


    @RequestMapping("downloadFileAction")
    public void downloadFileAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        downLoad("花费数据上传模板", linuxFile + File.separator + "花费数据上传模板.xls", response, "xls");
    }

}