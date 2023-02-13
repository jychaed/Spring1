package kr.or.ddit.board;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {

	@Inject
	SqlSessionTemplate sqlSessionTemplate;
	
	public int insert(Map<String, Object> map) {
		return sqlSessionTemplate.insert("Board.insert", map);
	}
	
	public Map<String, Object> selectBoard(Map<String, Object> map){
		return sqlSessionTemplate.selectOne("Board.selectBoard", map);
	}
	
	public int update(Map<String, Object>map) {
		return sqlSessionTemplate.update("Board.update", map);
	}
	
	public int delete(Map<String, Object> map) {
		return sqlSessionTemplate.delete("Board.delete", map);
	}
	
	public List<Map<String, Object>> selectList(Map<String, Object> map){
		return sqlSessionTemplate.selectList("Board.selectBoardList", map);
	}

	public int selectMember(Map<String, Object> map){
		return sqlSessionTemplate.selectOne("Board.selectMember", map);
	}
	
	public int boardHit(int bo_no) {
		return sqlSessionTemplate.update("Board.boardHit", bo_no);
	}
	
}
