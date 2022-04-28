package com.bayu.onlinebanking.controller;

import com.bayu.onlinebanking.entity.Appointment;
import com.bayu.onlinebanking.entity.User;
import com.bayu.onlinebanking.service.AppointmentService;
import com.bayu.onlinebanking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    private final UserService userService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, UserService userService) {
        this.appointmentService = appointmentService;
        this.userService = userService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createAppointment(Model model) {
        Appointment appointment = new Appointment();
        model.addAttribute("appointment", appointment);
        model.addAttribute("dateString", "");

        return "appointment";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createAppointmentPost(@ModelAttribute("appointment") Appointment appointment,
                                        @ModelAttribute("dateString") String date,
                                        Model model,
                                        Principal principal) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date date1 = dateFormat.parse(date);
        appointment.setDate(date1);

        User user = userService.findByUsername(principal.getName());
        appointment.setUser(user);

        appointmentService.createAppointment(appointment);

        return "redirect:/userFront";
    }
}
