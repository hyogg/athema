package com.athema.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.athema.dto.MemberDTO;
import com.athema.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	String dir = "/member/";
	
	@Autowired
	MemberService mservice;
	
	@RequestMapping("/get")
	public String get(Model model) {
		List<MemberDTO> mem_list = null;
		try {
			mem_list = mservice.getall();
		} catch (Exception e) {
			System.out.println("Controller 동작 실패");
			e.printStackTrace();
		}
		model.addAttribute("mem_list", mem_list);
		model.addAttribute("center", dir+"get");
		return "main";
	}

	@RequestMapping("/add")
	public String add(Model model) {
		int todayMem = 0;
		int weekMem = 0;
		int monthMem = 0;
		int totMem = 0;		// 총 누적 가입 회원
		int yesterMem = 0;	// 어제 가입한 회원
		int perday = 0;		// 전일 대비 증감율
		
		int todayWithdraw = 0;		// 오늘 탈퇴한 회원
		int monthWithdraw = 0;		// 당월 탈퇴한 회원
		int totWithdraw = 0;		// 총 탈퇴 회원
		
		try {
			todayMem = mservice.getTodayMember();
			weekMem = mservice.getWeekMember();
			monthMem = mservice.getMonthMember();
			totMem = mservice.getTotMember();
			yesterMem = mservice.getYesterdayMember();
			todayWithdraw = mservice.getTodayWithdraw();
			monthWithdraw = mservice.getMonthWithdraw();
			totWithdraw = mservice.getTotWithdraw();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 증감율 계산
		if (todayMem == yesterMem) {
			perday = 0;
		} else {
			perday = (todayMem - yesterMem)/yesterMem;
		}
		
		model.addAttribute("totWithdraw", totWithdraw);
		model.addAttribute("monthWithdraw", monthWithdraw);
		model.addAttribute("todayWithdraw", todayWithdraw);
		model.addAttribute("perday", perday);
		model.addAttribute("todayMem", todayMem);
		model.addAttribute("weekMem", weekMem);
		model.addAttribute("monthMem", monthMem);
		model.addAttribute("totMem", totMem);
		model.addAttribute("center", dir+"add");
		return "main";
	}

}