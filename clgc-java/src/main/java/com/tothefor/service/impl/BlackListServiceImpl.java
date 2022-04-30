package com.tothefor.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tothefor.mapper.BlackListMapper;
import com.tothefor.pojo.entity.BlackList;
import com.tothefor.service.BlackListService;
import org.springframework.stereotype.Service;

/**
 * @Author DragonOne
 * @Date 2022/4/28 20:29
 * @墨水记忆 www.tothefor.com
 */

@Service("BlackListServiceImpl")
public class BlackListServiceImpl extends ServiceImpl<BlackListMapper, BlackList> implements BlackListService {
}
