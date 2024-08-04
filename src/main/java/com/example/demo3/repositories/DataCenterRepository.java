package com.example.demo3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo3.models.DataCenter;

public interface DataCenterRepository extends JpaRepository<DataCenter, Long> {

}
