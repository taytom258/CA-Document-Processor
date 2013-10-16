package taytom258.tso.start;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;



public class FolderTree {
	
	public static DefaultTreeModel model;
	public static DefaultMutableTreeNode root;
	
	public static void defaultInit(){
		
		model = (new DefaultTreeModel(root = new DefaultMutableTreeNode("Example Folder"){
			
			private static final long serialVersionUID = -3581457013607325305L;

			{
			DefaultMutableTreeNode node_1;
			add(new DefaultMutableTreeNode("Diagrams"));
			add(new DefaultMutableTreeNode("Miscellaneous"));
			add(new DefaultMutableTreeNode("Reports"));
			node_1 = new DefaultMutableTreeNode("TSOs");
				node_1.add(new DefaultMutableTreeNode("Example TSO"));
			add(node_1);
			add(new DefaultMutableTreeNode("TSRs"));

			}
		}
	));
		
		model.setRoot(root);
		
}
}	


