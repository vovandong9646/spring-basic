package com.study.springbasic.dto.response;

import java.util.Date;
import lombok.Data;

@Data
public class HotelDTO {

  private Long id;
  private String name;
  private String description;
  private Integer status;
  private Integer version;
  private Date createdAt;
  private String createdBy;
  private Date updatedAt;
  private String updatedBy;
}
