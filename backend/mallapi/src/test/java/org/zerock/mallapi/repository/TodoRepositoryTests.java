package org.zerock.mallapi.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mallapi.domain.QTodo;
import org.zerock.mallapi.domain.Todo;
import org.zerock.mallapi.dto.PageRequestDTO;
import org.zerock.mallapi.dto.PageResponseDTO;
import org.zerock.mallapi.dto.TodoDTO;

import com.querydsl.jpa.JPQLQueryFactory;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Transactional //테스트코드 쿼리 롤백을 위해 사용
@Log4j2
public class TodoRepositoryTests {

    @Autowired
    private TodoRepository todoRepository;
    
    @Autowired
    private JPQLQueryFactory queryFactory;

    @Test
    void create() {
        Todo todo = Todo.builder()
                .title("테스트 제목")
                .writer("tester")
                .complete(false)
                .dueDate(LocalDate.of(2026, 5, 12))
                .build();

        Todo saved = todoRepository.save(todo);

        assertThat(saved.getTno()).isNotNull();
        assertThat(saved.getTitle()).isEqualTo("테스트 제목");
        assertThat(saved.getWriter()).isEqualTo("tester");
        assertThat(saved.isComplete()).isFalse();
        log.info("saved: {}", saved);
    }

    @Test
    void read() {
        Todo saved = todoRepository.save(Todo.builder()
                .title("조회 테스트")
                .writer("reader")
                .complete(false)
                .dueDate(LocalDate.now())
                .build());

        Optional<Todo> found = todoRepository.findById(saved.getTno());

        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).isEqualTo("조회 테스트");
        assertThat(found.get().getWriter()).isEqualTo("reader");
    }

    @Test
    void update() {
        Todo saved = todoRepository.save(Todo.builder()
                .title("수정 전")
                .writer("writer")
                .complete(false)
                .dueDate(LocalDate.now())
                .build());

        Todo merged = Todo.builder()
                .tno(saved.getTno())
                .title("수정 후")
                .writer(saved.getWriter())
                .complete(true)
                .dueDate(saved.getDueDate())
                .build();

        Todo updated = todoRepository.save(merged);

        assertThat(updated.getTno()).isEqualTo(saved.getTno());
        assertThat(updated.getTitle()).isEqualTo("수정 후");
        assertThat(updated.isComplete()).isTrue();

        assertThat(todoRepository.findById(saved.getTno()))
                .isPresent()
                .get()
                .extracting(Todo::getTitle, Todo::isComplete)
                .containsExactly("수정 후", true);
    }

    @Test
    void delete() {
        Todo saved = todoRepository.save(Todo.builder()
                .title("삭제 대상")
                .writer("writer")
                .complete(false)
                .dueDate(LocalDate.now())
                .build());

        Long tno = saved.getTno();
        todoRepository.deleteById(tno);

        assertThat(todoRepository.findById(tno)).isEmpty();
        assertThat(todoRepository.existsById(tno)).isFalse();
    }

    @Test
    void testPaging() {
        for (int i = 1; i <= 75; i++) {
            Todo todo = Todo.builder()
                    .title("Title..." + i)
                    .writer("user00")
                    .complete(false)
                    .dueDate(LocalDate.of(2023, 12, 31))
                    .build();
            todoRepository.save(todo);
        }

        //import org.springframework.data.domain.Pageable;
        Pageable pageable = PageRequest.of(0, 10, Sort.by("tno").descending());
        Page<Todo> result = todoRepository.findAll(pageable);

        log.info("total Page => " + result.getTotalPages());
        log.info("total Element => " + result.getTotalElements());
        result.getContent().stream().forEach(todo -> log.info(todo));
    }
    // QTodo를 이용해서 title로 '11'이라는 글자가 있는 데이터 검색
    @Test
    void testSearch2() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("tno").descending());

        //JPQLQueryFactory를 이용해서 검색
        QTodo qTodo = QTodo.todo;

        java.util.List<Todo> list = queryFactory.selectFrom(qTodo).where(qTodo.title.contains("11")).fetch();

        log.info(list);
    }

    @Test
    public void testSearch3() {
        String keyword = "";
        PageRequestDTO requestDTO = PageRequestDTO.builder().build();

        // todo repository에 search
        PageResponseDTO<TodoDTO> responseDTO = todoRepository.search(keyword, requestDTO);

        log.info("todoRepository testSearch3 => " + responseDTO);
    }
}
