package site.metacoding.ds;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class DispacherServlet extends HttpServlet {// 통신을 시작HttpServlet 소켓통신

	@Override // 부모의 메소드를 재정의 했다.
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPocess(req, resp);// 무슨요청이 와도 얘 때림doPocess//이쪽에서 매개변수(파라미터로 넘겨줘도 된다. String method로 넘겨주면 된다.)
	}// http를 때려준다

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPocess(req, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPocess(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPocess(req, resp);
	}

	private void doPocess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doPocess요청됨");
		String httpMethod = req.getMethod();// 이런 요청이 헤더로 들어온다. 자동으로 파싱이 된다.(노가다)
//		System.out.println(httpMethod);
//
		String identifier = req.getRequestURI();// get, join적으면 파싱이됨
//		System.out.println(identifier);

		// 공통로직 처리 디스패쳐가 분배되기전에 컨트롤러로 처리한다.
		// 디스패쳐 서블릿(모든 컨트롤러 메소드를 때림)은 못판다 계속 고쳐야해서 안된다. 라이브러리 오픈소스라서 안슴
		// 디스패쳐 서블릿(모든 컨트롤러 메소드를 때림)
		UserController c = new UserController();// 주소를 파싱해서 컨트롤러를 때려준다
											// c.getClass().를 해야 메모리에 뜬다
		Method[]methodsList = c.getClass().getDeclaredMethods();//클래스 정의된걸 뿌림, 런타임시에 찾음(분석)리플래션 - 런타임시 분석하다. -> 어노테이션 벨루값 찾는게 목적
		for (Method method : methodsList) {//for inch문
			//System.out.println(method.getName());//
		Annotation annotation = //Annotation타입
				method.getDeclaredAnnotation(RequestMapping.class);
		RequestMapping requestMapping = (RequestMapping)annotation;//다운캐스팅을해서 사용해야한다.
		try {
			//System.out.println(requestMapping.value());
			if(identifier.equals(requestMapping.value())) {
				method.invoke(c);//레펄런스 주소 /c안의 메소들를 때린다, 어노테이션은 리플렉션으로 돈다.
			}
		} catch (Exception e) {
			System.out.println(method.getName()+"은 어노테이션이 없습니다.");
		}
		
		}
	}
}
