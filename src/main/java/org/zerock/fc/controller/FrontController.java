package org.zerock.fc.controller;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.zerock.fc.annotations.GetMapping;
import org.zerock.fc.annotations.PostMapping;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;

@WebServlet(name = "FrontController", urlPatterns = {"*.do"})
@MultipartConfig//파일 업로드 처리
public class FrontController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("-------------------------------");

        String path = request.getRequestURI();
        System.out.println(path);

        Reflections reflections = new Reflections("org.zerock.fc.controller.sub", MethodAnnotationsScanner.class);

        Set<Method> getMethods = reflections.getMethodsAnnotatedWith(GetMapping.class);
        Set<Method> postMethods = reflections.getMethodsAnnotatedWith(PostMapping.class);

        Iterator<Method> getIt = getMethods.iterator();

        while(getIt.hasNext()){

            Method method = getIt.next();

            String value = method.getAnnotation(GetMapping.class).value();

            System.out.println(value);

            if(value.equals(path)){
                System.out.println("find method success ");

                Class<?> clz = method.getDeclaringClass();

                System.out.println(clz);

                try {
                    Object obj =clz.getConstructor(null).newInstance(null);
                    System.out.println(obj);

                    String result = (String) method.invoke(obj, request,response);


                    if(result != null){
                        if(result.startsWith("re:")){
                            response.sendRedirect(result.substring(3));
                            break;
                        }else{
                            request.getRequestDispatcher("/WEB-INF/"+result+".jsp").forward(request,response);
                            break;
                        }
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }//end if
        }//while


        Iterator<Method> postIt = postMethods.iterator();

        while(postIt.hasNext()){

            Method method = postIt.next();

            String value = method.getAnnotation(PostMapping.class).value();

            System.out.println(value);

            if(value.equals(path)){
                System.out.println("find method success ");

                Class<?> clz = method.getDeclaringClass();

                System.out.println(clz);

                try {
                    Object obj =clz.getConstructor(null).newInstance(null);
                    System.out.println(obj);

                    String result = (String) method.invoke(obj, request,response);


                    if(result != null){
                        if(result.startsWith("re:")){
                            response.sendRedirect(result.substring(3));
                            break;
                        }else{
                            request.getRequestDispatcher("/WEB-INF/"+result+".jsp").forward(request,response);
                            break;
                        }
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }//end if
        }//while


    }
}


//package org.zerock.fc.controller;
//
//import org.reflections.Reflections;
//import org.reflections.scanners.MethodAnnotationsScanner;
//import org.reflections.scanners.TypeAnnotationsScanner;
//import org.zerock.fc.annotations.Controller;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;
//import java.io.IOException;
//import java.lang.reflect.Constructor;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.Iterator;
//import java.util.Set;
//
//


//
//@WebServlet(name = "FrontController", urlPatterns= "{*.do}")
//public class FrontController extends HttpServlet {
//    @Override
//    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        System.out.println("--------");
//
//        String path = request.getRequestURI();
//        System.out.println(path);
//
//        Reflections reflections = new Reflections("org.zerock.fc.controller.sub", MethodAnnotationsScanner.class);
//
//
//      //  reflections.getMethodsAnnotatedWith(GetMapping.class);이 방식으로 메서드들을 찾을 수 있음.
//        Set<Method> getMethods = reflections.getMethodsAnnotatedWith(GetMapping.class);//겟메서드
//       // Set<Method> postMethods = reflections.getMethodsAnnotatedWith(PostMapping.class);//포스트메서드
//
//        Iterator<Method> it = getMethods.iterator();
//
//        while(it.hasNext()){
//
//            Method method = it.next();
//
//            String value =method.getAnnotation(GetMapping.class).value();
//
//            if (value.equals(path)) {
//                System.out.println("find method success");
//
//                //실행, 실행하려면 객체 필요!
//                //객체를 만들기위해서는 항상 클래스가 필요하다.
//
//                //클래스를 얻어오기
//                Class<?> clz= method.getDeclaringClass();
//                System.out.println(clz);
//
//                try {
//                    //생성자 얻어오기 + 객체만들기
//                    Object obj = clz.getConstructor(null).newInstance(null);
//                    System.out.println(obj);
//
//                    String result =(String) method.invoke(obj,request,response);
//
//                    if(result != null){
//                        if(result.startsWith("re:")){
//                            System.out.println("===========");
//                            response.sendRedirect(result.substring(3));
//                            break;
//                        }else{
//                            request.getRequestDispatcher("/WEB-INF/"+result+".jsp").forward(request,response);
//                            break;
//                        }
//
//                } catch (InstantiationException e) {
//                    e.printStackTrace();
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }catch(InvocationTargetException e){
//                    e.printStackTrace();
//
//            }
//
//    }//end if
//}


//        System.out.println("--------");
//
//        String path = req.getRequestURI();
//        System.out.println(path);
//
//        Reflections reflections = new Reflections("org.zerock.fc.controller.sub", TypeAnnotationsScanner.class);
//
//        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Controller.class);
//        //controller라는 어노테이션이 들어있는 클래스들찾기.
//
//        System.out.println(annotated);
//
//       Iterator<Class<?>> it = annotated.iterator();
//
//       while(it.hasNext()){
//           //System.out.println(it.next());//이 구문을 돌리면 해당 클래스들을 다 찾아서 출력!..
//           Class<?> clz = it.next();//클래스에는 annotation값이 있다.
//
//           Controller annotation = (Controller) clz.getDeclaredAnnotation(Controller.class);
//
//          // System.out.println(annotation.value());
//
//           String annoValue = annotation.value();
//
//           //실행해야하는 컨트롤러를 찾는 과정
//           if(path.startsWith(annoValue)){
//               System.out.println("==================");
//               System.out.println(clz);
//           }//end if
//       }
//
//
//    }
//}
