package com.tothefor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tothefor.mapper.ApplyMapper;
import com.tothefor.pojo.entity.Apply;
import com.tothefor.service.ApplyService;
import org.springframework.stereotype.Service;

/**
 * @Author DragonOne
 * @Date 2022/4/30 12:23
 * @墨水记忆 www.tothefor.com
 */

@Service("ApplyServiceImpl")
public class ApplyServiceImpl extends ServiceImpl<ApplyMapper, Apply> implements ApplyService {
}
