package com.wardrobe.controller;

import com.wardrobe.common.bean.ResponseBean;
import com.wardrobe.common.po.CommodityBanner;
import com.wardrobe.common.po.CommodityInfo;
import com.wardrobe.common.po.CommoditySize;
import com.wardrobe.common.util.FileUtil;
import com.wardrobe.controller.annotation.CommodityResolver;
import com.wardrobe.platform.service.ICommodityService;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cxs on 2018/7/30.
 */
@RequestMapping("commodity")
@Controller
public class CommodityController extends BaseController {

    @Autowired
    private ICommodityService commodityService;

    @ResponseBody
    @RequestMapping("addUpdateCommodity")
    public ResponseBean addUpdateCommodity(@CommodityResolver CommodityInfo commodityInfo, MultipartHttpServletRequest request) throws Exception {
       commodityService.addUpdateCommodityIn(commodityInfo, null, request);
        return new ResponseBean(true);
    }

    @ResponseBody
    @RequestMapping("updateStatus")
    public ResponseBean updateStatus(int cid, String status){
        commodityService.updateCommodityStatusIn(cid, status);
        return new ResponseBean(true);
    }

    @ResponseBody
    @RequestMapping("updateHot")
    public ResponseBean updateHot(int cid, String hot){
        commodityService.updateCommodityHotIn(cid, hot);
        return new ResponseBean(true);
    }

    @ResponseBody
    @RequestMapping("updateNewly")
    public ResponseBean updateNewly(int cid, String newly){
        commodityService.updateCommodityNewlyIn(cid, newly);
        return new ResponseBean(true);
    }

    @ResponseBody
    @RequestMapping("addSize")
    public ResponseBean addSize(CommoditySize commoditySize){
        commodityService.addSizeIn(commoditySize);
        return new ResponseBean(true);
    }

    @ResponseBody
    @RequestMapping("delSize")
    public ResponseBean deleteSize(int sid){
        commodityService.deleteSizeIn(sid);
        return new ResponseBean(true);
    }

    @ResponseBody
    @RequestMapping("saveCommodityBanner")
    public ResponseBean saveCommodityBanner(CommodityBanner commodityBanner, MultipartHttpServletRequest multipartRequest)throws IOException{
        commodityService.saveCommodityBanner(commodityBanner, multipartRequest);
        return new ResponseBean(true);
    }

    @ResponseBody
    @RequestMapping("delCommodityBanner")
    public ResponseBean delCommodityBanner(int cbid){
        commodityService.deleteCommodityBanner(cbid);
        return new ResponseBean(true);
    }

    @ResponseBody
    @RequestMapping("getCommoditySizes")
    public ResponseBean getCommoditySizes(int cid){
        List<CommoditySize> commoditySizeList = commodityService.getCommoditySizeList(cid);
        Map data = new HashMap<>();
        data.put("sizes", commoditySizeList);
        return new ResponseBean(data);
    }

    @ResponseBody
    @RequestMapping("excelCommoditys")
    public ResponseBean excelCommoditys(MultipartHttpServletRequest multipartRequest) throws IOException{
        commodityService.saveExcelCommoditys(multipartRequest);
        return new ResponseBean(true);
    }

    @RequestMapping("downCommodityTpl")
    public void downCommodityTpl(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setHeader("Content-Disposition", "attachment;" + FileUtil.getEncodingFileName("商品导入模板.xlsx", request.getHeader("User-Agent")));
        ServletOutputStream outputStream = response.getOutputStream();
        InputStream inputStream = CommodityController.class.getResourceAsStream("/tpl/commodity_tpl.xlsx");
        outputStream.write(IOUtils.toByteArray(inputStream));

        outputStream.close();
    }

}
