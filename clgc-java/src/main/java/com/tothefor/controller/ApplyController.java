package com.tothefor.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tothefor.mapper.ApplyMapper;
import com.tothefor.mapper.InformationMapper;
import com.tothefor.pojo.entity.Apply;
import com.tothefor.pojo.entity.Information;
import com.tothefor.resultR.R;
import com.tothefor.service.impl.ApplyServiceImpl;
import com.tothefor.service.impl.InformationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author DragonOne
 * @Date 2022/3/9 15:34
 * @墨水记忆 www.tothefor.com
 */

@RestController
@RequestMapping("/itemType")
public class ApplyController {

    @Autowired
    private ApplyMapper applyMapper;

    @Autowired
    private InformationMapper informationMapper;

    @Autowired
    private InformationServiceImpl informationService;

    @Autowired
    private ApplyServiceImpl applyService;


    /**
     * @Author DragonOne
     * @Date 2022/5/1
     * @墨水记忆 www.tothefor.com
     * @方法名称 getDescription
     * @方法描述 获取指定报名表编号获取备注信息
     * @参数说明
     * @返回值
     */
    @GetMapping("/getDescription/{type}")
    public R getDescription(@PathVariable String type) {

        QueryWrapper<Apply> qw = new QueryWrapper<>();
        qw.eq("type_id",type);
        Apply apply = applyMapper.selectOne(qw);
        return R.SUCCESS(apply.getDescription());
    }


    /**
     * @Author DragonOne
     * @Date 2022/4/29
     * @墨水记忆 www.tothefor.com
     * @方法名称 closeAllItem
     * @方法描述 设置所有状态为false
     * @参数说明
     * @返回值
     */
    @GetMapping("/closeAllItem")
    public R closeAllItem(){

        List<Apply> itemTypes = applyMapper.selectList(null);
        for(Apply it : itemTypes){
            it.setIsShow(false);
            applyMapper.updateById(it);
        }
        return R.SUCCESS();
    }


    /**
     * @Author DragonOne
     * @Date 2022/4/28
     * @墨水记忆 www.tothefor.com
     * @方法名称
     * @方法描述
     * @参数说明
     * @返回值
     */
    @GetMapping("/getInfoOneMsg")
    public R getTypeOneInfo() {
        QueryWrapper<Apply> qw = new QueryWrapper();
        qw.ne("is_show", 0);
        qw.orderByDesc("id");
        Apply itemType = applyMapper.selectOne(qw);
        if(itemType==null) return R.FAIL();
        return R.SUCCESS(itemType);
    }

    /**
     * @return
     * @Author DragonOne
     * @Date 2022/3/9 15:44
     * @墨水记忆 www.tothefor.com
     * @方法 itemTypeAll
     * @作用 查询全部类型
     * @参数说明
     */
    @GetMapping("/itemTypeAll")
    public R<List<Apply>> itemTypeAll() {
        return R.SUCCESS(applyMapper.selectList(null));
    }

    /**
     * @return
     * @Author DragonOne
     * @Date 2022/3/6 17:02
     * @墨水记忆 www.tothefor.com
     * @方法 itemSave
     * @作用 插入和修改接口
     * @参数说明
     */
    @PostMapping("/save")
    public R itemTypeSave(@RequestBody Apply item) {
        Long newTypeId = item.getTypeId();
        Long oldTypeID = null;
        Long id = item.getId();
        if (id != null) {
            QueryWrapper<Information> qw = new QueryWrapper<>();

            Apply itemType = applyMapper.selectById(id);
            oldTypeID = itemType.getTypeId();

            qw.eq("typeID", oldTypeID);
            List<Information> items = informationMapper.selectList(qw);

            for (Information it : items) {
                it.setTypeid(newTypeId);
                informationService.updateById(it);
            }

        }


        if (applyService.saveOrUpdate(item)) {

            return R.SUCCESS();
        } else {
            return R.FAIL();
        }
    }

    /**
     * @return
     * @Author DragonOne
     * @Date 2022/3/6 17:06
     * @墨水记忆 www.tothefor.com
     * @方法 itemDelete
     * @作用 根据id删除信息
     * @参数说明
     */
    @DeleteMapping("/del/{id}")
    public R itemTypeDelete(@PathVariable Integer id) {
        if (applyMapper.deleteById(id) == 1) {
            return R.SUCCESS();
        } else {
            return R.FAIL();
        }
    }

    /**
     * @return
     * @Author DragonOne
     * @Date 2022/3/6 17:11
     * @墨水记忆 www.tothefor.com
     * @方法 itemBatchDelete
     * @作用 批量删除
     * @参数说明
     */
    @PostMapping("/batch/del")
    public R itemTypeBatchDelete(@RequestBody List<Integer> ids) {
        int len = ids.size();
        if (applyMapper.deleteBatchIds(ids) == len) {
            return R.SUCCESS();
        } else {
            return R.FAIL();
        }
    }

    /**
     * @return
     * @Author DragonOne
     * @Date 2022/3/7 12:37
     * @墨水记忆 www.tothefor.com
     * @方法 PageitemAll
     * @作用 物品的分页查询
     * @参数说明
     */
    @GetMapping("/pageAll")
    public R<IPage<Apply>> PageItemTypeAll(@RequestParam("pageNum") Integer pageNum,
                                              @RequestParam("PageSize") Integer pageSize,
                                              @RequestParam(defaultValue = "") String itemname) {

        IPage<Apply> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Apply> queryWrapper = new QueryWrapper<>();
        if (StringUtils.hasText(itemname)) {
            queryWrapper.like("typename", itemname);
        }
//        queryWrapper.ne("id",1);
        queryWrapper.orderByDesc("id");
        return R.SUCCESS(applyService.page(page, queryWrapper));
    }

    /**
     * @return
     * @Author DragonOne
     * @Date 2022/3/9 16:28
     * @墨水记忆 www.tothefor.com
     * @方法 itemShow
     * @作用 修改物品状态
     * @参数说明
     */
    @PostMapping("/changeShow")
    public R itemShow(@RequestBody Apply id) {
        if (applyMapper.updateById(id) == 1) {
            return R.SUCCESS();
        } else {
            return R.FAIL();
        }
    }

    /**
     * @return
     * @Author DragonOne
     * @Date 2022/3/9 17:13
     * @墨水记忆 www.tothefor.com
     * @方法 export
     * @作用 数据导出
     * @参数说明
     */
    @GetMapping("/export")
    public void exportType(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        String token = request.getHeader("token");

        List<Map<String, Object>> list = CollUtil.newArrayList();
        List<Apply> all = applyMapper.selectList(null);

        for (Apply it : all) {
            Map<String, Object> row1 = new LinkedHashMap<>();
            row1.put("类型唯一标识符", it.getId());
            row1.put("类型编号", it.getTypeId());
            row1.put("类型名称", it.getTypename());
            row1.put("类型描述", it.getDescription());

            list.add(row1);
        }

        // 2. 写excel
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("下载信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(System.out);


    }



}
