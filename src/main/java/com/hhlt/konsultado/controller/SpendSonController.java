package com.hhlt.konsultado.controller;

import com.hhlt.konsultado.common.Result;
import com.hhlt.konsultado.entity.SpendSon;
import com.hhlt.konsultado.service.SpendSonServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("SpendSon")
public class SpendSonController {

    @Autowired
    private SpendSonServcie spendSonServcie;

    @RequestMapping("list")
    public String list(@RequestParam Map map, Model model) {
        return "Spend/spendson_list";
    }

    @RequestMapping("listSerach")
    @ResponseBody
    public Result listSerach(@RequestParam Map map, Model model) {
        Result result = new Result();
        Object pageNum2 = map.get("pageNum");
        Integer pageNum3 = Integer.parseInt(pageNum2.toString()) ;
        Integer pageSize = Integer.parseInt(map.get("pageSize").toString());
        Integer pageNum = (pageNum3 - 1) * pageSize;
        List<SpendSon> spendSons = spendSonServcie.selectList(pageNum,pageSize);
        int count = spendSonServcie.count();
        result.setData(spendSons);
        result.setCount((long)count);
        return result;
    }
}
