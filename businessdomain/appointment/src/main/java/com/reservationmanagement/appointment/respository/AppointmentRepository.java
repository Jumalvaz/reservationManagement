package com.reservationmanagement.appointment.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservationmanagement.appointment.entities.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    
}
