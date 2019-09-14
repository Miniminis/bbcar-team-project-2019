package com.ycha.par.dao;


import java.util.List;

import com.ycha.par.domain.Payment;
import com.ycha.par.domain.RDV;

public interface PaymentDao {

	public RDV selectRDVByPidx(int p_idx); 
	public int insertPayRecord(Payment payment);
	public Payment selectOneByRIdx(int r_idx);
	public List<Payment> selectListPassenger(int p_idx);

}
