package com.lapots.breed.quest.spring;

import com.vaadin.flow.component.grid.Grid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.List;

/**
 * The main view contains a simple label element and a template element.
 */
@HtmlImport("styles/shared-styles.html")
@Route("main")
public class MainView extends VerticalLayout {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainView.class);

    public MainView(PlayerRepository repository) {
        List<Player> allPlayers = repository.findAll();
        LOGGER.info("All players: {}", allPlayers);

        Grid<Player> grid = new Grid<>();
        grid.setItems(allPlayers);
        grid.addColumn(Player::getId).setHeader("ID");
        grid.addColumn(Player::getName).setHeader("Name");
        grid.addColumn(Player::getCharacterClass).setHeader("Class");
        grid.addColumn(Player::getLevel).setHeader("Level");

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(grid);
        verticalLayout.setHeight("100%");

        add(verticalLayout);
        setClassName("main-layout");
    }

    /*
        As far as I could get for now the flow of Vaadin Flow (lol) in case of using
        Polymer templates and HTML is this:
            1) create HTML template that would represent component
            2) create Polymer.Element for this HTML component
            3) create PolymerTemplate object with a model that would provide data
            4) wire this template to main view and do whatever

        In case of using plain Java code we can drop all those templates altogether and
        just code everything.

        Well still Vaadin will be slower than Angular, React and co. Not only due to faster
        VM Node.js than JVM, but also due to RPC. But whatever.
     */
}
