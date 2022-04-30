package com.tothefor.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tothefor.pojo.entity.Information;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @Author DragonOne
 * @Date 2022/4/30 12:09
 * @墨水记忆 www.tothefor.com
 */
@Component
@Repository
@Mapper
public interface InformationMapper extends BaseMapper<Information> {
}
