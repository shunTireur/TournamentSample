package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.vaadin.ui.Tree;

public class TournamentSample {

	public static String[] players = { "Alpha", "Bravo", "Charlie", "Delta",
			"Echo", "Foxtrot", "Golf", "Hotel", "India", "Juliet" };

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> playerList = new ArrayList<String>(Arrays.asList(players));

		int itemSize = (playerList.size() - 1) * 2 + 1;
		System.out.println(itemSize);

		String[] gameCell = new String[itemSize];
		for (int i = 0; i < gameCell.length; i++) {
			gameCell[i] = "item" + (i + 1);
		}

		int itemCnt = 0;
		Tree tree = new Tree();

		Object itemId = gameCell[itemCnt];
		tree.addItem(itemId);
		itemCnt++;

		while (itemCnt < itemSize) {
			// before action
			String item1 = gameCell[itemCnt++];
			tree.addItem(item1);
			tree.setParent(item1, itemId);
			String item2 = gameCell[itemCnt++];
			tree.addItem(item2);
			tree.setParent(item2, itemId);

			// Step1
			Object parentId = tree.getParent(itemId);
			if (parentId == null) {
				itemId = getNextItemId(gameCell, itemId);
				continue;
			}

			// Step2
			Object grandParentId = tree.getParent(parentId);
			if (grandParentId == null) {
				itemId = getNextItemId(gameCell, itemId);
				continue;
			}
			Collection<?> children = tree.getChildren(grandParentId);
			for (Object child : children) {
				if (!child.equals(parentId)) {
					// Step3
					Collection<?> c = tree.getChildren(child);
					if (c == null) {
						itemId = child;
					} else {
						Object obj = getChildId(tree, c);
						if (obj == null) {
							itemId = getNextItemId(gameCell, itemId);
						} else {
							itemId = obj;
						}
					}
					break;
				}
			}
		}

		System.out.println(tree);
	}

	private static Object getNextItemId(String[] gameCell, Object itemId) {
		Object obj = gameCell[0];
		for (int i = 0; i < gameCell.length; i++) {
			if (itemId == gameCell[i]) {
				obj = gameCell[i + 1];
				break;
			}
		}
		return obj;
	}

	public static Object getChildId(Tree tree, Collection<?> children) {
		for (Object child : children) {
			Collection<?> c = tree.getChildren(child);
			if (c == null) {
				return child;
			}
		}
		return null;
	}
}
