package com.lapots.breed.quest.spring;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "players")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Player {
    @Id
    @GeneratedValue
    @ToString.Exclude
    private int id;

    @NonNull private String name;
    @NonNull private int level;
    @NonNull private String characterClass;
}
