package A05_Breitensuche;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Breitensuche extends BaseTree<Integer> {

	@Override
	protected int compare(Integer a, Integer b) {
		return a.compareTo(b);
	}

	/**
	 * Liefert Knoten des Baums ausgehend von Start in Reihenfolge der Breitensuche zur�ck
	 * @param start Startknoten f�r Teilbaum
	 * @return Liste der Knoten in Breitenfolge
	 */
	public List<Integer> getBreadthFirstOrder(Node<Integer> start) {

		Queue<Node<Integer>> queueNodes = new LinkedList<>();
		List<Integer> results = new LinkedList<>();
		Node<Integer> node = start;

		while(node !=null){
			results.add(node.getValue());
			if(node.getLeft() !=null){
				 queueNodes.add(node.getLeft());

			}
			if(node.getRight() !=null){
				 queueNodes.add(node.getRight());
			}
			node = queueNodes.poll();
		}

		return results;
	}

	/**
	 * Liefert Knoten des Baums ausgehend von Start in Reihenfolge der Breitensuche zur�ck,
	 * allerdings nur jene Knoten, die in der angegebenen Ebene liegen (Start hat Ebene=1)
	 * @param start Startknoten f�r Teilbaum
	 * @param level Nur Knoten dieser Ebene ausgeben
	 * @return Liste aller Knoten
	 */
	public List<Integer> getBreadthFirstOrderForLevel(Node<Integer> start, int level) {
		ArrayDeque<Node<Integer>> queue = new ArrayDeque<Node<Integer>>();
		List<Integer> result = new ArrayList<Integer>();
		queue.add(start);

		Node<Integer>node;

		int neededLevel = start.getCurrentLevel() + level - 1;

	
		while((node=queue.poll()) !=null)
		{
			if(node.getCurrentLevel() < neededLevel)
			{
				// wenn links ein Element existiert, dieses der Queue hinzuf�gen
				Node<Integer> left = node.getLeft();
				if(left != null)
					queue.add(left);
			}

			if(node.getCurrentLevel() < neededLevel)
			{
	
				Node<Integer> right = node.getRight();
				if(right != null)
					queue.add(right);
			}

	
			if(neededLevel == node.getCurrentLevel())
				result.add(node.getValue());
		}

		return result;

}
