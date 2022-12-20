package kr.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.BoardDAO;
import vo.BoardVO;

//github.com/bitcocom/springpro
@Controller
public class BoardController {
	BoardDAO board_dao;
	
	public void setBoard_dao(BoardDAO board_dao) {
		this.board_dao = board_dao;
	}
	
	@RequestMapping("/")
	public String main() {
		return "WEB-INF/views/main.jsp";
	}
	
	//@ResponseBody ->jackson-databind(객체를->JSON데이터 포맷으로 변환)
	@RequestMapping("/myboardList.do")
	public @ResponseBody List<BoardVO> boardList(){
		
		List<BoardVO> list = board_dao.selectList();
		
		return list; //객체를 리턴(객체를 응답함) JSON 데이터 형식으로 변환해서 리턴하겠다.
	}
	
	/*@RequestMapping(value = { "/", "/myboardList.do" })
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
		 
		//조회수 증가를 위한 업데이트 메서드
		 board_dao.update_indate(idx);
		 
		 return "WEB-INF/views/boardView.jsp";
	 }
	 
	 //게시글 삭제
	 @RequestMapping("/boardDel.do")
	 public String delete(int idx) {
		 
		 int res = board_dao.delete(idx);
		 
		 return "redirect:myboardList.do";
	 }
	 
	 //수정하기로 가기위한 매핑. 수정 전 vo
	 @RequestMapping("/boardModifyForm.do")
	 public String modify_form(Model model, int idx) {
		 
		 BoardVO vo = board_dao.selectOne(idx);
		 model.addAttribute("vo", vo);
		 
		 return "WEB-INF/views/boardModify.jsp";
	 }
	 
	 //수정 업데이트 완료.  수정 후 vo임
	 @RequestMapping("/boardModify.do")
	 public String modifyOK(BoardVO vo){
		 
		 int res = board_dao.update(vo);
		 
		 return "redirect:myboardList.do";	 
	 } */
	
}
