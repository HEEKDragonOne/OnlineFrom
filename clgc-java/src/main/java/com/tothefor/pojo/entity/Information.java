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
@TableName("s_information")
public class Information implements Serializable {
    /**
     * 唯一id
     */
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;
    /**
     * 用户编号
     */
    @TableField("user_id")
    private String itemId;
    /**
     * 用户名
     */
    @TableField("name")
    private String name;
    /**
     * 报名表编号
     */
    @TableField("typeID")
    private Long typeid;
    /**
     * 报名表名称
     */
    @TableField(exist = false)
    private String typeName;
    /**
     * 启用状态
     */
    @TableField("is_show")
    private Boolean isShow;
    /**
     * 是否删除
     */
    @TableLogic
    @TableField("is_delete")
    private Boolean isDelete;
    /**
     * 报名时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "user_add_time", fill = FieldFill.INSERT) //在插入时填充
    private Date itemAddTime;

    /**
     * 院校
     */
    @TableField("school")
    private String size;
    /**
     * 专业和其他专业，professional，后台处理时如果professional为空而professional2不为空，则会将professional2的值给professional
     */
    @TableField("professional")
    private String description;
    @TableField("professional2")
    private String description2;
    /**
     * 年级
     */
    @TableField("grade")
    private String itemCount;

    /**
     * 手机号
     */
    @TableField("phone")
    private String phone;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;
    /**
     * 性别
     */
    @TableField("sex")
    private String sex;


}

