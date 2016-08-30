package com.hui.software;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/test", method = RequestMethod.GET)		//�뷽�����ϵ�RequestMapping����ϳ�·��
@SessionAttributes({"arg1","arg2"}) 				//��ע��ὫModelMap��������Ϊ"arg1������arg2���Ĳ����ٴη���session��
public class SecondController {
		
	private static final Logger logger =  LoggerFactory.getLogger(SecondController.class);
	
	
	/*�޲�������,���������Զ���*/
	@RequestMapping
	public String apply(Locale locale, Model model) { 
		logger.info("this is the method of apply");
		return "test";
	}
	
	
	/**
	 * ������������
	 * @param method ����������ʵ��һ��  
	 * @return 
	 */
	@RequestMapping(params="method=method1")
	public String apply1(String method) {
		logger.info("this is the method��" + method);
		return "method1";
	}
	
	@RequestMapping(params="method=method2")
	public String apply2(String method) {
		logger.info("This is the method of :" + method);
		return "method2";
	}
	

	/**
	 * ���ն������
	 * �β�ǰ��RequestParam���Դ����������,����ָ����Ӧ������
	 * @param arg1 ��name��
	 * @param arg2 "age"
	 * @return
	 */
	@RequestMapping("/mutiparams")
	public String apply3(@RequestParam("name") String arg1,@RequestParam("age") String arg2) {
		logger.info("this is method 3:" + arg1 +"|" + arg2);
		return "method3";
	}
	
	@RequestMapping("/request")	
	public String apply4(Model model,HttpServletRequest req, ModelMap map) {
		model.addAttribute("model","This is a value from model!");
		req.setAttribute("request", "This is a value from request!");
		req.getSession().setAttribute("session", "This is a value from seesion!");
		map.addAttribute("modelmap", "This is a value from ModelMap!");
		map.addAttribute("arg1","This is a value to test SessionAttributes!");
		return "test";
	}
	
	
	@RequestMapping(params="method=method3")
	public String apply5(@ModelAttribute("arg1") String value, ModelMap modelMap) {  //ModelAttribute���SessionAttributesʹ�ã��ɽ�ModelMap
		logger.info("apply5 : " + value);											//�е�ֵͨ����ע�⸳ֵ��ָ������
		return "test";
	}
	
	/*�ض���ǰ��*/
	@RequestMapping(params="method=method4")
	public String apply6(ModelMap mm) {
		return "redirect:/";			//ʹ�����·��
		//return "redirect:https://www.baidu.com";
		//return "forward:/";          //ʹ�����·��,����Ĭ��Ϊforward�����Բ�дforward
	}
	
	/*ͨ������ModelAndView���ݶ���*/
	@RequestMapping(params="method=method5")
	public ModelAndView apply7(String method) {
		logger.info("This is apply7");
		ModelAndView mv = new ModelAndView("test");
		User u = new User("�Ը��");
		mv.addObject("user", u);
		
		return mv;
		
	}
	
}
