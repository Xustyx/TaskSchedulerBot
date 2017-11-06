package com.xsty.telegram.web;

import com.xsty.telegram.alert.Alert;
import com.xsty.telegram.alert.AlertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by XST on 22/10/2017.
 */
@RestController
@RequestMapping("/alert")
public class AlertController {

    private AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @GetMapping
    public ResponseEntity<List<Alert>> getAlerts(){
        return ResponseEntity.ok(alertService.findAll());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Alert> getAlert(@PathVariable("id") Long id){
        return ResponseEntity.ok(alertService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<Alert> saveAlert(@RequestBody Alert alert){
        return ResponseEntity.ok(alertService.save(alert));
    }


    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public void deleteAlert(@PathVariable("id") Long id){
        alertService.delete(
            alertService.findOne(id)
        );
    }
}
