package kr.or.ddit.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService{

	@Inject
	BoardDAO boardDao;
	
	@Override
	public String insertBoard(Map<String, Object> map) {
		int affectRowCount = boardDao.insert(map);
		
		if(affectRowCount == 1) {
			return map.get("bo_no").toString();
		}
		return null;
	}

	@Override
	public Map<String, Object> selectBoard(Map<String, Object> map) {
		return boardDao.selectBoard(map);
	}

	@Override
	public boolean updateBoard(Map<String, Object> map) {
		int affectRowCount = boardDao.update(map);
		return affectRowCount == 1;
	}

	@Override
	public boolean deleteBoard(Map<String, Object> map) {
		int affectRowCount = boardDao.delete(map);
		return affectRowCount == 1;
	}

	@Override
	public List<Map<String, Object>> selectBoardList(Map<String, Object> map) {
		return boardDao.selectList(map);
	}

	@Override
	public boolean selectMember(Map<String, Object> map) {
		int affectRowCount = boardDao.selectMember(map);
		return affectRowCount == 1;
	}

	@Override
	public int boardHit(int bo_no) {
		return boardDao.boardHit(bo_no);
	}

}
