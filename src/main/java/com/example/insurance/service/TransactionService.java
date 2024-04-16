package com.example.insurance.service;

import java.util.List;
 
import com.example.insurance.dto.TransactionDTO;
 
public interface TransactionService {
    TransactionDTO addTransaction(TransactionDTO transactionDTO);
 
    TransactionDTO getTransactionById(int transactionId);
 
    List<TransactionDTO> getAllTransactions();
 
    TransactionDTO updateTransaction(int id,TransactionDTO transactionDTO);
 
}
