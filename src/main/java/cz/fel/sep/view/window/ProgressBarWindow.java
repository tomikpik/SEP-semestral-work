package cz.fel.sep.view.window;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.ProgressBar;
import org.vaadin.viritin.layouts.MHorizontalLayout;

public class ProgressBarWindow extends AbstractWindow {
    private ProgressBar progressBar = new ProgressBar(0.0f);

    public ProgressBarWindow() {
        super("Processing");
        setWidth("20%");
        setHeight("20%");


        progressBar.setSizeFull();
        progressBar.setIndeterminate(true);

        MHorizontalLayout horizontalLayout = new MHorizontalLayout(progressBar);
        horizontalLayout.setMargin(true);
        horizontalLayout.setSpacing(true);
        horizontalLayout.setSizeFull();
        horizontalLayout.alignAll(Alignment.MIDDLE_CENTER);

        setContent(horizontalLayout);
        setClosable(false);
    }
}
