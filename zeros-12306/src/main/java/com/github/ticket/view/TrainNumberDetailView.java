package com.github.ticket.view;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhaozhenhang <zhaozhenhang@kuaishou.com>
 * Created on 2023-01-22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainNumberDetailView {

    private Integer id;

    private Integer trainNumberId;

    private Integer fromStationId;

    private Integer fromCityId;

    private Integer toStationId;

    private Integer toCityId;

    private Integer stationIndex;

    private Integer relativeMinute;

    private Integer waitMinute;

    private String money;

    private String fromStation;

    private String toStation;

    private String trainNumber;

}
