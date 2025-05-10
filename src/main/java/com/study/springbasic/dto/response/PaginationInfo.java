package com.study.springbasic.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PaginationInfo {

  private int currentPage;
  private int pageSize;
  private long totalItems;
  private int totalPages;
}
