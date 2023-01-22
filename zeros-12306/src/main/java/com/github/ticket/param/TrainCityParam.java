package com.github.ticket.param;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-22
 */
@Data
@Builder
public class TrainCityParam {

    private Integer id;

    @NotBlank(message = "城市名不可以为null")
    @Length(min = 1, max = 40, message = "城市名称长度在1-40个字符之间")
    private String name;
}
