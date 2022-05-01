package com.tothefor.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tothefor.mapper.*;
import com.tothefor.pojo.dto.EchartsR;
import com.tothefor.pojo.dto.PieData;
import com.tothefor.pojo.entity.*;
import com.tothefor.resultR.R;
import com.tothefor.service.impl.*;
import com.tothefor.utils.emailSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.*;

/**
 * 物品controller
 *
 * @Author DragonOne
 * @Date 2022/3/4 13:28
 * @墨水记忆 www.tothefor.com
 */

@RestController
@RequestMapping("/item")
public class InformationController {

    @Autowired
    private InformationMapper informationMapper;

    @Autowired
    private InformationServiceImpl informationService;

    @Autowired
    private ApplyMapper applyMapper;


    @Autowired
    private BlackListMapper blackListMapper;


    @Autowired
    private emailSend emailSend;


    /**
     * @Author DragonOne
     * @Date 2022/4/29
     * @墨水记忆 www.tothefor.com
     * @方法名称 getProfessionByTypeId
     * @方法描述 Echarts获取专业及其对应数据
     * @参数说明
     * @返回值
     */
    @GetMapping("/getProfessionByTypeId/{typeId}")
    public R getProfessionByTypeId(@PathVariable String typeId){
        QueryWrapper<Information> qw = new QueryWrapper<>();
        qw.eq("typeID",typeId);
        // 对应报名表的所有人
        List<Information> items = informationMapper.selectList(qw);

        //获取专业
        Set<String> pros = new HashSet<>();
        for(Information it: items){
            pros.add(it.getDescription());
        }

        //找出每一个专业对应的人数
        List<EchartsR> ans = new ArrayList<>();
        for(String it: pros){
            EchartsR r = new EchartsR();
            r.setName(it);

            QueryWrapper<Information> qwmid = new QueryWrapper<>();
            qwmid.eq("professional",it);
            qwmid.eq("typeID",typeId);
            Integer integer = informationMapper.selectCount(qwmid);
            r.setValue(integer);
            ans.add(r);
        }
        return R.SUCCESS(ans);

    }

    /**
     * @Author DragonOne
     * @Date 2022/4/29
     * @墨水记忆 www.tothefor.com
     * @方法名称 getSchoolByTypeId
     * @方法描述 Echarts获取院校及其对应数据
     * @参数说明
     * @返回值
     */
    @GetMapping("/getSchoolByTypeId/{typeId}")
    public R getSchoolByTypeId(@PathVariable String typeId){
        QueryWrapper<Information> qw = new QueryWrapper<>();
        qw.eq("typeID",typeId);
        // 对应报名表的所有人
        List<Information> items = informationMapper.selectList(qw);

        //获取院校
        Set<String> schools = new HashSet<>();
        for(Information it: items){
            schools.add(it.getSize());
        }

        //找出每一个院校对应的人数
            //院校
        List<String> scs = new ArrayList<>();
            //每个院校的人员
        List<Integer> values = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        for(String it: schools){
            scs.add(it);

            QueryWrapper<Information> qwmid = new QueryWrapper<>();
            qwmid.eq("school",it);
            qwmid.eq("typeID",typeId);
            Integer integer = informationMapper.selectCount(qwmid);
            values.add(integer);
        }
        map.put("x",CollUtil.newArrayList(scs));
        map.put("y",CollUtil.newArrayList(values));
        return R.SUCCESS(map);

    }

    /**
     * @Author DragonOne
     * @Date 2022/4/29
     * @墨水记忆 www.tothefor.com
     * @方法名称 getGradeByTypeId
     * @方法描述 Echarts获取年级及其对应数据
     * @参数说明
     * @返回值
     */
    @GetMapping("/getGradeByTypeId/{typeId}")
    public R getGradeByTypeId(@PathVariable String typeId){

        QueryWrapper<Information> qw = new QueryWrapper<>();
        qw.eq("typeID",typeId);
        // 对应报名表的所有人
        List<Information> items = informationMapper.selectList(qw);


        //找出所有的年级
        Set<String> grades = new HashSet<>();

        for(Information it: items){
            grades.add(it.getItemCount());
        }

        //找出每一个年级对应的人数
            //年级
        List<String> gradess = new ArrayList<>();
            //每个年级的人员
        List<Integer> values = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        for(String grade: grades){
            gradess.add(grade);

            QueryWrapper<Information> qwmid = new QueryWrapper<>();
            qwmid.eq("grade",grade);
            qwmid.eq("typeID",typeId);
            Integer integer = informationMapper.selectCount(qwmid);
            values.add(integer);
        }
        map.put("x",CollUtil.newArrayList(gradess));
        map.put("y",CollUtil.newArrayList(values));
        return R.SUCCESS(map);
    }

