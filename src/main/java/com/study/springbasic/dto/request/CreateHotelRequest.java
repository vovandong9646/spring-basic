package com.study.springbasic.dto.request;

import lombok.Data;

@Data
public class CreateHotelRequest {

  private String name;
  private String description;
  private Integer status;
  private Integer version;
}
