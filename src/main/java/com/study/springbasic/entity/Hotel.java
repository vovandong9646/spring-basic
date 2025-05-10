package com.study.springbasic.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.Data;

@Entity
@Table(name = "hotel")
@Data
public class Hotel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long hotelId;

  private String name;
  private String description;
  private Integer status;
  private Integer version;

  @Column(name = "created_at")
  private Date createdAt;

  @Column(name = "created_by")
  private String createdBy;

  @Column(name = "updated_at")
  private Date updatedAt;

  @Column(name = "updated_by")
  private String updatedBy;

  // before insert
  @PrePersist
  public void beforeInsert() {
    Date now = new Date();
    this.setCreatedAt(now);
    this.setCreatedBy("SYSTEM"); // change to userlogin
    this.setUpdatedAt(now);
    this.setUpdatedBy("SYSTEM"); // change to userlogin
  }

  @PreUpdate
  public void beforeUpdate() {
    Date now = new Date();
    this.setUpdatedAt(now);
    this.setUpdatedBy("SYSTEM"); // change to userlogin
  }
}
