package com.reservationmanagement.appointment.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
/**
 *
 * @author Jumalvaz
 */
@Data
@Entity
public class Appointment {
   
   @GeneratedValue(strategy = GenerationType.AUTO)  
   @Id
   private long id;
   private String detail;
   private Date time;    
   
}
