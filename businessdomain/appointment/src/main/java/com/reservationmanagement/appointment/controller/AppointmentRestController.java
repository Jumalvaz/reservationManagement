package com.reservationmanagement.appointment.controller;

import com.reservationmanagement.appointment.entities.Appointment;
import com.reservationmanagement.appointment.respository.AppointmentRepository;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author Jumalvaz
 */
@RestController
@RequestMapping("/appointment")
public class AppointmentRestController {

	@Autowired
	AppointmentRepository appointmentRepository;

	@GetMapping()
	public List<Appointment> list() {
		return appointmentRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable long id) {
		Optional<Appointment> appointment = appointmentRepository.findById(id);
		if (appointment.isPresent()) {
			return new ResponseEntity<>(appointment.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> put(@PathVariable long id, @RequestBody Appointment input) {
		Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
		if (optionalAppointment.isPresent()) {
			Appointment newAppointment = optionalAppointment.get();
			newAppointment.setDetail(input.getDetail());
			newAppointment.setTime(input.getTime());
			Appointment save = appointmentRepository.save(newAppointment);
			return new ResponseEntity<>(save, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<?> post(@RequestBody Appointment input) {
		Appointment save = appointmentRepository.save(input);
		return ResponseEntity.ok(save);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable long id) {
		appointmentRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
