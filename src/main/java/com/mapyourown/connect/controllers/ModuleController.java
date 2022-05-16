package com.mapyourown.connect.controllers;

import com.mapyourown.connect.models.Module;
import com.mapyourown.connect.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ModuleController {
    @Autowired
    private ModuleRepository moduleRepository;

    @GetMapping("/module/{id}")
    ResponseEntity<Module> getModulesById(@PathVariable(value="id") Long moduleId){
        Optional<Module> module = moduleRepository.findById(moduleId);
        return new  ResponseEntity<Module>(module.get(), HttpStatus.OK);
    }
}
