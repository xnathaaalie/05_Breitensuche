package A05_Breitensuche;

public abstract class BaseTree<Type> {

   /**
    * Wurzel des Baums (Startknoten)
    */
   protected Node<Type> root;

   /**
    * Wurzel auslesen
    * @return
    */
   public Node<Type> getRoot() {
      return root;
   }

   /** 
    * Methode zum Vergleich zweier Elemente f�r die innere Ordnung des Baums
    * @param a Erstes Element
    * @param b Zweites Element
    * @return <0, wenn a<b | >0, wenn a>b | 0, wenn a=b
    */
   protected abstract int compare(Type a, Type b);

   /**
    * Neues Element hinzuf�gen
    * @param elem Hinzuzuf�gendes Element
    */
   public void add(Type elem) {
      Node<Type> neu = new Node<Type>(elem);
      if (root == null) {          // Fall 1: Baum ist leer
         root = neu;
         
         // Root Level = 1
         root.currentLevel = 1;
         return;
      }
      Node<Type> node = root;             // Fall 2: Baum ist nicht leer
      while (true) {
         int vgl = compare(elem, node.getValue());
         if (vgl < 0) {             // kleiner
            
            // wenn links kein Element, ein neues einf�gen, mit h�herem Level
            if (node.getLeft() == null) {
               node.setLeft(neu);
               neu.setParent(node);
               
               // Level um 1 erh�ht bei neuem Node setzen
               neu.currentLevel = node.currentLevel + 1;
               return;
            }
            
            // aktuelles node auf linkes Element setzen
            node = node.getLeft();
         }
         else if (vgl > 0) {             // gr��er
            
            // wenn rechts kein Element, ein neues einf�gen, mit h�herem Level
            if (node.getRight() == null) {
               node.setRight(neu);
               neu.setParent(node);
               
               // Level um 1 erh�ht bei neuem Node setzen
               neu.currentLevel = node.currentLevel + 1;
               return;
            }
            
            // aktuelles node auf rechtes Element setzen
            node = node.getRight();
         }
         else {                   // gleich (nicht nochmal einf�gen)
            
            // abbrechen. Element wurde schon eingef�gt
            return;
         }
      }
   }

   /**
    * Element im Baum finden (startet bei Root-Node)
    * @param needle Zu suchendes Element
    * @return Knoten des Elements
    */
   public Node<Type> find(Type needle) {
      return find(root, needle);
   }
   
   /**
    * Element in Teilbaum finden (startet bei current-Node)
    * @param current Startknoten
    * @param needle  Zu suchendes Element
    * @return Knoten des Elements
    */
   public Node<Type> find(Node<Type> current, Type needle) {
      if (current == null) {
         return null;
      }
      int vgl = compare(needle, current.getValue());
      if (vgl == 0) {       // Gefunden
         return current;
      }
      else if (vgl < 0) {    // Gesuchtes Element ist links
         return find(current.getLeft(), needle);
      }
      else {          // Gesuchtes Element ist rechts
         return find(current.getRight(), needle);
      }
   }
   
   /**
    * Funktion zur Ausgabe des gesamten Baums.
    */
   public void printTree() {
      printTree(root, "");
   }
   
   /**
    * Funktion zur Ausgabe des Baums unterhalb eines Knotens
    * @param current Knoten, dessen Teilbaum ausgegeben werden soll
    * @param prefix  Zur Einr�ckung
    */
   public void printTree(Node<Type> current, String prefix) {
      if (current == null) {
         return;
      }
      System.out.println(prefix + current.getValue());
      printTree(current.getLeft(), prefix + " L ");
      printTree(current.getRight(), prefix + " R ");
   }

}
 
