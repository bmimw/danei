package cn.tedu.alipay.config;

public class AlipayConfig {
	// �̻�appid
	public static String APPID = "2021000122690499";
	// ˽Կ pkcs8��ʽ��
	public static String RSA_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCyKItOAwQcv5YhYwD3dS5Y3ru3Ipm9UsRZspAnHuZilQo3tHCo/ApbS2uNY913Y4Zs259c9wlqukXu7Zu7GfjwRFGOwdodMDhey1MCdWo90p8/k8Wb0Ei7ZtVEqrfgM5q6x6JrRsqRUvs6CM94hdx1PiUdY+MMi3sMeOq4kovjl0zPPTqwW/cwgo57vl6oywQfeYF/fAeLdrDDD6szUaEMDcTZHdyUi9Ka1w7smfPieK2ODxtKFlOZ68eFdIqwA0LCDr2WV2HyEEyqUAeLJW58lfV35bGV8qIaYo3XVrda+tt8KLiEGtBBBYdjAOqzgX0NOCtBW3ABRCUwjjP6HNeXAgMBAAECggEASBUHZ+TogQkzIQcBmR2cRvC3Wn1xXpxiPTh2Ap4J8NEEdU1YV8bCGfYF6gsBzBBEjz6818xLzxbWvLj+TVFQ20WJCg18xlqANDpnt5LyDVlufTa4uTYod0o/96xrARdf/zpOiMD3rJy6cNyntMfUTst6EM4dSzIT8xPpRAxm9QnRXgonMTqPybfMwHCtaAP5Gl9zD/UB0dGyS9lHRviHeKaOxg+AIZmpFOUS0noiTf2QscuITM8/xnLRZxc8wF5VIIX3yueZvwcjY2c2WCvazPDYVnmEmSVWgbijNjzTTJ9sUQ+HSjs5S/AFeCOcExWqY7idbTptGCsXNVsdpdaGgQKBgQDewb4ZQHCJgWEzAPQ8w+a09oaQEt+viKJPp+GPvU9JmkCUAVm+s2uUgvsFnC9x4WyxiGRJAXXKT9L/odkcND9OkI6QcxgwqSbFrlVID3DOzOiDen/Cruc1pB5DtWvOUM758wPjPlfdiUjNufPTUgb4bnCgc6wj3ZAvVSmrI8SeMQKBgQDMvvOrHfR0jjFmDwfxOn67ieAVYi7h93EhI7gFcjn3gWwcNvoBrXAAePalpRi0+plNFoCrMuOwC7QQ5c1dzeBmqzICkm86LiQqPMtYueXy+l5Xxk+LKiT0IwOiO0Az+9FZ06POa4thj15UK5H5dts0KkcqDA33gIAt7SJ36c54RwKBgQCooqeVWlFX59h70kcUEYKAkPlB7OfmbVvjTcbXGIwDvoERCwyz8ASLg7UhhCHu5qdwpz60fiR8Kn4kBJTmYy9h/NHQzTEM9aFimh351aajCEEl+ADsk5h55Wr7+0ZIBKkMVEsnJ7VBA4NYK2RJIJoiTQl6jtdMt5lGHATViNh6oQKBgHHKQlQ8vqq0iCHHgst/jgWed+qKebHeOoKHfnOQa0TyKep4Z3PdN10b3UBsRBH5IJAmTHZZPw+NQZMOlJdonymQRagGSEjWcgUDaqQyFOl4WkyAC581WfpRJ2jXczQw5MX+iJBlVr5u1qcC3AsAEo2oXYqxzmVahmJ/42Mvjy9rAoGBANTP+h1fAulqiNRhmG0r52q0Iwqgm+R6RQa0jMBHQvv65KeS7Oml548T5wcxSxCdf5LjvAJMrkBtFQtHsYZCJnvp84uEOIDybii97nEdwxNBvsP3sSDxe+dkSZlypYQ3GSf0KnBGTR+mezISBKjgTIeHMbgqWQhU27ws0g4CJwCz";
	// �������첽֪ͨҳ��·�� ��http://����https://��ʽ������·�������ܼ�?id=123�����Զ����������������������������
	public static String notify_url = "http://localhost:8080/alipay.trade.wap.pay-JAVA-UTF-8/notify_url.jsp";
	// ҳ����תͬ��֪ͨҳ��·�� ��http://����https://��ʽ������·�������ܼ�?id=123�����Զ���������������������������� �̻������Զ���ͬ����ת��ַ
	public static String return_url = "http://localhost:8080/alipay.trade.wap.pay-JAVA-UTF-8/return_url.jsp";
	// �������ص�ַ
	public static String URL = "https://openapi.alipaydev.com/gateway.do";
	// ����
	public static String CHARSET = "UTF-8";
	// ���ظ�ʽ
	public static String FORMAT = "json";
	// ֧������Կ
	public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsK3owjMrgicBNwD9VMJW+vxTRNLqS5VAc7DFDWWAGthLJJjgaorj9K+c0588nz5gBnk0wCtbw2lfaGKzza7U69fQjwZrQSvxw0ho+eUA8izR0QV751SKSidV8OoYUtPTOGC4WYJQo3T0Gv4N4OxEq1K3kU/fH9VC//oezmmC4Hoqlgd3H7Cn+pF7NkDRCdfSSsdAkYrfgZVpAWF3q+EFufHgb3VQD/u8OS0JQkNAdO/sD+SB0/+CppF49PcgXvljWPAgrAUmvejhLjnAecpS8u3v0WqfVlZUgtSNP/TuzQ9xR//fr2DLp2/3Hjq0wKAthj63o3npVQ9ftLM/m1sANQIDAQAB";
	// ��־��¼Ŀ¼
	public static String log_path = "E:\\\\log";
	// RSA2
	public static String SIGNTYPE = "RSA2";
}