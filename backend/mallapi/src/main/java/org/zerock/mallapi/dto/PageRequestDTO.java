package org.zerock.mallapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder // page request dto를 상속해서 또다른 dto를 만들경우를 위해사용. ex) 모든 페이징이 페이지 번호랑 사이즈는 필요함.
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {
    
    @Builder.Default // Builder.Default 생성자를 안쓰고 빌더를 사용하면 비어있는 생성자를 호출하게 되어서 기본값 설정
    private int page = 1;

    @Builder.Default
    private int size = 10;
}
