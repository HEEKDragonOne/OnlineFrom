package com.tothefor.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author DragonOne
 * @Date 2022/4/28 20:23
 * @墨水记忆 www.tothefor.com
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)// 使用链式方法
@TableName("s_black_list")
public class BlackList implements Serializable {

    /**
     * 黑名单id
     */
    @TableId(type = IdType.AUTO)
    @TableField("blacklist_id")
    private Long blacklistId;

    /**
     * 黑名单用户编号
     */
    @TableField("blacklist_user_id")
    private String userId;

    /**
     * 逻辑删除
     */
    @TableField("is_delete")
    private Boolean iSDelete;

    /**
     * 黑名单用户姓名
     */
    @TableField("user_name")
    private String name;
    /**
     * 黑名单用户年级
     */
    @TableField("user_grade")
    private String grade;
    /**
     * 专业
     */
    @TableField("usr_prof")
    private String prof;

    /**
     * 添加时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "add_time", fill = FieldFill.INSERT) //在插入时填充
    private Date addTime;

    /**
     * 备注
     */
    @TableField("description")
    private String description;
}
