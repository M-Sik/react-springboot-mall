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
    private Long tno; // 사용자가 변경 불가
    private String title; // 사용자가 변경 가능
    private String writer; // 사용자가 변경 불가
    private boolean complete; // 사용자가 변경 가능
    private LocalDate dueDate; // 사용자가 변경 가능

    public void changeDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void changeTittle(String title) {
        this.title = title;
    }

    public void changeComplete(Boolean complete) {
        this.complete = complete;
    }
}
