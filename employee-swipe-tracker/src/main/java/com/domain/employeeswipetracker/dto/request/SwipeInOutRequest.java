package com.domain.employeeswipetracker.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SwipeInOutRequest {

    @JsonProperty("id")
    private Long employeeId;

    @JsonProperty("swipe")
    private String swipeState;
}
