package cn.dao;

import cn.beans.Spend;
import cn.beans.User;

public interface FinanceDao {
	int createNewSpend(Spend spend);
	int getTodo(User user);
}
