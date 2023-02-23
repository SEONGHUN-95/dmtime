package com.example.dongmyung.menu;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Table(name = "menu")
@Getter
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="file_id")
    private Long id;
    private String originFileName;
    private String storeFileName;

    private String savedPath;
    private LocalDateTime createDate;

    @Builder
    public Menu(Long id, String originFileName, String storeFileName, String savedPath, LocalDateTime createDate){
        this.id = id;
        this.originFileName = originFileName;
        this.storeFileName = storeFileName;
        this.savedPath = savedPath;
        this.createDate = createDate;
    }
}
