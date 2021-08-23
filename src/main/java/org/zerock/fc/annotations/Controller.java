package org.zerock.fc.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.TYPE)//클래스 선언부에 이용하는게 Type
//리플렉션동작
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {

    String value();//어노테이션 메서드는 동사로 안만들고 명사로만듬.
}
