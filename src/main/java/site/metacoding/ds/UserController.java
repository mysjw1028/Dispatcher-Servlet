package site.metacoding.ds;

public class UserController {

	@RequestMapping("/join")
	public void join() {
		System.out.println("join호출됨");
	}

	@RequestMapping("/login")
	public void login() {
		System.out.println("login호출됨");
	}
	public void joinForm() {
		System.out.println("joinForm호출됨");
	}
}
