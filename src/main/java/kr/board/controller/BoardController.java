package kr.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.BoardDAO;
import vo.BoardVO;


@Controller
public class BoardController {
	BoardDAO board_dao;
	
	public void setBoard_dao(BoardDAO board_dao) {
		this.board_dao = board_dao;
	}
	 
	@RequestMapping(value = { "/", "/myboardList.do" })
	public String List(Model model) {
		List<BoardVO> list = board_dao.selectList();
		model.addAttribute("list", list);
		return "WEB-INF/views/boardList.jsp"; // /WEB-INF/views/boardList.jsp -> forward
	}	
	
	//글쓰기 버튼 눌렀을 때 글쓰기.jsp 로 이동시킴
	@RequestMapping("/boardForm.do")
	public String boardForm() {
		return "WEB-INF/views/boarForm.jsp";
	}
	
	 @RequestMapping("/boardInsert.do")
	 public String boardInsert(BoardVO vo) {
		 System.out.println("C_vo.idx : "+ vo.getIdx());
		 System.out.println("C_vo.title : "+ vo.getTitle());
		 
		 int res = board_dao.insert(vo);
		 
			
			  System.out.println("C1_vo.idx : "+ vo.getIdx());
			  System.out.println("C1_vo.title : "+ vo.getTitle());
			
		 System.out.println("C_res : "+res);
		 return "redirect:myboardList.do";
	 }
}