    /**
     * @Author DragonOne
     * @Date 2022/3/23
     * @墨水记忆 www.tothefor.com
     * @方法名称 getTypeName
     * @方法描述 获取类型的全部名称
     * @参数说明
     * @返回值
     */
    @GetMapping("/getTypeName")
    public R getTypeName() {
        QueryWrapper<Apply> qw = new QueryWrapper();
        qw.ne("type_id", 0);
        List<Apply> itemTypes = applyMapper.selectList(qw);

        Map<String, Object> map = new HashMap<>();
        List<Integer> listi = new ArrayList<>();
        List<String> lists = new ArrayList<>();
        for (Apply it : itemTypes) {
            lists.add(it.getTypename());

            Long typeId = it.getTypeId();
            QueryWrapper<Information> qw2 = new QueryWrapper<>();
            qw2.eq("typeID", typeId);
            List<Information> items = informationMapper.selectList(qw2);
            listi.add(items.size());
        }
        map.put("x", CollUtil.newArrayList(lists));
        map.put("y", CollUtil.newArrayList(listi));

        return R.SUCCESS(map);
    }

    /**
     * @Author DragonOne
     * @Date 2022/3/13
     * @墨水记忆 www.tothefor.com
     * @方法名称 getPieData
     * @方法描述 获取每个类型种类的对应数量
     * @参数说明
     * @返回值
     */
    @PostMapping("/getPie")
    public R getPieData() {
        List<PieData> pieData = new ArrayList<>();
        List<Apply> itemTypes = applyMapper.selectList(null);
        for (Apply it : itemTypes) {
            if (it.getTypeId() == 0) itemTypes.remove(it);
        }
        for (Apply it : itemTypes) {
            Long typeId = it.getTypeId();
            QueryWrapper<Information> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("typeID", typeId);
            int typeCnt = informationMapper.selectList(queryWrapper).size();
            PieData mpd = new PieData();
            mpd.setName(it.getTypename());
            mpd.setValue(typeCnt);
            pieData.add(mpd);
        }
        return R.SUCCESS(pieData);
    }


    /**
     * @Author DragonOne
     * @Date 2022/3/13
     * @墨水记忆 www.tothefor.com
     * @方法名称 getItemCnt
     * @方法描述 获取物品总数
     * @参数说明
     * @返回值
     */
    @PostMapping("/getItemCnt")
    public R getItemCnt() {
        List<Information> items = informationMapper.selectList(null);
        int size = items.size();
        return R.SUCCESS(size);
    }

    /**
     * @Author DragonOne
     * @Date 2022/3/13
     * @墨水记忆 www.tothefor.com
     * @方法名称 getItemTypeCnt
     * @方法描述 获取物品类型总数
     * @参数说明
     * @返回值
     */
    @PostMapping("/getItemTypeCnt")
    public R getItemTypeCnt() {
        List<Apply> itemTypes = applyMapper.selectList(null);
        int size = itemTypes.size();
        size--;
        return R.SUCCESS(size);
    }


