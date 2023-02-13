package kr.or.ddit.web;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardRetrieveController {

	@Inject
	private BoardService boardService;
	
	@RequestMapping(value = "/list.do")
	public ModelAndView list(@RequestParam Map<String, Object> map) {
		List<Map<String, Object>> list = boardService.selectBoardList(map);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardList", list);
		
		if(map.containsKey("keyword")) {
			mav.addObject("keyword",map.get("keyword"));
		}
		
		mav.setViewName("board/list");
		return mav;
	}

	@RequestMapping(value = "/detail.do", method = RequestMethod.GET)
	public ModelAndView detail(@RequestParam Map<String, Object> map) {
		Map<String, Object> detailMap = boardService.selectBoard(map);
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("board", detailMap);
		String bo_no = map.get("bo_no").toString();
		mav.addObject("bo_no",bo_no);
		mav.setViewName("board/detail");
		return mav;
	}
	
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public ModelAndView selectMember() {
//		ModelAndView mav = new ModelAndView();
//		mav.addObject(attributeName, attributeValue);
//		mav.setViewName(viewName);
		return new ModelAndView("board/login");
	}
	
	
	@RequestMapping(value="/login.do", method = RequestMethod.POST)
	public ModelAndView selectMember(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		
		boolean isLoginSuccess = boardService.selectMember(map);
		
		if(isLoginSuccess) {
			mav.setViewName("redirect:/board/list.do");
		}else {
			mav.setViewName("redirect:/board/login.do");
		}
		return mav;
	}
	
}
