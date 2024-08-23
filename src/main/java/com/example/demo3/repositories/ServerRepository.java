package com.example.demo3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo3.models.Server;

public interface ServerRepository extends JpaRepository<Server, Long> {

}
