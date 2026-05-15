package org.zerock.mallapi.service;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.mallapi.dto.PageRequestDTO;
import org.zerock.mallapi.dto.PageResponseDTO;
import org.zerock.mallapi.dto.TodoDTO;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
@Transactional // 테스트코드로 인해 db(쿼리) 수정 및 접근시 롤백 되는 어노테이션
public class TodoServiceTests {

    @Autowired
    private TodoService todoService;

    @Test
    public void testRegister() {
        TodoDTO todoDTO = TodoDTO.builder()
                .title("서비스 테스트")
                .writer("tester")
                .dueDate(LocalDate.of(2025, 12, 31))
                .build();

        Long tno = todoService.register(todoDTO);
        log.info("TNO: " + tno);
    }
    
    @Test
    public void testRead() {
        Long tno = 694L;

        TodoDTO todoDTO = todoService.get(tno);
        log.info(todoDTO);
    }

    @Test
    public void testModify() {
        TodoDTO todoDTO = TodoDTO.builder()
                .tno(694L)
                .title("test update title")
                .complete(true)
                .dueDate(LocalDate.of(2026, 5, 14))
                .build();
        todoService.modify(todoDTO);
    }

    //list() 테스트코드
    @Test
    public void testList() {
        log.info("-----------");
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();
        
        PageResponseDTO<TodoDTO> dto = todoService.list(pageRequestDTO);
        // log.info(dto);
        log.info("pageNumList => " + dto.getPageNumList());
    }
}
