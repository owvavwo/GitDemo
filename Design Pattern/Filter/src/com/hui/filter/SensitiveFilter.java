package com.hui.filter;

public class SensitiveFilter implements Filter {

	@Override
	public String doFilter(String str) {
		String r = str.replace("����ҵ", "��ҵ")
					  .replace("����", "");
		return r;
	}

}
