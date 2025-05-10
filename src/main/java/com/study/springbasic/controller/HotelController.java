package com.study.springbasic.controller;

import com.study.springbasic.entity.Hotel;
import com.study.springbasic.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {

  @Autowired
  private HotelService hotelService;

  @GetMapping("/{id}")
  public ResponseEntity<Hotel> getHotel(@PathVariable("id") Long hotelId) {
//    return ResponseEntity.status(HttpStatus.OK).body(hotelService.getHotelById(hotelId));
    return ResponseEntity.ok(hotelService.getHotelById(hotelId));
  }

}
