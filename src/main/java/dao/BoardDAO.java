package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.BoardVO;

public class BoardDAO {

	SqlSession sqlSession; // 맴퍼접급을 위한 SqlSession 객체 필요

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	// 전체리스트 보기
	public List<BoardVO> selectList() {
		List<BoardVO> list = sqlSession.selectList("b.getLists");
		return list;
	}

	// 새글등록
	public int insert(BoardVO vo) {

		int res = sqlSession.insert("b.boardInsert", vo);
		return res;
	}

	// 게시글 상세보기
	public BoardVO selectOne(int idx) {

		BoardVO vo = sqlSession.selectOne("b.board_one", idx);
		return vo;
	}

	// 게시글 삭제
	public int delete(int idx) {
		System.out.println("D_ idx " + idx);
		int res = sqlSession.delete("b.board_del", idx);
		System.out.println("D_res : " + res);

		return res;
	}

	// 게시글 수정
	public int update(BoardVO vo) {
		int res = sqlSession.update("b.board_update", vo);
		return res;
	}

	// 상세보기 들어갔을 때 조회수 증가
	public int update_count(int idx) {
		int res = sqlSession.update("b.update_count", idx);
		System.out.println("DAO 조회수 " + res);
		return res;

	}


	// 상세보기 들어갔을 때 조회수 증가
	/*
	 * public BoardVO update_indate(BoardVO vo) { int res =
	 * sqlSession.update("b.update_indate", vo); System.out.println("DAO 조회수 "+res);
	 * return vo;
	 * 
	 * 
	 * }
	 */
}
