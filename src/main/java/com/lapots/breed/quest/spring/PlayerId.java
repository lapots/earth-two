package com.lapots.breed.quest.spring;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import java.io.Serializable;

@Data
@Embeddable
@RequiredArgsConstructor
@NoArgsConstructor
public class PlayerId implements Serializable {
    @GeneratedValue
    @ToString.Exclude
    private int id;

    @NonNull
    private String name;
}