    /**
     * @return
     * @Author DragonOne
     * @Date 2022/3/6 17:01
     * @墨水记忆 www.tothefor.com
     * @方法 itemAll
     * @作用 查找所有物品信息接口
     * @参数说明
     */
    @GetMapping("/itemAll")
    public R<List<Information>> itemAll() {

        return R.SUCCESS(informationMapper.selectList(null));
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
    public R itemSave(@RequestBody Information item) throws Exception {

        if(item.getFlagUP()){
            if (informationService.saveOrUpdate(item)) {
                return R.SUCCESS();
            } else {
                return R.FAIL();
            }
        }else{
            String userXH = item.getItemId();
            Long bmbBH = item.getTypeid();

            QueryWrapper<Information> qwuser = new QueryWrapper();
            qwuser.eq("user_id",userXH);
            qwuser.eq("typeID",bmbBH);
            Integer integer = informationMapper.selectCount(qwuser);
            if(integer>0) return R.FAIL(402,"已经存在该用户！");


            if(item.getDescription().equals("0") && item.getDescription2()!=null) item.setDescription(item.getDescription2());

            QueryWrapper<BlackList> qw = new QueryWrapper();
            qw.eq("blacklist_user_id", item.getItemId());
            Integer aLong = blackListMapper.selectCount(qw);

            String xh = item.getItemId();
            String em = item.getEmail();

            QueryWrapper<Apply> qw2 = new QueryWrapper();
            qw2.ne("is_show", 0);
            qw2.orderByDesc("id");
            Apply itemType = applyMapper.selectOne(qw2);
            String bm = itemType.getTypename();

            if (aLong > 0) {
                return R.FAIL(401, "对不起！您被限制参加此类比赛。");
            } else {
                try {
                    emailSend.sendEmail(xh,bm,em);
                    if (informationService.saveOrUpdate(item)) {
                        return R.SUCCESS();
                    } else {
                        return R.FAIL();
                    }
                }catch (Exception e){
                    return R.FAIL();
                }
            }

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
        if (informationMapper.deleteById(id) == 1) {
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
        if (informationMapper.deleteBatchIds(ids) == len) {
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
    public R<IPage<Information>> PageItemAll(@RequestParam("pageNum") Integer pageNum,
                                      @RequestParam("PageSize") Integer pageSize,
                                      @RequestParam(defaultValue = "") String itemname,
                                      @RequestParam(defaultValue = "0") Integer searchtype) {


        IPage<Information> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Information> queryWrapper = new QueryWrapper<>();
        if (StringUtils.hasText(itemname)) {
            queryWrapper.like("name", itemname);
        }
        if (searchtype != 0) {
            queryWrapper.eq("typeID", searchtype);
        }
        queryWrapper.orderByDesc("id");


        IPage<Information> page1 = informationService.page(page, queryWrapper);
        List<Information> records = page1.getRecords();
        for (Information it : records) {
            Long typeid = it.getTypeid();
            QueryWrapper<Apply> qw = new QueryWrapper();
            qw.eq("type_id", typeid);
            Apply itemType = applyMapper.selectOne(qw);
            it.setTypeName(itemType.getTypename());
        }
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
    public R itemShow(@RequestBody Information id) {
        if (informationMapper.updateById(id) == 1) {
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
    @GetMapping("/export/{typeID}")
    public void export(@PathVariable String typeID,HttpServletRequest request, HttpServletResponse response) throws Exception {
//        String token = request.getHeader("token");

        QueryWrapper<Information> qwtype = new QueryWrapper();
        qwtype.eq("typeID",typeID);

        List<Map<String, Object>> list = CollUtil.newArrayList();
        List<Information> all = informationMapper.selectList(qwtype);

        for (Information it : all) {
            Map<String, Object> row1 = new LinkedHashMap<>();
            row1.put("唯一标识符", it.getId());
            row1.put("用户编号", it.getItemId());
            row1.put("姓名", it.getName());
            row1.put("性别", it.getSex());

            Long typeid = it.getTypeid();
            QueryWrapper<Apply> qwmid = new QueryWrapper<>();
            qwmid.eq("type_id",typeid);
            Apply itemType = applyMapper.selectOne(qwmid);

            row1.put("报名表标题", itemType.getTypename());
            row1.put("报名时间", it.getItemAddTime());
            row1.put("院校", it.getSize());
            row1.put("年级", it.getItemCount());
            row1.put("专业", it.getDescription());
            row1.put("手机号", it.getPhone());
            row1.put("邮箱", it.getEmail());

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
