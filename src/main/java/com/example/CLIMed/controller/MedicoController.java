package com.example.CLIMed.controller;

import com.example.CLIMed.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedicoController {

    @Autowired
    MedicoRepository medicoRepository;
}
