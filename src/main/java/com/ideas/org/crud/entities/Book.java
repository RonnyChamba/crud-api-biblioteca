package com.ideas.org.crud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bok_ide")
    private  Integer ide;

    @Column(name = "bok_tit", nullable = false)
    private  String title;

    @Column(name = "bok_des")
    private  String description;

    @Column(name = "bok_isb", nullable = false, unique = true)
    private String isbn;

    @Column(name = "bok_nup")
    private  int pagesNumber;

    @Column(name = "bok_pho")
    private  String photo;

    @Column(name = "bok_pub")
    private Date publicationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_cat")
    private  Category category;

}
