package com.lapots.breed.quest.spring;

import com.lapots.breed.quest.component.CharacterCreationForm;
import com.lapots.breed.quest.component.PlayersGrid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.spring.annotation.UIScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a simple label element and a template element.
 */
@HtmlImport("styles/shared-styles.html")
@Route("main")
@UIScope
public class MainView extends VerticalLayout {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainView.class);

    public MainView(PlayerRepository repository, PlayersGrid grid, CharacterCreationForm form) {
        form.addObserver(grid);
        form.setWidth("30%");

        grid.setItems(repository.findAll());

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(form);

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(grid, form);
        verticalLayout.setHeight("100%");
        add(verticalLayout);

        setClassName("main-layout");
    }
}
