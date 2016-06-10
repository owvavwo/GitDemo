package com.hui.filter;

import com.hui.filter.*;
import com.hui.filter.web.Request;
import com.hui.filter.web.Response;

public class Main {

	public static void main(String[] args) {
		String str = "��Һ�:)��ʲô�����У�����ҵ������ž���ģ�";	
		Request request = new Request();
		request.setRequestStr(str);
		Response response = new Response();
		response.setResponseStr("response");
		
		FilterChain fc = new FilterChain();
		fc.addFilter(new HTMLFilter())
			.addFilter(new SensitiveFilter());		
				
		fc.doFilter(request, response, fc);
		System.out.println(request.requestStr);
		System.out.println(response.responseStr);
	}

}
