package com.tothefor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tothefor.pojo.entity.BlackList;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @Author DragonOne
 * @Date 2022/4/28 20:27
 * @墨水记忆 www.tothefor.com
 */

@Component
@Repository
@Mapper
public interface BlackListMapper extends BaseMapper<BlackList> {
}
