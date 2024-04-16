package com.example.insurance.controller;

import com.example.insurance.dto.BeneficiaryDTO;
import com.example.insurance.service.BeneficiaryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
 
@RestController
@RequestMapping("/insurance/beneficiaries")
@AllArgsConstructor
public class BeneficiaryController {
 
    private final BeneficiaryService beneficiaryService;
 
    @PostMapping("/add")
    public ResponseEntity<BeneficiaryDTO> addBeneficiary(@RequestBody BeneficiaryDTO beneficiaryDTO) {
        BeneficiaryDTO addedBeneficiary = beneficiaryService.saveBeneficiary(beneficiaryDTO);
        return new ResponseEntity<BeneficiaryDTO>(addedBeneficiary, HttpStatus.CREATED);
    }
 
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteBeneficiary(@PathVariable("id") int beneficiaryId) {
        boolean deleted=beneficiaryService.deleteBeneficiary(beneficiaryId);
        return new ResponseEntity<Boolean>(deleted,HttpStatus.OK);
    }
 
    @PutMapping("/update/{id}")
    public ResponseEntity<BeneficiaryDTO> updateBeneficiary(@PathVariable int id,@RequestBody BeneficiaryDTO beneficiaryDTO) {
        BeneficiaryDTO updatedBeneficiary = beneficiaryService.updateBeneficiary(id,beneficiaryDTO);
        return new ResponseEntity<BeneficiaryDTO>(updatedBeneficiary,HttpStatus.OK);
    }
 
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BeneficiaryDTO>> getBeneficiariesByUserId(@PathVariable("userId") int userId) {
        List<BeneficiaryDTO> beneficiaries = beneficiaryService.getByUserId(userId);
        return new ResponseEntity<List<BeneficiaryDTO>>(beneficiaries,HttpStatus.OK);
    }
 
    @GetMapping("/policy/{policyId}")
    public ResponseEntity<List<BeneficiaryDTO>> getBeneficiariesByPolicyId(@PathVariable("policyId") int policyId) {
        List<BeneficiaryDTO> beneficiaries = beneficiaryService.getByPolicyId(policyId);
        return new ResponseEntity<List<BeneficiaryDTO>>(beneficiaries,HttpStatus.OK);
    }
}
