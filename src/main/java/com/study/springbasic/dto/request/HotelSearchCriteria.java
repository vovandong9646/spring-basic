package com.study.springbasic.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelSearchCriteria {

  private String name;
  private String description;
  private Integer status;
}
