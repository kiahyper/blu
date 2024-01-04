package com.blubank.doctorappointment.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/doctor")
@RestController
public class PatientController {

    @Operation(summary = "", description = "AssetInfoMapper")
    @GetMapping()
    public String updateData() {
        return"Ok";
    }
}
