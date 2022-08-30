package site.metacoding.ds;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



@Target({ElementType.METHOD})//메서드, 클래스,필드(변수)METHOD위에서만 작동하게 런타임시 실행
@Retention(RetentionPolicy.RUNTIME)//RUNTIME(런타임시)SOURCE(컴파일시)
public @interface RequestMapping {
	String value();//value 메서드 고정, 키값 디폴트다.  
	
}
