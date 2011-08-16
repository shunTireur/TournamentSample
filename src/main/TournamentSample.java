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

		TournamentSample ts = new TournamentSample();

		List<String> playerList = new ArrayList<String>(Arrays.asList(players));

		int itemSize = (playerList.size() - 1) * 2 + 1;
		Object[] gameCell = new String[itemSize];
		for (int i = 0; i < gameCell.length; i++) {
			gameCell[i] = "item" + (i + 1);
		}

		Tree tree = ts.getTournamentTree(gameCell);


		Integer floor = new Integer(0);
		outputTreeItems(tree, 1, floor);


		System.out.println("done.");
	}

	private static void outputTreeItems(Tree tree, int id, Integer floor) {
		for (int i = 0; i < floor; i++)
			System.out.print("->");
		System.out.println("item" + (id));
		if (tree.hasChildren("item" + (id))) {
			Collection<?> children = tree.getChildren("item" + (id));
			floor++;
			for (Object child : children) {
				String str = child.toString();
				str = str.replace("item", "");
				outputTreeItems(tree, Integer.parseInt(str), floor);
			}
		}
		floor--;
	}

	public Tree getTournamentTree(Object[] objs) {
		Tree tree = new Tree();

		for (int i = 0; i < objs.length; i++) {
			tree.addItem(objs[i]);
		}

		int parentId = 0;
		int iCnt = 1;
		int n = 1;
		while (iCnt < objs.length) {
			tree.setParent(objs[iCnt++], objs[parentId]);
			if (iCnt >= objs.length) break;
			tree.setParent(objs[iCnt++], objs[parentId]);
			if (iCnt >= objs.length) break;
			parentId++;

			if (4 <= parentId) {
				parentId = parentId * 2 - 1;
				for (int i = 4 * n + 1; i < parentId; i += 2) {
					tree.setParent(objs[iCnt++], objs[i]);
					if (iCnt >= objs.length) break;
					tree.setParent(objs[iCnt++], objs[i]);
					if (iCnt >= objs.length) break;
				}
				if (iCnt >= objs.length) break;
				for (int i = 4 * n; i < parentId; i += 2) {
					tree.setParent(objs[iCnt++], objs[i]);
					if (iCnt >= objs.length) break;
					tree.setParent(objs[iCnt++], objs[i]);
					if (iCnt >= objs.length) break;
				}
				n++;
			}
		}

		return tree;
	}

}
