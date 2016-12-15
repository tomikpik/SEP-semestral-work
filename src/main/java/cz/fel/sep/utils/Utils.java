package cz.fel.sep.utils;

import com.vaadin.ui.Component;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.viritin.label.MLabel;

public class Utils {
    public static Component buildHeader(String caption) {
        MLabel titleLabel = new MLabel(caption);
        titleLabel.setSizeUndefined();
        titleLabel.addStyleName(ValoTheme.LABEL_H1);

        //titleLabel.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        return titleLabel;
    }
}
