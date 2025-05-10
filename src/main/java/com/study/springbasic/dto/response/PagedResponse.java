package com.study.springbasic.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PagedResponse<T> {

  private List<T> data;
  private PaginationInfo paginationInfo;
}
