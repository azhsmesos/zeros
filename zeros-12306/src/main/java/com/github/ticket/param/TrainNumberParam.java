package com.github.ticket.param;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-23
 */
@Data
@Builder
public class TrainNumberParam {

    private Integer id;

    @NotBlank(message = "车次不可以为null")
    private String name;

    @NotBlank(message = "类型不可以为null")
    private String trainType;

    @Min(value = 1)
    @Max(value = 4)
    private Integer type;
}
