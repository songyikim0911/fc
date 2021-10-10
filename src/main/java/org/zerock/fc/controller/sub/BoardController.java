package org.zerock.fc.controller.sub;

import lombok.extern.log4j.Log4j2;
import org.zerock.fc.annotations.Controller;
import org.zerock.fc.annotations.GetMapping;
import org.zerock.fc.annotations.PostMapping;
import org.zerock.fc.dto.BoardDTO;
import org.zerock.fc.dto.PageDTO;
import org.zerock.fc.dto.PageMaker;
import org.zerock.fc.service.BoardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Log4j2
@Controller(value = "/board")
public class BoardController {

    private Integer getInt(String str) {
        try {
            int value = Integer.parseInt(str);
            if(value <=0) {
                return null;
            }
            return value;

        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping(value = "/board/register.do")
    public String registerGET(HttpServletRequest request, HttpServletResponse response)throws Exception{

        System.out.println("board register post do........");

        return "board/register";
    }

    @PostMapping(value = "/board/register.do")
    public String registerPost(HttpServletRequest request, HttpServletResponse response)throws Exception{

        System.out.println("board register post....do");

        BoardDTO boardDTO = BoardDTO.builder()
                .title(request.getParameter("title"))
                .content(request.getParameter("content"))
                .writer(request.getParameter("writer"))
                .build();

        Integer bno = BoardService.INSTANCE.register(boardDTO);

        return "re:/board/list.do?bno="+bno;
    }

    @GetMapping(value = "/board/list.do")
    public String list(HttpServletRequest request, HttpServletResponse response)throws Exception{

        System.out.println("---------------------------");


        Integer page = getInt(request.getParameter("page"));
        Integer size = getInt(request.getParameter("size"));

        PageDTO pageDTO = PageDTO.builder().build();

        if (page != null) {pageDTO.setPage(page);}
        if (size != null) {pageDTO.setSize(size);}

        log.info("=========================1");
        log.info(pageDTO);

        List<BoardDTO> dtoList = BoardService.INSTANCE.getList(pageDTO);

        log.info("=========================2");
        log.info(dtoList);

        request.setAttribute("dtoList", dtoList);

        PageMaker pageMaker = new PageMaker(pageDTO.getPage(),pageDTO.getSize(), 123);

        request.setAttribute("pageMaker", pageMaker);

        return "board/list";
    }

    @GetMapping(value = "/board/read.do")
    public String read(HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
        Integer bno = getInt(request.getParameter("bno"));
        Integer page = getInt(request.getParameter("page"));
        Integer size = getInt(request.getParameter("size"));


        PageDTO pageDTO = PageDTO.builder().build();

        if (page != null) {pageDTO.setPage(page);}
        if (size != null) {pageDTO.setSize(size);}

        BoardDTO boardDTO = BoardService.INSTANCE.read(bno);

        request.setAttribute("boardDTO", boardDTO);
        request.setAttribute("pageDTO", pageDTO);

        return "board/read";
    }

    @GetMapping(value = "/board/modify.do")
    public String modifyGET(HttpServletRequest request, HttpServletResponse response) throws RuntimeException {

        Integer bno = getInt(request.getParameter("bno"));
        Integer page = getInt(request.getParameter("page"));
        Integer size = getInt(request.getParameter("size"));

        PageDTO pageDTO = PageDTO.builder().build();;

        if (page != null) { pageDTO.setPage(page); }
        if (page != null) { pageDTO.setSize(size); }

        BoardDTO boardDTO = BoardService.INSTANCE.read(bno);


        request.setAttribute("PageDTO", pageDTO);
        request.setAttribute("boardDTO", boardDTO);



        return "board/modify";
    }


    @PostMapping("/board/modify.do")
    public String modifyPost(HttpServletRequest request, HttpServletResponse response) throws RuntimeException {
        Integer bno = Integer.parseInt(request.getParameter("bno"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        BoardDTO dto = BoardDTO.builder()
                .bno(bno)
                .title(title)
                .content(content)
                .build();

        BoardService.INSTANCE.modify(dto);

        return "re:/board/read.do?bno=" + bno;
    }

    @PostMapping("/board/remove.do")
    public String remove(HttpServletRequest request, HttpServletResponse response) throws RuntimeException {

        Integer bno = Integer.parseInt(request.getParameter("bno"));

        BoardService.INSTANCE.remove(bno);

        return "re:/board/list.do";
    }


}

/*package org.zerock.fc.controller.sub;

import lombok.extern.log4j.Log4j2;
import org.zerock.fc.annotations.Controller;
import org.zerock.fc.annotations.GetMapping;
import org.zerock.fc.annotations.PostMapping;
import org.zerock.fc.dto.BoardDTO;
import org.zerock.fc.dto.PageDTO;
import org.zerock.fc.dto.PageMaker;
import org.zerock.fc.service.BoardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Log4j2
@Controller(value = "/board")
public class BoardController {

    @PostMapping(value="/board/remove.do")
    public String removePost(HttpServletRequest request, HttpServletResponse response) throws Exception{

        Integer bno = Integer.parseInt(request.getParameter("bno"));

        BoardService.INSTANCE.remove(bno);

        return "re:/board/list.do";

    }



    @GetMapping(value="/board/modify.do")
    public String modifyGet(HttpServletRequest request, HttpServletResponse response) throws Exception{
        System.out.println("board modify get.. do");

        Integer bno = Integer.parseInt(request.getParameter("bno"));
        Integer page = Integer.parseInt(request.getParameter("page"));
        Integer size = Integer.parseInt(request.getParameter("size"));

        PageDTO pageDTO = PageDTO.builder().build();

        if(page!=null){pageDTO.setPage(page);}
        if(page!=null){pageDTO.setSize(size);}


        BoardDTO boardDTO = BoardService.INSTANCE.read(bno);

        request.setAttribute("PageDTO", pageDTO);
        request.setAttribute("boardDTO",  boardDTO);

        return "board/modify";

    }

    @PostMapping(value="/board/modify.do")
    public String modifyPost(HttpServletRequest request, HttpServletResponse response) throws Exception{
        System.out.println("board modify post .. do");

        Integer bno = Integer.parseInt(request.getParameter("bno"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");



        BoardDTO dto = BoardDTO.builder()
                .bno(bno)
                .title(title)
                .content(content)
                .build();

        BoardService.INSTANCE.modify(dto);



        return "re:/board/read.do?bno=" + bno;
    }

    @GetMapping(value="/board/read.do")
    public String read(HttpServletRequest request, HttpServletResponse response) throws Exception{
        System.out.println("board read get..do");

        Integer bno = Integer.parseInt(request.getParameter("bno"));
        Integer page = Integer.parseInt(request.getParameter("page"));
        Integer size = Integer.parseInt(request.getParameter("size"));
//스프링을 하면 이런 과정을 거칠 필요 X

        PageDTO pageDTO = PageDTO.builder().build();

        if(page != null){ pageDTO.setPage(page);}
        if(size != null){ pageDTO.setSize(size);}


        BoardDTO boardDTO = BoardService.INSTANCE.read(bno);

        request.setAttribute("boardDTO", boardDTO);
        request.setAttribute("pageDTO", pageDTO);

        return "board/read";

    }

    @GetMapping(value="/board/register.do")
    public String registerGet(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("board register get....do");

        return "board/register";

    }

    @PostMapping(value="/board/register.do")
    public String registerPost(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("board register post....do");


        BoardDTO boardDTO = BoardDTO.builder()
                .title(request.getParameter("title"))
                .content(request.getParameter("content"))
                .writer(request.getParameter("writer"))
                .build();


        Integer bno = BoardService.INSTANCE.register(boardDTO);


        return "re:/board/list.do";
    }

    @GetMapping(value = "/board/list.do")
    public String list(HttpServletRequest request, HttpServletResponse response) throws Exception {


        Integer page = Integer.getInteger(request.getParameter("page"));
        Integer size = Integer.getInteger(request.getParameter("size"));

        PageDTO pageDTO = PageDTO.builder().build();

        if (page != null) {
            pageDTO.setPage(page);
        }
        if (size != null) {
            pageDTO.setSize(size);
        }

        log.info("===================1");
        log.info(pageDTO);

        List<BoardDTO> dtoList = BoardService.INSTANCE.getList(pageDTO);

        log.info("===================2");
        log.info(dtoList);

        request.setAttribute("dtoList", dtoList);

        PageMaker pageMaker = new PageMaker(pageDTO.getPage(), pageDTO.getSize(), 1230);

        request.setAttribute("pageMaker", pageMaker);


        return "board/list";

    }


}

//package org.zerock.fc.controller.sub;
//
//import org.zerock.fc.annotations.Controller;
//
//@Controller(value="/board")
//public class BoardController {
//
//    @PostMapping(value="/board/register.do")
//    public String registerPost(){
//        System.out.println("board register post..do");
//
//        return "re:/board/list.do";
//    }
//
//    @GetMapping(value ="/board/list.do")
//    public String list(){
//        System.out.println("-----------");
//        return "board/list";
//    }
//
//}
*/