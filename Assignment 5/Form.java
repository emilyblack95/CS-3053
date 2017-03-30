package vaadin_archetype_application.Assignment5;

import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

/** 
 * GameForm class for Assignment 5 - HCI
 * 3/30/17
 * @author Emily Black
 * Game catalog interface.
 */
@SuppressWarnings("serial")
public class Form extends FormLayout {

    private TextField title = new TextField("Title");
    private TextField developer = new TextField("Developer");
    private TextField year = new TextField("Year");
    private NativeSelect<Genre> status = new NativeSelect<>("Genre");
    private Button save = new Button("Save");
    private Button delete = new Button("Delete");

    private Service service = Service.getInstance();
    private Game customer;
    private MyUI myUI;
    private Binder<Game> binder = new Binder<>(Game.class);

    public Form(MyUI myUI) {
        this.myUI = myUI;

        setSizeUndefined();
        status.setEmptySelectionAllowed(false);
        HorizontalLayout elements = new HorizontalLayout(title, developer, year, status);
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        addComponents(elements, buttons);

        status.setItems(Genre.values());
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(KeyCode.ENTER);

        binder.bindInstanceFields(this);

        save.addClickListener(e -> this.save());
        delete.addClickListener(e -> this.delete());
    }

    public void setCustomer(Game customer) {
        this.customer = customer;
        binder.setBean(customer);

        // Show delete button for only customers already in the database
        delete.setVisible(customer.isPersisted());
        setVisible(true);
        title.selectAll();
    }

    private void delete() {
        service.delete(customer);
        myUI.updateList();
        setVisible(false);
    }

    private void save() {
        service.save(customer);
        myUI.updateList();
        setVisible(false);
    }
}
