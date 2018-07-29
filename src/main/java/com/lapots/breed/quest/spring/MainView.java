package com.lapots.breed.quest.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a simple label element and a template element.
 */
@HtmlImport("styles/shared-styles.html")
@Route
public class MainView extends VerticalLayout {
    private final CustomerRepository repo;
    final Grid<Customer> grid;

    private final CustomerEditor editor;
    final TextField filter;
    private final Button addNewBtn;

    public MainView(CustomerRepository repo, CustomerEditor editor) {
        this.repo = repo;
        this.grid = new Grid<>(Customer.class);
        this.editor = editor;
        this.addNewBtn = new Button("New customer", VaadinIcon.PLUS.create());
        filter = new TextField();

        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);

        grid.setHeight("300px");
        grid.setColumns("id", "firstName", "lastName");
        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);

        filter.setPlaceholder("Filter by last name");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listCustomers(e.getValue()));

        grid.asSingleSelect().addValueChangeListener(e -> {
            editor.editCustomer(e.getValue());
        });

        addNewBtn.addClickListener(e -> editor.editCustomer(new Customer("", "")));
        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listCustomers(filter.getValue());
        });

        listCustomers(null);

        add(actions, grid, editor);

        setHeight("100%"); // fix
    }

    void listCustomers(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            grid.setItems(repo.findAll());
        } else {
            grid.setItems(repo.findByLastNameStartsWithIgnoreCase(filterText));
        }
    }
}
