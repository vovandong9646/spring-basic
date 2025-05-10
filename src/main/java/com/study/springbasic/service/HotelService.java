package com.study.springbasic.service;

import com.study.springbasic.dto.request.CreateHotelRequest;
import com.study.springbasic.dto.request.HotelSearchCriteria;
import com.study.springbasic.dto.request.UpdateHotelRequest;
import com.study.springbasic.dto.response.HotelDTO;
import com.study.springbasic.dto.response.PagedResponse;
import com.study.springbasic.dto.response.PaginationInfo;
import com.study.springbasic.entity.Hotel;
import com.study.springbasic.repository.HotelRepository;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class HotelService {

  @Autowired
  private HotelRepository hotelRepository;

  @Autowired
  private ModelMapper modelMapper;

  public HotelDTO getHotelById(Long id) {
    Optional<Hotel> hotelEntityOptional = hotelRepository.findById(id);
    if (hotelEntityOptional.isPresent()) {
      // mapping entity to DTO
      return modelMapper.map(hotelEntityOptional.get(), HotelDTO.class);
    }
    return null;
  }

  public PagedResponse<HotelDTO> getHotelList(HotelSearchCriteria criteriaRequest,
      Pageable pageable) {

    // search specification
    Specification<Hotel> specification = buildSpecification(criteriaRequest);
    Page<Hotel> page = hotelRepository.findAll(specification, pageable);

    // map entity to DTO
    List<HotelDTO> hotelDTOList = page.getContent().stream().map(hotel -> {
      //      HotelDTO hotelDTO = new HotelDTO();
      //      hotelDTO.setId(hotel.getHotelId());
      //      hotelDTO.setName(hotel.getName());
      //      hotelDTO.setDescription(hotel.getDescription());
      //      hotelDTO.setStatus(hotel.getStatus());
      //      hotelDTO.setVersion(hotel.getVersion());
      //      hotelDTO.setCreatedAt(hotel.getCreatedAt());
      //      hotelDTO.setCreatedBy(hotel.getCreatedBy());
      //      hotelDTO.setUpdatedAt(hotel.getUpdatedAt());
      //      hotelDTO.setUpdatedBy(hotel.getUpdatedBy());
      HotelDTO hotelDTO = modelMapper.map(hotel, HotelDTO.class);
      return hotelDTO;
    }).collect(Collectors.toList());

    return PagedResponse.<HotelDTO>builder()
        .data(hotelDTOList)
        .paginationInfo(PaginationInfo.builder()
                            .currentPage(page.getNumber())
                            .pageSize(page.getSize())
                            .totalItems(page.getTotalElements())
                            .totalPages(page.getTotalPages())
                            .build())
        .build();
  }

  /*
   * build search specification
   *
   * @param request
   * @return
   */
  private Specification<Hotel> buildSpecification(HotelSearchCriteria request) {
    return (root, query, cb) -> {
      List<Predicate> predicates = new ArrayList<>();
      // neu request.searchByName co input
      if (StringUtils.hasText(request.getName())) {
        // `hotel.name` like '%searchByName%'
        predicates.add(cb.like(root.get("name"), "%" + request.getName() + "%"));
      }
      // neu request.searchByDescription co input
      if (StringUtils.hasText(request.getDescription())) {
        predicates.add(cb.like(root.get("description"), "%" + request.getDescription() + "%"));
      }
      // neu request.searchByStatus co input
      if (request.getStatus() != null) {
        predicates.add(cb.equal(root.get("status"), request.getStatus()));
      }

      // return predicate
      return cb.and(predicates.toArray(new Predicate[predicates.size()]));
    };
  }

  public HotelDTO createHotel(CreateHotelRequest request) {
    // mapping from request to entity
    //    Hotel hotel = new Hotel();
    //    hotel.setName(request.getName());
    //    hotel.setDescription(request.getDescription());
    //    hotel.setStatus(request.getStatus());
    //    hotel.setVersion(request.getVersion());
    Hotel hotel = modelMapper.map(request, Hotel.class);

    // save entity
    Hotel hotelSaved = hotelRepository.save(hotel);

    // mapping entity to DTO
    return modelMapper.map(hotelSaved, HotelDTO.class);
  }

  public HotelDTO updateHotel(Long hotelId, UpdateHotelRequest request) {
    // giả su: dung HotelDTO thi co the khong day du column de cap nhat
    // vi vay su dung truc tiep entity ma find duoc
    Hotel hotelInDb = hotelRepository.findById(hotelId).orElse(null);
    if (hotelInDb != null) {
      // mapping from request to entity (chỉ mapping item request có giá trị khác null) - Thay vi phai check null nhu ben duoi
      // Lưu ý 1: destination phải là hotelInDb, chứ k được Hotel.class, vì nếu dùng Hotel.class là nó sẽ tạo 1 thực thể mới, do đó tất cả item không set sẽ null
      // Lưu ý 2: Phải cấu hình thêm `modelMapper.getConfiguration().setSkipNullEnabled(true);` ở class ModelMapperConfig.java
      modelMapper.map(request, hotelInDb);
//      if (request.getName() != null) {
//        hotelInDb.setName(request.getName());
//      }
//      if (request.getDescription() != null) {
//        hotelInDb.setDescription(request.getDescription());
//      }
//      if (request.getStatus() != null) {
//        hotelInDb.setStatus(request.getStatus());
//      }
//      if (request.getVersion() != null) {
//        hotelInDb.setVersion(request.getVersion());
//      }
      Hotel hotelUpdated = hotelRepository.save(hotelInDb);
      return modelMapper.map(hotelUpdated, HotelDTO.class);
    }
    return null;
  }

  public boolean deleteHotel(Long id) {
    if (hotelRepository.existsById(id)) {
      hotelRepository.deleteById(id);
      return true;
    }
    return false;
  }


}
