package kr.or.ddit.book.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/*
   어노테이션(@) Repository는 데이터에 접근하는 클래스임을 명시한다.
   해당 어노테이션이 있는 클래스는 스프링이 데이터를 관리하는 클래스라고 인지하여
   자바 빈(Java Bean)으로 등록해서 관리한다.
   
   SqlSessionTemplate 객체를 멤버 변수로 선언하는 이유는 mapper.xml을 실행시키기 위해서입니다.
   해당 객체 위에 어노테이션(@) Inject 또는 어노테이션(@) Autowired를 붙여서 sqlSessionTemplate 객체를 사용할 수 있도록 합니다.
   이러한 형태를 '의존성 주입'이라고 합니다. ( 필드 인젝션(Field Injection) )
   
   SqlSessionTemplate 객체는 new 키워드를 통해 직접 생성하지 않고, 의존성 주입(Dependency Injection - DI)을 통해
   주입받습니다. (like 싱글톤)
   스프링은 미리 만들어 놓은 sqlSessionTemplate 타입 객체를 BookDAT 객체에 주입합니다.
   해당 과정은 스프링에서 자동 실행되며 개발자가 직접 sqlSessionTemplate 객체를 생성하는 일 없이 곧바로 사용할 수 있습니다.
   
   SqlSessionTemplate 객체는 root-context.xml에서 정의해 둔 객체이기도 하고, 서버가 시작될 때 스프링은 미리 xml을 읽ㅅ어
   객체를 인스턴스화 해둡니다.

*/
@Repository  //==>데이터를 관장하는 녀석이란 걸 알려줌.
public class bookDAO {
   

   /*
       (SqlSessionTemplate는 root-context에 있음~)
       sqlSessionTemplat은 데이터 베이스에 개별적으로 쿼리를 실행시키는 객체로,
      sqlSessionTemplate을 소스코드에서 사용하여 쿼리를 실행시킵니다..
      매퍼 XML을 실행시키기 위해 SqlSessionTemplate 객체를 멤버 변수로 선언합니다.
      @Inject 어노테이션을 붙여서 SqlSessionTemplate 객체를 사용할 수 있게 합니다.
    */
   @Inject //==>의존성 주입 기능을 가짐. 이걸 쓰면 해당 인스턴스는 싱글톤으로 이용할 수 있음.
//   @Autowired  //==>Inject와 같은 기능을 가짐. 차이 없고 그냥 똑같음.   
   SqlSessionTemplate sqlSessionTemplate;
   
   
   
   /*
    sqlSessionTemplate.insert()
    1) 첫 번째 파라미터는 SQL Mapper의 id입니다.
       book_SQL.xml에서 namespace로 설정된 'Book'과
       insert쿼리를 실행하기 위해 만든 insert문의 id의 값 'insert'입니다.
       mybatis는 네임스페이스 + id  조합으로 쿼리를 찾아서 실행합니다.
    2) 두 번째 파라미터는 쿼리에 전달할 데이터입니다.
       mapper 내 insert 쿼리를 실행하기 위해 전달되어지는 parameterType이 map입니다.
       
       
       외부에서 DAO까지 map에 title, category, price가 담겨져서 넘어옵니다.
       그리고, useGeneratedKeys와 keyProperty의 설정 덕분에 book 테이블의 pk인 book_id의 항목이 생깁니다.
    */
   public int insert(Map<String, Object> map) {
   /*
       sqlSessionTemplate.insert()의 반환값은 쿼리의 영향을 받은 행 수(row count)입니다.
       insert쿼리의 경우 성공하면 1개의 행(row)이 생기므로 1을 리턴하고, 실패하면 0을 리턴합니다.
    */
   // root-context.xml에서 선언했던 sql~Template를 사용해 쿼리를 실행시킴.
   // insert("namespace.id", parameterType)<--book_SQL.xml에서 설정한 것들을 이용해 연결시켜 준다.   
      
      return sqlSessionTemplate.insert("Book.insert", map); // 경로 써주고 넘겨줄 타입 적기 
   }
   
   
   
   public Map<String, Object> selectBook(Map<String, Object> map){
      /*
          sqlSessionTemplate의 selectOne()메서드는 데이터를 한 개만 가져올 때 사용합니다.
          만약 쿼리 결과의 행 수가 0개라면 selectOne()메서드는 null을 반환하게 되고.
          쿼리 결과가 여러 개면 TooManyResultException 예외를 던집니다.
          우리가 작성한 쿼리는 조건이 pk이고, pk는 무조건 행이 유일함을 보장하기 때문에
          결과는 0개 아니면 1개입니다.
       */
      return sqlSessionTemplate.selectOne("Book.selectBook",map);
   }
   
   
   
   public int update(Map<String, Object> map) {
      
      /*
          sqlSessionTemplate객체의 update()메서드는 insert()메서드와 사용법이 동일합니다.
          첫 번째 파라미터는 쿼리ID, 두 번째 파라미터는 쿼리 파라미터이며 반환값은 영향받은 행의 수입니다.
       */
      return sqlSessionTemplate.update("Book.update", map);
   }
   
   
   
   public int delete(Map<String, Object> map) {
      /*
          sqlSessionTemplate객체의 delete메서드는 update메서드와 사용법이 동일합니다.
          첫 번째 파라미터는 쿼리ID, 두 번째 파라미터는 쿼리 파라미터이며 반환값은 영향받은 행의 수입니다.
       */
      return sqlSessionTemplate.delete("Book.delete", map);
   }
   
   
   
   public List<Map<String, Object>> selectList(Map<String , Object> map) {
      // 쿼리 결과를 목록으로 받기 위해서는 SqlSessionTemplate.selectList를 사용할 수 있습니다.
      // 첫 번째 파라미터는 쿼리ID,  두 번째 파라미터는 쿼리 파라미터입니다.
      // 리턴 타입을 List타입으로 설정한 건 selectList()메서드의 결과가 집합 목록을 반환하기 때문입니다.
      return sqlSessionTemplate.selectList("Book.selectBookList", map);
   }
   
   
   
   
   
   
   
   
   
   
   
   
   
}