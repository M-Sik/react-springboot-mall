package org.zerock.mallapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.mallapi.domain.Todo;
import org.zerock.mallapi.repository.search.TodoSearch;

// 인터페이스에 JpaRepository를 extend함
// JpaRepository객체에 첫번째는 엔티티(Todo) 두번째 값에는 해당 엔티티의 pk타입
// TodoSearch를 사용하기위해 TodoRepository에 상속 해줘야함.
public interface TodoRepository extends JpaRepository<Todo, Long>, TodoSearch {
    
    // title로 검색하는 페이징 처리
    Page<Todo> findByTitleContaining(String title, Pageable pageable);
    
}
