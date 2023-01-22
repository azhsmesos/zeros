package com.github.ticket.param;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-22
 */
@Data
@Builder
public class TrainStationParam {

    private Integer id;

    @NotBlank(message = "站点名称不可以为null")
    private String name;

    @NotNull
    @Min(value = 1, message = "城市不合法")
    private Integer cityId;
}
