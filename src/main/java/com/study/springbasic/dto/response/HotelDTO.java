package com.study.springbasic.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.Data;
import org.springframework.core.annotation.Order;

@Data
public class HotelDTO {

  @JsonProperty("id")
  private Long hotelId;

  @JsonProperty("name")
  private String name;

  @JsonProperty("description")
  private String description;

  @JsonProperty("status")
  private Integer status;

  @JsonProperty("version")
  private Integer version;

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("created_by")
  private String createdBy;

  @JsonProperty("updated_at")
  private Date updatedAt;

  @JsonProperty("updated_by")
  private String updatedBy;
}
