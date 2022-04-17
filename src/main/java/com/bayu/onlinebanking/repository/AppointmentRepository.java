package com.bayu.onlinebanking.repository;

import com.bayu.onlinebanking.entity.Appointment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

    List<Appointment> findAll();
}
