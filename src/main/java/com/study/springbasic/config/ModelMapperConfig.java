package com.study.springbasic.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Class được đánh dấu @Configuration trong Spring Boot (như ModelMapperConfig) sẽ được Spring scan và khởi tạo ngay khi ứng dụng Spring Boot khởi động (lúc ApplicationContext được tạo ra)
// Khi Spring Boot khởi động, nó sẽ tự động quét (component scan) các class có annotation @Configuration trong project.
// Ngay sau khi ApplicationContext được khởi tạo, các class @Configuration này sẽ được thực thi để tạo ra các bean mà bạn định nghĩa bằng annotation @Bean bên trong class đó
// Các bean này (ví dụ: bean modelMapper) sẽ tồn tại trong suốt vòng đời của ApplicationContext (thường là singleton, sống đến khi ứng dụng tắt)
@Configuration
public class ModelMapperConfig {

  // Theo mặc định của Spring, khi khai báo một bean bằng annotation @Bean trong một class cấu hình (@Configuration) mà không chỉ định tên bean, thì tên bean sẽ chính là tên method.
  // Ví dụ bên dưới tên bean là: "modelMapper" (trùng với tên method).
  // Thứ tự inject: Type (kiểu dữ liệu - ModelMapper) -> @Qualifier or @Primary -> tên bean (tên method)
  // Giải thích: Nếu có 1 bean 1 Type thì inject theo Type (không quan tâm tên bean)
  // Còn nếu có nhiều bean cùng Type (vd: modelMapper và testDuplicateBean cùng Type là ModelMapper) thì tại lúc inject @Autowired nếu chỉ đin rõ tên bean thì lấy đúng tên bean chỉ định.
  // Còn nếu không có @Qualifier or @Primary tại luc inject thì nó sẽ mapping theo tên method (tên bean)

  // Ket luan:
  // Bạn có thể inject với bất kỳ tên biến nào, ví dụ @Autowired private ModelMapper mapper; hoặc private ModelMapper modelMapper;, Spring sẽ tự động inject bean dựa vào type là ModelMapper nếu chỉ có một bean loại này trong context.
  // Nếu có nhiều bean cùng type, Spring sẽ tìm bean có tên trùng với tên biến bạn khai báo khi inject. Truong hop ten bien khong trùng thi phai khai bao @Qualifier
  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    // MatchingStrategies.STRICT giúp ánh xạ chính xác tên thuộc tính giữa Entity và DTO, hạn chế lỗi khi tên khác nhau
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    // Bỏ qua thuộc tính null
    modelMapper.getConfiguration().setSkipNullEnabled(true);
    return modelMapper;
  }

//  @Bean
//  public ModelMapper testDuplicateBean() {
//    ModelMapper modelMapper = new ModelMapper();
//    // MatchingStrategies.STRICT giúp ánh xạ chính xác tên thuộc tính giữa Entity và DTO, hạn chế lỗi khi tên khác nhau
//    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//    // Bỏ qua thuộc tính null
//    modelMapper.getConfiguration().setSkipNullEnabled(true);
//    return modelMapper;
//  }

}
