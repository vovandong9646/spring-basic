package com.study.springbasic.controller;

import com.study.springbasic.dto.request.CreateHotelRequest;
import com.study.springbasic.dto.request.HotelSearchCriteria;
import com.study.springbasic.dto.request.UpdateHotelRequest;
import com.study.springbasic.dto.response.HotelDTO;
import com.study.springbasic.dto.response.PagedResponse;
import com.study.springbasic.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {

  @Autowired
  private HotelService hotelService;

  /**
   * endpoint: http://localhost:8080/api/hotel/1
   *
   * @param hotelId
   * @return
   */
  @GetMapping(value = "/{id}", produces = "application/json")
  public ResponseEntity<HotelDTO> getHotel(@PathVariable("id") Long hotelId) {
    //    return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotelById(hotelId));
    HotelDTO hotelDTO = hotelService.getHotelById(hotelId);
    if (hotelDTO != null) {
      return ResponseEntity.ok(hotelDTO); // 200 OK
    } else {
      return ResponseEntity.notFound().build(); // 404 Not Found
    }
  }

  /**
   * endpoint:
   * http://localhost:8080/api/hotel?searchByName=peach&searchByDescription=famous&searchByStatus
   * =1&page=1&size=3&sort=status,asc&sort=version,desc
   *
   * @param searchByName
   * @param searchByDescription
   * @param searchByStatus
   * @param pageable
   * @return
   */
  @GetMapping(value = {"/", ""}, produces = "application/json")
  public ResponseEntity<PagedResponse<HotelDTO>> getListHotel(
      @RequestParam(required = false) String searchByName,
      @RequestParam(required = false) String searchByDescription,
      @RequestParam(required = false) Integer searchByStatus,
      @PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
    HotelSearchCriteria searchCriteria =
        new HotelSearchCriteria(searchByName, searchByDescription, searchByStatus);
    return ResponseEntity.ok(hotelService.getHotelList(searchCriteria, pageable));
  }

  /**
   * endpoint: http://localhost:8080/api/hotel (POST)
   *
   * @param request
   * @return
   */
  @PostMapping({"/", ""})
  public ResponseEntity<HotelDTO> createHotel(@RequestBody CreateHotelRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.createHotel(request));
  }

  /**
   * endpoint: http://localhost:8080/api/hotel/1 (PUT)
   *
   * @param hotelId
   * @param request
   * @return
   */
  @PutMapping({"/{id}"})
  public ResponseEntity<HotelDTO> updateHotel(@PathVariable("id") Long hotelId,
      @RequestBody UpdateHotelRequest request) {

    HotelDTO hotelUpdated = hotelService.updateHotel(hotelId, request);

    if (hotelUpdated != null) {
      return ResponseEntity.ok(hotelUpdated); // 200 OK
    } else {
      return ResponseEntity.notFound().build(); // 404 Not found
    }
  }

  /**
   * endpoint: http://localhost:8080/api/hotel/2 (DELETE)
   *
   * @param hotelId
   * @return
   */
  @DeleteMapping({"/{id}"})
  public ResponseEntity<Void> deleteHotel(@PathVariable("id") Long hotelId) {
    boolean deleted = hotelService.deleteHotel(hotelId);
    if (deleted) {
      return ResponseEntity.noContent().build(); // 204 No content
    } else {
      return ResponseEntity.notFound().build(); // 404 Not found
    }
  }


}
