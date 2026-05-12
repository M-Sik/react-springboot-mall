package org.zerock.mallapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.mallapi.domain.Todo;

// 인터페이스에 JpaRepository를 extend함
// JpaRepository객체에 첫번째는 엔티티(Todo) 두번째 값에는 해당 엔티티의 pk타입
public interface TodoRepository extends JpaRepository<Todo, Long>{
    
}
