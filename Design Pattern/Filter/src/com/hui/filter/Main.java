package com.hui.filter;

public class Main {

	public static void main(String[] args) {
		String str = "��Һ�:)��ʲô�����У�����ҵ������ž���ģ�";
		MsgProcessor mp = new MsgProcessor();
		mp.setMsg(str);
		
		FilterChain fc = new FilterChain();
		fc.addFilter(new HTMLFilter())
			.addFilter(new SensitiveFilter());
		mp.setFc(fc);	
		
		FilterChain fc2 = new FilterChain();
		fc2.addFilter(new FaceFilter());
		
		fc.addFilter(fc2);
		
		String result = mp.process();
		System.out.println(result);
	}

}
