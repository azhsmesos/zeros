package com.github.ticket.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainSeat {
    private Long id;

    private String ticket;

    private Long userId;

    private Long travellerId;

    private Integer trainNumberId;

    private Integer carriageNumber;

    private Integer rowNumber;

    private Integer seatNumber;

    private Integer seatLevel;

    private Date trainStart;

    private Date trainEnd;

    private Integer money;

    private String showNumber;

    private Integer status;

    private Integer fromStationId;

    private Integer toStationId;

}