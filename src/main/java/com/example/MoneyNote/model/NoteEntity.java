package com.example.MoneyNote.model;
import lombok.Data;
import javax.persistence.*;
@Entity
@Table(name = "money")
@Data
public class NoteEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer id;

    @Column(name="date")
     String date;

    @Column(name="name")
     String name;

    @Column(name="pay")
     Integer pay;

    @Column(name="billitem")
     String billitem;

    @Column(name="user_id")
    Integer user_id;

}
