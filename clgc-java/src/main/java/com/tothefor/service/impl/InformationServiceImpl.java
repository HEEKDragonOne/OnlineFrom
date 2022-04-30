package com.tothefor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tothefor.mapper.InformationMapper;
import com.tothefor.pojo.entity.Information;
import com.tothefor.service.InformationService;
import org.springframework.stereotype.Service;

/**
 * @Author DragonOne
 * @Date 2022/4/30 12:11
 * @墨水记忆 www.tothefor.com
 */
@Service("InformationServiceImpl")
public class InformationServiceImpl extends ServiceImpl<InformationMapper, Information> implements InformationService {
}
