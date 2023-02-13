package kr.or.ddit.book.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.book.dao.bookDAO;


/*
 * 일반적으로 서비스 레이어는 인터페이스와 클래스를 함께 사용합니다,
 * 스프링은 직접 클래스를 생성하는 것을 지양하고 인터페이스를 통해 접근하는 것을 권장하는 프레임워크입니다.
 */
@Service
public class BookServiceImpl implements BookService {
	
	/*
	 * Service 클래스는 비즈니스 클래스가 위치하는 곳입니다.
	 * 스프링 MVC 구조에서 서비스 클래스는 컨트롤러오ㅏ DAO를 연결하는 역할을 합니다.
	 * 
	 * 어노테이션(@) Service는 스프링에 서비스 클래스임을 알려줍니다.
	 * 
	 * 데이터베이스 접근을 위해 bookDao 인스턴스를 주입받습니다.
	 * 클래스의 이름이 Impl로 끝나는 것은 implements의 약자로 관습에 따릅니다 (개발자들 사이에서)
	 * Impl이 붙고 안붙고에 따라 클래스인지 인터페이스인지 구별하기 쉽습니다.
	 */
	@Inject
	bookDAO bookDao;
	
	/**
	 * <p>책 등록</p>
	 * @since SampleSpringYse 1.0
	 * @author ddit_june
	 * @param map 등록할 책 데이터
	 * @return 성공 책 ID, 실패 null
	 */
	@Override
	public String insertBook(Map<String, Object> map) {
		// affectRowCount 변수에는 영향받은 행 수가 담깁니다.
		// insert 구문은 입력이 성공하면 1, 실패하면 0을 리턴합니다.
		int affectRowCount = bookDao.insert(map);
		//  book_id = 34
		if(affectRowCount == 1) {
			// 결과가 성공일시 map 인스턴스에 book 테이블의 pk인 book_id가 담겨있습니다.
			return map.get("book_id").toString();
		}
		return null;
	}

	/*
	 * <p>책 상세보기</p>
	 * @since SampleSpringYse 1.0
	 * @author ddit_young
	 * @param map 책 ID
	 * @return ID에 해당하는 책 정보
	 */
	@Override
	public Map<String, Object> selectBook(Map<String, Object> map) {
		// 서비스 내 selectBook함수는 dao를 호출한 결과를 바로 리턴하는 일만 합니다.
		return bookDao.selectBook(map);
	}

	/*
	 * <p>책 수정</p>
	 * @since SampleSpringYse 1.0
	 * @author ddit_young
	 * @param map 책 ID
	 * @return 성공 1(true), 실패 0(false)
	 */
	@Override
	public boolean updateBook(Map<String, Object> map) {
		// 수정의 경우 입력과는 다르게 pk를 가져오거나 하는 절차가 필요없으므로
		// 행이 정상적으로 영향받았는지만 검사하면 된다.
		int affectRowCount = bookDao.update(map);
		return affectRowCount == 1;
	}

	/*
	 * <p>책 삭제</p>
	 * @since SampleSpringYse
	 * @author ddit_young
	 * @param map 책 ID
	 * @return 성공 1(true), 실패 0(false)
	 */
	@Override
	public boolean deleteBook(Map<String, Object> map) {
		// 삭제의 경우 수정과 동일하게 한개의 행이 정상적으로 영향받았는지만 검사하면 된다.
		int affectRowCount = bookDao.delete(map);
		return affectRowCount == 1;
	}

	/*
	 * <p>책 목록</p>
	 * @since SampleSpringYse
	 * @author ddit_young
	 * @param map 책 키워드
	 * @return 성공 리스트(책정보들), 실패 null
	 */
	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> map) {
		return bookDao.selectList(map);
	}

}
