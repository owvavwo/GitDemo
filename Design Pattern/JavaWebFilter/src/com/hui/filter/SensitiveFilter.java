package com.hui.filter;

import com.hui.filter.web.Request;
import com.hui.filter.web.Response;

public class SensitiveFilter implements Filter {

	@Override
	public void doFilter(Request request, Response response, FilterChain chain) {
		request.requestStr = request.requestStr.replace("����ҵ", "��ҵ")
				  .replace("����", "") + "-->SensitiveFilter";
		chain.doFilter(request, response, chain);		
		response.responseStr += "-->SensitiveFilter";
	}

	 

}
