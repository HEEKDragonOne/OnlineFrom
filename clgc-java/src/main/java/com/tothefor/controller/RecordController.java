package com.tothefor.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tothefor.mapper.BlackListMapper;
import com.tothefor.pojo.entity.BlackList;
import com.tothefor.resultR.R;
import com.tothefor.service.impl.BlackListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 物品controller
 *
 * @Author DragonOne
 * @Date 2022/3/4 13:28
 * @墨水记忆 www.tothefor.com
 */

@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private BlackListMapper blackListMapper;

    @Autowired
    private BlackListServiceImpl blackListService;


    /**
     * @return
     * @Author DragonOne
     * @Date 2022/3/6 17:01
     * @墨水记忆 www.tothefor.com
     * @方法 itemAll
     * @作用 查找所有物品信息接口
     * @参数说明
     */
    @GetMapping("/recordAll")
    public R<List<BlackList>> itemAll() {

        return R.SUCCESS(blackListMapper.selectList(null));
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
    public R itemSave(@RequestBody BlackList item) {

        if (blackListService.saveOrUpdate(item)) {
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
    public R itemDelete(@PathVariable Integer id) {
        if (blackListMapper.deleteById(id) == 1) {
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
    public R itemBatchDelete(@RequestBody List<Integer> ids) {
        int len = ids.size();
        if (blackListMapper.deleteBatchIds(ids) == len) {
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
    public R<IPage<BlackList>> PageItemAll(@RequestParam("pageNum") Integer pageNum,
                                        @RequestParam("PageSize") Integer pageSize,
                                        @RequestParam(defaultValue = "") String itemname) {


        IPage<BlackList> page = new Page<>(pageNum, pageSize);
        QueryWrapper<BlackList> queryWrapper = new QueryWrapper<>();
        if (StringUtils.hasText(itemname)) {
            queryWrapper.like("user_name", itemname);
        }
        queryWrapper.orderByDesc("blacklist_id");


        IPage<BlackList> page1 = blackListService.page(page, queryWrapper);
        return R.SUCCESS(page1);
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
    public R itemShow(@RequestBody BlackList id) {
        if (blackListMapper.updateById(id) == 1) {
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
    public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        String token = request.getHeader("token");

        List<Map<String, Object>> list = CollUtil.newArrayList();
        List<BlackList> all = blackListMapper.selectList(null);

        for (BlackList it : all) {
            Map<String, Object> row1 = new LinkedHashMap<>();
            row1.put("唯一标识符", it.getBlacklistId());
            row1.put("用户编号", it.getUserId());
            row1.put("姓名", it.getName());
            row1.put("年级", it.getGrade());
            row1.put("专业", it.getProf());
            row1.put("添加时间", it.getAddTime());
            row1.put("备注", it.getDescription());

            list.add(row1);
        }

        // 2. 写excel
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("黑名单信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(System.out);


    }


}
