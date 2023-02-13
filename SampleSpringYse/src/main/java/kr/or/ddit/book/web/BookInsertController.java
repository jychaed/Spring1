package kr.or.ddit.book.web;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.book.service.BookService;


/* 
 * @Controller 어노테이션이 있는 클래스는 스프링이 브라우저의 요청(request)을 받아 들이는 컨트롤러하고 인지해서
 * 자바 빈 (Java Bean) 으로 등록해서 관리합니다
 */
@Controller 
@RequestMapping("/book") // 이건 첫번째 관문이고
public class BookInsertController { // 너가 어떤 역할인지 알려줄 필요 있음 @Controller
	
	@Inject
	BookService bookService;
	
	/*
	 * @RequestMapping
	 * - 요청 URL을 어떤 메소드가 처리할 지 여부를 결정
	 * 
	 * method 속성은 http 요청 메소드를 의미합니다.
	 * 일반적인 웹 페이지 개발에서 GET 메소드는 데이터를 변경하지 않는 경우에,
	 * POST메소드는 데이터가 변경될 경우 사용됩니다.
	 * 
	 * ModelAndView는 컨트롤러가 반환할 데이터를 담당하는 모델 (Model1)과 화면을 담당하는 뷰(View)의 경로를 합쳐놓은 객체이다
	 * ModelAndView의 생성자에 문자열 타입 파라미터가 입력되면 뷰의 경로라고 간주합니다.
	 */
	@RequestMapping(value = "/form.do", method = RequestMethod.GET) // 여기로 요청하면 해당 아래 메서드구나 잡아주고 이걸 타고 ! 아래로!
	public ModelAndView bookForm() {
		ModelAndView mav = new ModelAndView();
		return new ModelAndView("book/form"); // 잘 들어오면 여기로 보내줄께 경로! jsp 파일로 가봐! 여기는 화면단이야!
	} // 여기서 겟인지 포스트인지 확인해주고 돌려줘야함  method = RequestMethod.GET 이방식에 해당하는 것만 옴!
	// ModelAndView 
	/* 
	 * 컨트롤러는 최종 뷰를 리턴해요 데이터 처리를 해서 뷰단에서 데이터를 넘겨줘야하죠 ModelAndView 라는 객체를 이용해서
		내가 리턴을 하고자하는 그 페이지로 이동을 해요 
		ModelAndView 하면 addObject 그 해당경로를 쓰는거고 setViewName 뷰의 이름을 넘겨주는 저거 필요없어서 그냥 바로 화면만
		보여주게 넘겨준
		(mav.addObject(attributeName, attributeValue)
		mav.setViewName(viewName);)
		이거 생략
		
	*/
	
	/*
	 * 데이터의 변경이 일어나므로 http메소드는 POST 방식으로 처리
	 * 어노테이션 @RequestParam은 HTTP 파라미터를 map변수에 자동으로 바인딩합니다.
	 * Map타입의 경우는 어노테이션 @RequestParam을 붙여야한 HTTP 파라미터의 값을 map안에 바인딩해줍니다.
	 */
	@RequestMapping(value = "/form.do", method = RequestMethod.POST)
	public ModelAndView insertBook(@RequestParam Map<String, Object> map ) {
		ModelAndView mav = new ModelAndView();
		
		// 서비스 메소드 insertBook을 호출합니다.
		String bookId = bookService.insertBook(map);
		if(bookId == null) {
			// 데이터 입력이 실패할 경우 다시 데이터를 입력받아야 하므로 생성 화면으로 redirect합니다.
			// ModelAndView 객체는 setViewName() 메소드를 통해 뷰의 경로를 지정할 수 있습니다.
			mav.setViewName("redirect:/book/form.do");
		} else {
			// 뷰의 경로가 redirect: 로 시작하면 스프링은 뷰 파일을 찾아가는 것이 아니라 웹페이지의 주소
			//	(/detail.do)를 변경합니다.
			// form.do > detail.do
			// 데이터 입력이 성공하면 상세 페이지로 이동합니다.
			mav.setViewName("redirect:/book/detail.do?bookId=" + bookId);
		}
		return mav;
	}
	

}
