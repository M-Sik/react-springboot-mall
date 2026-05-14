package org.zerock.mallapi.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tbl_todo")
@Getter
@ToString
@Builder
@AllArgsConstructor // 빌더 사용시 항상 선언
@NoArgsConstructor // 빌더 사용시 항상 선언
public class Todo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Id 자동부여
    private Long tno;
    private String title;
    private String writer;
    private boolean complete;
    private LocalDate dueDate;
}
