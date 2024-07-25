package com.gargouri.TaskManager;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    private long  id;
    private String title ;
    private String description;
    private boolean completed ;
}
