package cn.util;

public class Constants {
	public static PropertyUtils wxpu=new PropertyUtils("wx");
	public static String AccessTokenUrl=String.format("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=%s&corpsecret=%s",wxpu.getProp("corpid"),wxpu.getProp("corpsecret"));
    public static String GetDepartmentUsersUrl=String.format("https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token=%s&department_id=%s&fetch_child=FETCH_CHILD&status=0",AccessTokenTool.getAccessToken(),"1");
    public static String GetIdByCode=String.format("https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=%s&code=",AccessTokenTool.getAccessToken());
    public static String SendMessageUrl=String.format("https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=%s", AccessTokenTool.getAccessToken());
/*    public static String UserRole="user";
    public static String SpendCheckRole="spend_check";
    public static String CashierRole="cashier";
    public static String SignatureRole="signature";
    public static String IncomeRole="income";
    public static String IncomeCheckRole="income_check";
    public static String InvoiceRole="invoice";
    public static String ProjectRole="project";*/
}
