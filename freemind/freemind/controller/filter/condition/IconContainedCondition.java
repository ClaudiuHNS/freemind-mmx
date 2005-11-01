/*
 * Created on 05.05.2005
 * Copyright (C) 2005 Dimitri Polivaev
 */
package freemind.controller.filter.condition;

import java.awt.Color;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import freemind.main.Resources;
import freemind.modes.MindIcon;
import freemind.modes.MindMapNode;

public class IconContainedCondition implements Condition {
	private String iconName;
	public IconContainedCondition(String iconName){
		this.iconName = iconName;
	}

	public boolean checkNode(MindMapNode node) {
		List icons = node.getIcons();
		for (ListIterator i=icons.listIterator(); i.hasNext(); ) {
			MindIcon nextIcon = (MindIcon) i.next() ;
			if (iconName.equals(nextIcon.getName())) return true;
		}
		return false;
	}

    /* (non-Javadoc)
     * @see javax.swing.ListCellRenderer#getListCellRendererComponent(javax.swing.JList, java.lang.Object, int, boolean, boolean)
     */
    public JComponent getListCellRendererComponent() {
        JComponent component = new JPanel();
        component.setBackground(Color.WHITE);
        component.add(new JLabel(Resources.getInstance().getResourceString("filter_icon")));
        component.add(new JLabel(Resources.getInstance().getResourceString("filter_contains")));
        component.add(Resources.getInstance().getComponent(getIconName()));
        return component;
    }

    private String getIconName() {
        return iconName;
    }
}
