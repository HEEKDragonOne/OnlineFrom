package com.tothefor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tothefor.pojo.entity.Apply;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @Author DragonOne
 * @Date 2022/4/30 12:22
 * @墨水记忆 www.tothefor.com
 */

@Component
@Repository
@Mapper
public interface ApplyMapper extends BaseMapper<Apply> {
}
