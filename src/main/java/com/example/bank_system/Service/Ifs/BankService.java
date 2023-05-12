package com.example.bank_system.Service.Ifs;

import com.example.bank_system.Vo.BankRequest;
import com.example.bank_system.Vo.BankResponse;

public interface BankService {

	BankResponse getdepositByCardAndPassword(BankRequest request);

}
