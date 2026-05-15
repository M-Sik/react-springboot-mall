package org.zerock.mallapi.repository.search;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.zerock.mallapi.domain.QTodo;
import org.zerock.mallapi.domain.Todo;
import org.zerock.mallapi.dto.PageRequestDTO;
import org.zerock.mallapi.dto.PageResponseDTO;
import org.zerock.mallapi.dto.TodoDTO;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.JPQLQueryFactory;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Repository
@RequiredArgsConstructor
@Log4j2
public class TodoSearchImpl implements TodoSearch{

    private final JPQLQueryFactory queryFactory;
    private final ModelMapper modelMapper;

    @Override
    public PageResponseDTO<TodoDTO> search(String keyword, PageRequestDTO pageRequest) {
        QTodo todo = QTodo.todo;

        JPQLQuery<Todo> query = queryFactory.selectFrom(todo);

        query.where(todo.title.contains(keyword)).orderBy(todo.tno.desc());
        // tno 역순으로 정렬
        query.orderBy(todo.tno.desc());

        // 페이징 처리
        Pageable pageable = PageRequest.of(pageRequest.getPage() - 1, pageRequest.getSize());

        query.offset(pageable.getOffset());
        query.limit(pageable.getPageSize());

        List<Todo> todoList = query.fetch();

        long count = query.fetchCount();

        // todoList를 todoDTO List로 변환 modelMapper사용
        List<TodoDTO> dtoList = todoList.stream().map(todo1 -> modelMapper.map(todo1, TodoDTO.class)).toList();

        return PageResponseDTO.<TodoDTO>withAll().dtoList(dtoList).pageRequestDTO(pageRequest).totalCount(count).build();
    }
}
