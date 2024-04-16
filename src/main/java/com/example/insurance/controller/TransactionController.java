package com.example.insurance.controller;

import com.example.insurance.dto.TransactionDTO;
import com.example.insurance.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
import java.util.List;
 
@RestController
@RequestMapping("/insurance/transactions")
@AllArgsConstructor
public class TransactionController {
 
    private final TransactionService transactionService;
 
    @PostMapping("/add")
    public ResponseEntity<TransactionDTO> addTransaction(@RequestBody TransactionDTO transactionDTO) {
        TransactionDTO addedTransaction = transactionService.addTransaction(transactionDTO);
        return new ResponseEntity<>(addedTransaction, HttpStatus.CREATED);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable("id") int transactionId) {
        TransactionDTO transaction = transactionService.getTransactionById(transactionId);
        return new ResponseEntity<TransactionDTO>(transaction,HttpStatus.OK);
    }
 
    @GetMapping("/all")
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        List<TransactionDTO> transactions = transactionService.getAllTransactions();
        return new ResponseEntity<List<TransactionDTO>>(transactions,HttpStatus.OK);
    }
 
    @PutMapping("/update/{id}")
    public ResponseEntity<TransactionDTO> updateTransaction(@PathVariable int id,@RequestBody TransactionDTO transactionDTO) {
        TransactionDTO updatedTransaction = transactionService.updateTransaction(id,transactionDTO);
        return new ResponseEntity<TransactionDTO>(updatedTransaction,HttpStatus.OK);
    }
 
}