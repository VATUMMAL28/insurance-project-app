package com.example.insurance.service;

import com.example.insurance.dto.TransactionDTO;
import com.example.insurance.entity.Transaction;
import com.example.insurance.exceptions.PaymentsNotFoundException;
import com.example.insurance.exceptions.ResourceNotFoundException;
import com.example.insurance.mapper.TransactionMapper;
import com.example.insurance.repository.PaymentRepository;
import com.example.insurance.repository.PolicyRepository;
import com.example.insurance.repository.TransactionRepository;
import com.example.insurance.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
 
import java.util.List;
import java.util.stream.Collectors;
 
@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService{
 
	private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final UserRepository usersRepository;
    private final PolicyRepository policyRepository;
    private final PaymentRepository paymentRepository;
 
    @Override
    public TransactionDTO addTransaction(TransactionDTO transactionDTO) {
 
        usersRepository.findById(transactionDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + transactionDTO.getUserId()));
        policyRepository.findById(transactionDTO.getPolicyId())
                .orElseThrow(() -> new ResourceNotFoundException("Policy not found with ID: " + transactionDTO.getPolicyId()));
        paymentRepository.findById(transactionDTO.getPaymentId())
                .orElseThrow(() -> new PaymentsNotFoundException("Payment not found with ID: " + transactionDTO.getPaymentId()));
        
        Transaction transaction = transactionMapper.convertFromDTO(transactionDTO);
 
 
        Transaction savedTransaction = transactionRepository.save(transaction);
        return transactionMapper.convertToDTO(savedTransaction);
    }
 
    @Override
    public TransactionDTO getTransactionById(int transactionId) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new PaymentsNotFoundException("Transaction not found with ID: " + transactionId));
        return transactionMapper.convertToDTO(transaction);
    }
 
    @Override
    public List<TransactionDTO> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream()
                .map(transactionMapper::convertToDTO)
                .collect(Collectors.toList());
    }
 
    @Override
    public TransactionDTO updateTransaction(int id,TransactionDTO transactionDTO) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new PaymentsNotFoundException("Transaction not found with ID: " + transactionDTO.getTransactionId()));
 
        transaction.setStatus(transactionDTO.getStatus());
 
        Transaction updatedTransaction = transactionRepository.save(transaction);
        return transactionMapper.convertToDTO(updatedTransaction);
}
}
