package com.ocr.p9front.proxies;

import com.ocr.p9front.domain.PatientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Proxy for Patient / micro service
 */
@FeignClient(name = "microservice-patient", url = "http://localhost:8045")
public interface PatientProxy {

    @GetMapping(value = "/Patients")
    List<PatientDTO> getPatients();

}
