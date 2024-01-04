package com.blubank.doctorappointment.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/doctor")
@RestController
public class DoctorController {

    @Operation(summary = "", description = "")
    @GetMapping()
    public String updateData() {
        return"Ok";
    }
}
