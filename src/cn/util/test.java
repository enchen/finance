package cn.util;

import cn.services.impl.WXMessageServiceImpl;

public class test {

	public static void main(String[] args) {
		WXMessageServiceImpl wx=new WXMessageServiceImpl();
		wx.sendTextMessage("小帅哥过来玩呀！", "chenen");
		
	}
}
