package com.example.bank_system.Service.Ifs;

import com.example.bank_system.Entity.Bank;
import com.example.bank_system.Vo.BankRequest;
import com.example.bank_system.Vo.BankResponse;

public interface BankService {

	BankResponse login(BankRequest request);

	BankResponse getDepositByCardAndPassword(BankRequest request);

	BankResponse withdrawByCardAndPassword(BankRequest request);

}
