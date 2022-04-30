package com.tothefor.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.io.Serializable;

/**
 * @Author DragonOne
 * @Date 2022/3/4 13:28
 * @墨水记忆 www.tothefor.com
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)// 使用链式方法
@TableName("s_apply")
public class Apply implements Serializable {
    /**
     * 唯一标识符
     */
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;
    /**
     * 报名表编号
     */
    @TableField("type_id")
    private Long typeId;
    /**
     * 报名表名称
     */
    @TableField("typename")
    private String typename;
    /**
     * 启用状态
     */
    @TableField("is_show")
    private Boolean isShow;
    /**
     * 逻辑删除
     */
    @TableLogic
    @TableField("is_delete")
    private Boolean isDelete;

    /**
     * 报名表描述备注
     */
    @TableField("description")
    private String description;


    /**
     * 开始时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("begin_time")
    private Date beginTime;


    /**
     * 结束时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("end_time")
    private Date endTime;

}

