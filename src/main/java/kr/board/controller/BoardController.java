package kr.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.BoardDAO;
import vo.BoardVO;

//github.com/bitcocom/springpro
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
	
	//새글 등록
	 @RequestMapping("/boardInsert.do")
	 public String boardInsert(BoardVO vo) {

		 int res = board_dao.insert(vo);
			
		 System.out.println("C_res : "+res);
		 return "redirect:myboardList.do";
	 }
	 
	 //게시글 상세보기
	 @GetMapping("/view.do")
	 public String view(Model model, int idx) {
		 
		 BoardVO vo = board_dao.selectOne(idx);  //해당되는idx 의 모든 정보를 vo로 담는다.
		 model.addAttribute("vo", vo);
		 return "WEB-INF/views/boardView.jsp";
	 }
	 
	 //게시글 삭제
	 @RequestMapping("/boardDel.do")
	 public String delete(int idx) {
		 
		 int res = board_dao.delete(idx);
		 
		 return "redirect:myboardList.do";
	 }
	 
}
