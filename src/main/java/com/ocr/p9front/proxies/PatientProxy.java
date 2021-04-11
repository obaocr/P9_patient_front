package com.ocr.p9front.proxies;

import com.ocr.p9front.domain.PatientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Proxy for Patient / micro service
 */
@FeignClient(name = "microservice-patient", url = "http://patient:8045")
public interface PatientProxy {

    @GetMapping(value = "/patient")
    List<PatientDTO> getPatients();

    @RequestMapping(method = RequestMethod.GET, value = "/patient/{Id}")
    PatientDTO getPatientById(@PathVariable("Id") Integer Id);

    @PostMapping(value = "/patient")
    Integer addPatient(@RequestBody PatientDTO patient);

    @RequestMapping(method = RequestMethod.PUT, value = "/patient/{Id}")
    Boolean updatePatient(@RequestParam Integer Id, @RequestBody PatientDTO patient);

    @RequestMapping(method = RequestMethod.DELETE, value = "/patient/{Id}")
    Boolean deletePatient(@RequestParam Integer Id);

}
