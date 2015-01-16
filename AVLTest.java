package project4;

public class AVLTest {

	public static void main(String[] args) {
		AVLTree<Integer> test1 = new AVLTree<Integer>();
		test1.add(3);
		test1.add(5);
		test1.add(4);
		test1.add(8);
		test1.add(10);
		
		
		System.out.println(test1.size());
		
	//	test1.printTree();
		
	}

}
