package com.study.springbasic.repository;

import com.study.springbasic.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long>,
    JpaSpecificationExecutor<Hotel> {

}
