package main;

import java.util.ArrayList;
import java.util.Arrays;
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

		Tree tree = new Tree();

		String[] gameCell = new String[itemSize];
		for (int i = 0; i < gameCell.length; i++) {
			gameCell[i] = "item" + (i + 1);
			tree.addItem(gameCell[i]);
		}

		int parentId = 0;
		int iCnt = 1;
		int n = 1;
		while (iCnt < gameCell.length) {
			tree.setParent(gameCell[iCnt++], gameCell[parentId]);
			if (iCnt >= gameCell.length) break;
			tree.setParent(gameCell[iCnt++], gameCell[parentId]);
			if (iCnt >= gameCell.length) break;
			parentId++;

			if (4 <= parentId) {
				parentId = parentId * 2 - 1;
				for (int i = 4 * n + 1; i < parentId; i += 2) {
					tree.setParent(gameCell[iCnt++], gameCell[i]);
					if (iCnt >= gameCell.length) break;
					tree.setParent(gameCell[iCnt++], gameCell[i]);
					if (iCnt >= gameCell.length) break;
				}
				if (iCnt >= gameCell.length) break;
				for (int i = 4 * n; i < parentId; i += 2) {
					tree.setParent(gameCell[iCnt++], gameCell[i]);
					if (iCnt >= gameCell.length) break;
					tree.setParent(gameCell[iCnt++], gameCell[i]);
					if (iCnt >= gameCell.length) break;
				}
				n++;
			}
		}

		System.out.println(tree);
	}

}
