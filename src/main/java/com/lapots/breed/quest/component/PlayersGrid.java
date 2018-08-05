package com.lapots.breed.quest.component;

import com.lapots.breed.quest.component.observer.ContentObserver;
import com.lapots.breed.quest.spring.Player;
import com.lapots.breed.quest.spring.PlayerId;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;

@SpringComponent
@UIScope
public class PlayersGrid extends Grid<Player> implements ContentObserver<List<Player>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayersGrid.class);

    @PostConstruct
    public void afterInit() {
        addColumn(Player::getId).setHeader("ID");
        addColumn(Player::getName).setHeader("Name");
        addColumn(Player::getCharacterClass).setHeader("Class");
        addColumn(Player::getLevel).setHeader("Level");
    }

    @Override
    public void refreshContent(List<Player> content) {
        setItems(content);
    }
}
