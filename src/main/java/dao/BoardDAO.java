package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.BoardVO;

public class BoardDAO {
	
	SqlSession sqlSession; //맴퍼접급을 위한 SqlSession 객체 필요
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public List<BoardVO> selectList(){
		List<BoardVO> list = sqlSession.selectList("b.getLists");
		return list;
	}
	
	 public int insert(BoardVO vo) {
		 System.out.println("d_vo.idx : "+ vo.getIdx());
		 System.out.println("d_vo.title : "+ vo.getTitle());
		 int res = sqlSession.insert("b.boardInsert", vo);
			
			 System.out.println("d1_vo.idx : "+ vo.getIdx());
			  System.out.println("d1_vo.title : "+ vo.getTitle());
			

		 System.out.println("D_res : "+res);
		 return res;
	 }
	
	
}
