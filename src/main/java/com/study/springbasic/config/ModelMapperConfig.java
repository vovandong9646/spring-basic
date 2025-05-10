package com.study.springbasic.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    // MatchingStrategies.STRICT giúp ánh xạ chính xác tên thuộc tính giữa Entity và DTO, hạn chế lỗi khi tên khác nhau
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    // Bỏ qua thuộc tính null
    modelMapper.getConfiguration().setSkipNullEnabled(true);
    return modelMapper;
  }

}
