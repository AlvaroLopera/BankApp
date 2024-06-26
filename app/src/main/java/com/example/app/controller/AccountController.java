package com.example.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.app.dto.AmountRequest;
import com.example.app.dto.FundTransferRequest;
import com.example.app.dto.PinRequest;
import com.example.app.dto.PinUpdateRequest;
import com.example.app.dto.TransactionDTO;
import com.example.app.service.AccountService;
import com.example.app.service.TransactionService;
import com.example.app.util.LoggedinUser;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    
    @Autowired
	private AccountService accountService;

	@Autowired
	private TransactionService transactionService;

	@GetMapping("/pin/check")
	public ResponseEntity<?> checkAccountPIN() {
		boolean isPINValid = accountService.isPinCreated(LoggedinUser.getAccountNumber());

		Map<String, Object> result = new HashMap<>();
		result.put("hasPIN", isPINValid);

		if (isPINValid) {
			result.put("msg", "PIN Created");

		} else {
			result.put("msg", "Pin Not Created");
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/pin/create")
	public ResponseEntity<?> createPIN(@RequestBody PinRequest pinRequest) {
		accountService.createPIN(LoggedinUser.getAccountNumber(), pinRequest.getPassword(), pinRequest.getPin());

		Map<String, String> response = new HashMap<>();
		response.put("msg", "PIN created successfully");

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PostMapping("/pin/update")
	public ResponseEntity<?> updatePIN(@RequestBody PinUpdateRequest pinUpdateRequest) {
		accountService.updatePIN(LoggedinUser.getAccountNumber(), pinUpdateRequest.getOldPin(),
				pinUpdateRequest.getPassword(), pinUpdateRequest.getNewPin());

		Map<String, String> response = new HashMap<>();
		response.put("msg", "PIN updated successfully");

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PostMapping("/deposit")
	public ResponseEntity<?> cashDeposit(@RequestBody AmountRequest amountRequest) {

		if (amountRequest.getAmount() <= 0) {
			Map<String, String> err = new HashMap<>();
			err.put("Error", "Invalid amount");
			return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
		}

		accountService.cashDeposit(LoggedinUser.getAccountNumber(), amountRequest.getPin(), amountRequest.getAmount());

		Map<String, String> response = new HashMap<>();
		response.put("msg", "Cash deposited successfully");

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PostMapping("/withdraw")
	public ResponseEntity<?> cashWithdrawal(@RequestBody AmountRequest amountRequest) {
		if (amountRequest.getAmount() <= 0) {
			Map<String, String> err = new HashMap<>();
			err.put("Error", "Invalid amount");
			return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
		}
		accountService.cashWithdrawal(LoggedinUser.getAccountNumber(), amountRequest.getPin(),
				amountRequest.getAmount());

		Map<String, String> response = new HashMap<>();
		response.put("msg", "Cash withdrawn successfully");

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/fund-transfer")
	public ResponseEntity<?> fundTransfer(@RequestBody FundTransferRequest fundTransferRequest) {
		if (fundTransferRequest.getAmount() <= 0) {
			Map<String, String> err = new HashMap<>();
			err.put("Error", "Invalid amount");
			return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
		}

		accountService.fundTransfer(LoggedinUser.getAccountNumber(), fundTransferRequest.getTargetAccountNumber(),
				fundTransferRequest.getPin(), fundTransferRequest.getAmount());
		Map<String, String> response = new HashMap<>();
		response.put("msg", "Fund transferred successfully");

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/transactions")
	public ResponseEntity<List<TransactionDTO>> getAllTransactionsByAccountNumber() {
		List<TransactionDTO> transactions = transactionService
				.getAllTransactionsByAccountNumber(LoggedinUser.getAccountNumber());
		return ResponseEntity.ok(transactions);
	}

}
