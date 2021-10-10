package org.zerock.fc.controller.sub;

import org.zerock.fc.annotations.Controller;
import org.zerock.fc.annotations.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller(value = "/member")
public class MemberController {


    @GetMapping("/member/signup.do")
    public String singup(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("member signup.............");
        System.out.println("member signup.............");
        System.out.println("member signup.............");
        System.out.println("member signup.............");
        System.out.println("member signup.............");

        return null;
    }
}
//   중요!!!!🎈🎈✨ get방식 로그인 예시 이제는 extends와 override가 없고
//    메서드를 만들고 어노테이션만 만들어주면된다!
//    @getMapping("/member/login.do")
//    public String login(HttpServletRequest request, HttpServletResponse response){
//        System.out.println("member login...");
//        return null;
//    }

//package org.zerock.fc.controller.sub;
//
//import org.zerock.fc.annotations.Controller;
//
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletResponse;
//
//@Controller(value="/member")
//public class MemberController {
//
//    @GetMapping("/member/signup.do")
//    public void signup(HttpServlet request, HttpServletResponse response){
//        System.out.println("member sign up...");
//        System.out.println("member sign up...");
//        System.out.println("member sign up...");
//        System.out.println("member sign up...");
//        System.out.println("member sign up...");
//
//    }
//}
