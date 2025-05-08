package com.candidateonboardingsystem.controller;

import com.candidateonboardingsystem.utils.dtos.BankInfoDTO;
import com.candidateonboardingsystem.service.BankInfoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bank-info")
public class BankInfoController {

    private final BankInfoService bankInfoService;

    public BankInfoController(BankInfoService bankInfoService) {
        this.bankInfoService = bankInfoService;
    }

    @PostMapping
    public ResponseEntity<BankInfoDTO> createBankInfo(@Valid @RequestBody BankInfoDTO dto) {
        return new ResponseEntity<>(bankInfoService.saveBankInfo(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankInfoDTO> getBankInfo(@PathVariable Long id) {
        return new ResponseEntity<>(bankInfoService.getBankInfoById(id), HttpStatus.OK);
    }
}
