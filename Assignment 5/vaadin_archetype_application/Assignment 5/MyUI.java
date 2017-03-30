package vaadin_archetype_application.Assignment5;

import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/** 
 * MyUI for Assignment 5 - HCI
 * 3/30/17
 * @author Emily Black
 * Game catalog interface.
 */
@SuppressWarnings("serial")
@Theme("mytheme")
public class MyUI extends UI {
    
    private Service service = Service.getInstance();
    private Grid<Game> grid = new Grid<>(Game.class);
    private TextField filterText = new TextField();
    private Form form = new Form(MyUI.this);
    private Label websiteTitle = new Label("Emily's Video Game Catalog");

    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	getPage().setTitle("Emily's Video Game Catalog");
        final VerticalLayout layout = new VerticalLayout();
        
        websiteTitle.addStyleName("h1");
        layout.addComponent(websiteTitle);

        filterText.setPlaceholder("Filter");
        filterText.addValueChangeListener(e -> updateList());
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button clearFilterTextBtn = new Button(VaadinIcons.CLOSE);
        clearFilterTextBtn.setDescription("Clear Filter");
        clearFilterTextBtn.addClickListener(e -> filterText.clear());

        CssLayout filtering = new CssLayout();
        filtering.addComponents(filterText, clearFilterTextBtn);
        filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        Button addGameBtn = new Button("Add Game");
        addGameBtn.addClickListener(e -> {
            grid.asSingleSelect().clear();
            form.setCustomer(new Game());
        });

        HorizontalLayout toolbar = new HorizontalLayout(filtering, addGameBtn);

        grid.setColumns("title", "developer", "year");

        grid.setSizeFull();

        layout.addComponents(toolbar, grid, form);

        updateList();

        setContent(layout);

        form.setVisible(false);

        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() == null) {
                form.setVisible(false);
            } else {
                form.setCustomer(event.getValue());
            }
        });
    }

    public void updateList() {
        List<Game> games = service.findAll(filterText.getValue());
        grid.setItems(games);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}