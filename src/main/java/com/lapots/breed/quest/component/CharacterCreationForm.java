package com.lapots.breed.quest.component;

import com.lapots.breed.quest.component.observer.ContentObserver;
import com.lapots.breed.quest.spring.Player;
import com.lapots.breed.quest.spring.PlayerRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringComponent
@UIScope
public class CharacterCreationForm extends FormLayout {
    private static final Logger LOGGER = LoggerFactory.getLogger(CharacterCreationForm.class);

    private List<ContentObserver<?>> boundObservers = new ArrayList<>();

    private TextField name;
    private TextField characterClass;
    private TextField level;

    @Autowired
    private PlayerRepository repository;

    @PostConstruct
    public void create() {
        name = new TextField();
        characterClass = new TextField();
        level = new TextField();

        addFormItem(name, "Name");
        addFormItem(characterClass, "Class");
        addFormItem(level, "Level");

        Binder<Player> binder = new Binder<>(Player.class);

        binder
            .forField(level)
            .withConverter(new StringToIntegerConverter(0, ""))
            .bind(Player::getLevel, Player::setLevel);

        binder.bindInstanceFields(this);

        Button button = new Button("Save");
        button.addClickListener(buttonClickEvent -> {
            try {
                Player newPlayer = new Player();
                binder.writeBean(newPlayer);
                LOGGER.info("Attempt to write object: {}", newPlayer);

                repository.save(newPlayer);

                LOGGER.info("Notifying observers...");
                boundObservers.forEach(contentObserver -> {
                    if (contentObserver instanceof PlayersGrid) {
                        ((PlayersGrid) contentObserver).refreshContent(repository.findAll());
                    }
                });
            } catch (ValidationException e) {
                e.printStackTrace();
            }
        });

        add(button);
    }

    public void addObserver(ContentObserver observer) {
        boundObservers.add(observer);
    }

}
