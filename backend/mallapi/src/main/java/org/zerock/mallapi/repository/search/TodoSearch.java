package org.zerock.mallapi.repository.search;

import org.zerock.mallapi.dto.PageRequestDTO;
import org.zerock.mallapi.dto.PageResponseDTO;
import org.zerock.mallapi.dto.TodoDTO;

// TodoSearch를 사용하기위해 TodoRepository에 상속 해줘야함.
public interface TodoSearch {
    
    PageResponseDTO<TodoDTO> search(String keyword, PageRequestDTO pageRequestDTO);
}
