package com.hhlt.konsultado.controller;

import com.hhlt.konsultado.common.Result;
import com.hhlt.konsultado.entity.DealData;
import com.hhlt.konsultado.service.DealDataService;
import com.hhlt.konsultado.service.ImportExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("deal")
public class DealController {

    @Value("${exportFile}")
    private String exportFile;
    @Value("${linuxImportFile}")
    private String linuxImportFile;

    @Autowired
    private ImportExcelService importExcelService;
    @Autowired
    private DealDataService dealDataService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String dealAdd() {
        return "deal/dealdata-add";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String dealList() {
        return "deal/dealdata-list";
    }

    @RequestMapping(value = "/uploads")
    @ResponseBody
    public String uploads(@RequestParam("file") MultipartFile sortPicImg, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fname = sortPicImg.getOriginalFilename().toLowerCase();
        String suffix = "";

        if (fname.lastIndexOf(".") > 1) {
            suffix = fname.substring(fname.lastIndexOf(".") + 1);
        }
        if ("doc".equals(suffix) || "docx".equals(suffix) || "pdf".equals(suffix) || "xls".equals(suffix) ||
                "xlsx".equals(suffix) || "ppt".equals(suffix) || "pptx".equals(suffix)) {

            String pathAll = linuxImportFile + File.separator + fname;
            File localFile = new File(linuxImportFile, fname);
            sortPicImg.transferTo(localFile);  //保存文件的方法

            //将上传文件的信息保存到数据库中
            try {
                importExcelService.importDealData(pathAll);
            } catch (IllegalArgumentException | ParseException e) {
                return "{\"code\":1,\"msg\":\"" + e.getMessage() + "\"}";

            }
            String fileUrl = "http://localhost:8080/" + fname;
            String resp = "{\"code\":0, \"msg\":\"success\", \"fileUrl\":\"" + fileUrl + "\", \"fileName\":\""
                    + sortPicImg.getOriginalFilename() + "\", \"fileSize\":" + sortPicImg.getSize() + " }";
            return resp;
        } else {
            return "{\"code\":1,\"msg\":\"文件格式不支持。\"}";
        }
    }

    @RequestMapping(value = "/listSearch")
    @ResponseBody
    @PreAuthorize("hasAuthority('dealData:query')")
    public Result listSearch(@RequestParam Map<String, Object> map){
        Result result = new Result();
        Map<String, Object> param = new HashMap<>();
        int pageNo = Integer.parseInt(map.get("pageNo").toString());
        int pageSize = Integer.parseInt(map.get("pageSize").toString());
        map.put("pageNo", (pageNo - 1) * pageSize);
        map.put("pageSize", pageSize);

        List<DealData> dataList = dealDataService.list(map);
        Integer count = dealDataService.count(map);
        param.put("list", dataList);
        param.put("total", count);
        result.setData(param);

        return result;
    }



}
