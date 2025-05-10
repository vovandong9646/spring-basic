package com.study.springbasic.service;

import com.study.springbasic.entity.Hotel;
import com.study.springbasic.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelService {

  @Autowired
  private HotelRepository hotelRepository;

  public Hotel getHotelById(Long id) {
    return hotelRepository.findById(id).orElse(null);
  }


}
