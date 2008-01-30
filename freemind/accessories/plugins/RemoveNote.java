/*FreeMind - A Program for creating and viewing Mindmaps
 *Copyright (C) 2000-2004  Joerg Mueller, Daniel Polansky, Christian Foltin and others.
 *
 *See COPYING for Details
 *
 *This program is free software; you can redistribute it and/or
 *modify it under the terms of the GNU General Public License
 *as published by the Free Software Foundation; either version 2
 *of the License, or (at your option) any later version.
 *
 *This program is distributed in the hope that it will be useful,
 *but WITHOUT ANY WARRANTY; without even the implied warranty of
 *MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *GNU General Public License for more details.
 *
 *You should have received a copy of the GNU General Public License
 *along with this program; if not, write to the Free Software
 *Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 * Created on 06.10.2004
 */
/* $Id: RemoveNote.java,v 1.1.2.3 2008-01-30 20:44:47 christianfoltin Exp $ */

package accessories.plugins;

import java.util.Iterator;

import javax.swing.Action;
import javax.swing.JMenuItem;

import freemind.controller.MenuItemEnabledListener;
import freemind.extensions.HookRegistration;
import freemind.modes.MindMap;
import freemind.modes.MindMapNode;
import freemind.modes.ModeController;
import freemind.modes.mindmapmode.MindMapController;
import freemind.modes.mindmapmode.hooks.MindMapNodeHookAdapter;

/**
 * @author foltin
 * 
 */
public class RemoveNote extends MindMapNodeHookAdapter {
	public RemoveNote() {
		super();
	}

	public void invoke(MindMapNode node) {
		super.invoke(node);
		if (getMindMapController().getSelected() == node) {
			NodeNoteRegistration.getHtmlEditorPanel()
					.setCurrentDocumentContent("");
		}
		getMindMapController().setNoteText(node, null);
	}

	public static class Registration implements HookRegistration,
			MenuItemEnabledListener {

		private final MindMapController controller;

		private final MindMap mMap;

		private final java.util.logging.Logger logger;

		public Registration(ModeController controller, MindMap map) {
			this.controller = (MindMapController) controller;
			mMap = map;
			logger = controller.getFrame().getLogger(this.getClass().getName());
		}

		public boolean isEnabled(JMenuItem pItem, Action pAction) {
			if (controller == null)
				return false;
			boolean foundNote = false;
			for (Iterator iterator = controller.getSelecteds()
					.iterator(); iterator.hasNext();) {
				MindMapNode node = (MindMapNode) iterator.next();
				if (node.getNoteText() != null) {
					foundNote = true;
					break;
				}
			}
			return foundNote;
		}

		public void deRegister() {
		}

		public void register() {
		}
	}
}
