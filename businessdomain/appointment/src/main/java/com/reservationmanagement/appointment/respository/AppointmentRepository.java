package com.reservationmanagement.appointment.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservationmanagement.appointment.entities.Appointment;

/**
 *
 * @author Jumalvaz
 */
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    
}
