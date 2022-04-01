package com.kdomo.Myhome.controller;

import com.kdomo.Myhome.model.Board;
import com.kdomo.Myhome.repository.BoardRepository;
import com.kdomo.Myhome.validator.BoardVaildator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardVaildator boardVaildator;

    @GetMapping("/list")
    public String list(Model model,@PageableDefault(value = 0,size = 5) Pageable pageable,@RequestParam(required = false,defaultValue = "") String searchText){
//        Page<Board> boards = boardRepository.findAllByOrderByIdDesc(pageable);
        Page<Board> boards = boardRepository.findByTitleContainingOrContentContainingOrderByIdDesc(searchText,searchText,pageable);
        int startPage = Math.max(1,boards.getPageable().getPageNumber()-3);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber()+3);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        model.addAttribute("boards",boards);
        return "board/list";
    }

    @GetMapping("/write")
    public String write(Model model, @RequestParam(required = false ) Long id ){ //required는 필수인지 아닌지
        if(id == null){
            model.addAttribute("board",new Board());
        }else{
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board",board);
        }
        return "board/write";
    }

    @PostMapping("/write")
    public String writeSubmit(@Valid Board board, BindingResult bindingResult){ //@ModelAttribute는 일반적으로 파라미터 받아올 때 사용
        boardVaildator.validate(board,bindingResult);
        if(bindingResult.hasErrors()){
            return "board/write";
        }
        boardRepository.save(board);
        return "redirect:/board/list";
    }

}
