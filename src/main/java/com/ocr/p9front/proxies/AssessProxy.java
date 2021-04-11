package com.ocr.p9front.proxies;

import com.ocr.p9front.domain.PatientRiskDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Proxy for PatientRisk / micro service
 */
@FeignClient(name = "microservice-patient-risk", url = "http://patientrisk:8052")
public interface AssessProxy {

    @RequestMapping(method = RequestMethod.GET, value = "/assess/{Id}")
    PatientRiskDTO getPatientRiskById(@PathVariable("Id") Integer Id);
}
