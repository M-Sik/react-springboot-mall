package org.zerock.mallapi.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor // 빌더 사용시 항상 선언
@NoArgsConstructor // 빌더 사용시 항상 선언
public class TodoDTO {
    
    //Todo에 대한 내용
    private Long tno;
    private String title;
    private String writer;
    private boolean complete;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dueDate;
}
