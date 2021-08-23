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


    @GetMapping("/board/read.do")
    public void read(HttpServletRequest request, HttpServletResponse response) throws Exception{
        System.out.println("board read get..do");


    }

    @PostMapping("/board/register.do")
    public String registerGet(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("board register post....do");

        return "board/register";

    }

    @PostMapping("/board/register.do")
    public String registerPost(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("board register post....do");


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
