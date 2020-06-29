package com.ocr.poseidon.controllers;

import com.ocr.poseidon.domain.RuleName;
import com.ocr.poseidon.repositories.RuleNameRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * Class RuleNameController for RuleName
 */

@Controller
public class RuleNameController {

    private static final Logger log = LogManager.getLogger(RuleNameController.class);

    @Autowired
    RuleNameRepository ruleNameRepository;

    /**
     * Endpoint for ruleName list
     * @param model
     * @return ruleName list
     */
    @RequestMapping("/ruleName/list")
    public String home(Model model) {
        log.debug("home");
        model.addAttribute("ruleNames", ruleNameRepository.findAll());
        log.info("ruleNames displayed");
        return "ruleName/list";
    }

    /**
     * Endpoint to display RuleName add IHM
     * @param model
     */
    @GetMapping("/ruleName/add")
    public String addRuleForm(Model model) {
        log.debug("addRuleForm");
        RuleName ruleName = new RuleName();
        model.addAttribute("ruleName", ruleName);
        log.info("ruleNames get for add");
        return "ruleName/add";
    }

    /**
     * Endpoint to validate for adding RuleName
     * @param ruleName RuleName object to be added
     * @param result technical result
     * @param model
     */
    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        log.debug("validate");
        if (result.hasErrors()) {
            log.error("errors = " + result.getAllErrors());
            return "ruleName/add";
        }
        ruleNameRepository.save(ruleName);
        log.info("ruleNames validated for add");
        return "redirect:/ruleName/list";
    }

    /**
     * Endpoint to display RuleName updating form
     * @param id RuleName id to be updated
     * @param model
     */
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        log.debug("showUpdateForm");
        RuleName ruleName = ruleNameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
        model.addAttribute("ruleName", ruleName);
        log.info("ruleNames get for update");
        return "ruleName/update";
    }

    /**
     * Endpoint to validate for update
     * @param id is the RuleName id
     * @param ruleName is the RuleName object to be updated
     * @param result technical result
     * @param model
     */
    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                                 BindingResult result, Model model) {
        log.debug("updateRuleName");
        if (result.hasErrors()) {
            log.error("errors = " + result.getAllErrors());
            return "ruleName/update";
        }
        RuleName ruleNameToUpdate = ruleNameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
        ruleNameToUpdate.setName(ruleName.getName());
        ruleNameToUpdate.setDescription(ruleName.getDescription());
        ruleNameToUpdate.setJson(ruleName.getJson());
        ruleNameToUpdate.setTemplate(ruleName.getTemplate());
        ruleNameToUpdate.setSqlPart(ruleName.getSqlPart());
        ruleNameToUpdate.setSqlStr(ruleName.getSqlStr());
        ruleNameRepository.save(ruleNameToUpdate);
        model.addAttribute("ruleNames", ruleNameRepository.findAll());
        log.info("ruleNames updated");
        return "redirect:/ruleName/list";
    }

    /**
     * Endpoint to delete a RuleName object
     * @param id is the RuleName id
     * @param model
     */
    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        log.debug("deleteRuleName");
        RuleName ruleName = ruleNameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
        ruleNameRepository.delete(ruleName);
        model.addAttribute("ruleNames", ruleNameRepository.findAll());
        log.info("ruleNames deleted");
        return "redirect:/ruleName/list";
    }
}
