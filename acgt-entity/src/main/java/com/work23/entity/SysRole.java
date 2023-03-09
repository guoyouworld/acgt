package com.work23.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 
 * </p>
 *
 * @author gzheng
 * @since 2023-03-07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("sys_role")
@ApiModel(value = "SysRole对象", description = "")
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "int_id", type = IdType.AUTO)
    private Integer intId;

    private String roleName;

    private String stateFlag;
}
