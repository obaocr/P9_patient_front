package com.ocr.p9front.proxies;

import com.ocr.p9front.domain.PatientDTO;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Proxy for Patient / micro service
 */
@FeignClient(name = "microservice-patient", url = "http://localhost:8045")
public interface PatientProxy {

    @GetMapping(value = "/Patients")
    List<PatientDTO> getPatients();

    @RequestMapping(method = RequestMethod.GET, value = "/Patient")
    PatientDTO getPatientById(@RequestParam("Id") Integer Id);

    @PostMapping(value = "/Patient")
    Integer addPatient(@RequestParam PatientDTO patient);

    @PutMapping(value = "/Patient/{Id}")
    Boolean updatePatient(@RequestParam PatientDTO patient);

}
