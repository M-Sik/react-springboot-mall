package org.zerock.mallapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.mallapi.domain.Todo;
import org.zerock.mallapi.dto.PageRequestDTO;
import org.zerock.mallapi.dto.PageResponseDTO;
import org.zerock.mallapi.dto.TodoDTO;
import org.zerock.mallapi.repository.TodoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@RequiredArgsConstructor // TodoRepository DI를 위해 사용
@Log4j2
public class TodoServiceImpl implements TodoService {

    // RequiredArgsConstructor을 사용하기 위해선 final 사용
    private final ModelMapper modelMapper;
    //TodoRepository
    private final TodoRepository todoRepository;

    @Override
    public Long register(TodoDTO todoDTO) {
        log.info("---register---");

        Todo todo = modelMapper.map(todoDTO, Todo.class);
        Todo savedTodo = todoRepository.save(todo); // save를 하면 db랑 동기화됨.

        return savedTodo.getTno();
    }
    
    @Override
    public TodoDTO get(Long tno) {
        Optional<Todo> result = todoRepository.findById(tno);
        Todo todo = result.orElseThrow();
        TodoDTO dto = modelMapper.map(todo, TodoDTO.class);
        return dto;
    }

    @Override
    public void modify(TodoDTO todoDTO) {
        // Todo 엔티티 조회
        Optional<Todo> result = todoRepository.findById(todoDTO.getTno());
        Todo todo = result.orElseThrow();

        // title, complete, dueDate 변경
        todo.changeTittle(todoDTO.getTitle());
        todo.changeComplete(todoDTO.isComplete());
        todo.changeDueDate(todoDTO.getDueDate());

        //dirty checking
        
    }

    @Override
    public void remove(Long tno) {

    }
    
    @Override
    public PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO) {
        // Pageable 생성
        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() - 1, pageRequestDTO.getSize(),
                Sort.by("tno").descending());

        // todoRepository 호출
        // pageable이 파라미터로 들어가면 무조건 리턴 타입은 Page임.
        Page<Todo> result = todoRepository.findAll(pageable); // findByTitleContaining 사용해야하는거 아닌가?
        List<TodoDTO> dtoList = result.getContent().stream()
            .map(todo -> modelMapper.map(todo, TodoDTO.class))
            .collect(Collectors.toList());
        long totalCount = result.getTotalElements();

        // 결과를 PageResponseDTO로 처리
        PageResponseDTO<TodoDTO> responseDTO = PageResponseDTO.<TodoDTO>withAll()
            .dtoList(dtoList)
            .pageRequestDTO(pageRequestDTO)
            .totalCount(totalCount)
            .build();
        return responseDTO;
    }
    
}
