package cn.services;

import cn.beans.Spend;

public interface FinanceService {
	public int createNewSpend(Spend spend,String token);
	public int getTodo(String role,String token);

}
