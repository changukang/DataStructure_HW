import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.lang.Integer;
import java.lang.Double;

class Node {
  public int val;
  public Node left, right, p;
  public int color=0;
  /////
  /// color RED is assigned to integer 1
  /// color BLACK is assigned to integer 0
  /////
  public Node(int newval) {
    val = newval;
    left = null;
    right = null;
    p = null;
  }
}

class RB{
<<<<<<< HEAD
	public int[] none_found = new int[1000]; //없느 노드를 기록하기 위함
	public int index = 0;
	public Node root;
=======
	public Node root = null;
	//나중에 RB.nill 추가 예정
>>>>>>> parent of 5352e3d... changed up the code
	public RB(){
		root = null;
	}

	public void Left_Rotate(Node x){
		Node y = x.right;
		x.right = y.left;

		if(y.left!=null){
			y.left.p = x;
		}
		y.p = x.p;
		if(x.p == null){
			this.root = y;
		}
		else if(x==x.p.left){
			x.p.left =y;
		}
		else{
			x.p.right = y;
		}
		y.left = x;
		x.p = y;
	}

	public void Right_Rotate(Node x){
		Node y = x.left;
		x.left = y.right;
		if(y.right!=null){
			y.right.p = x;
		}
		y.p = x.p;
		if(x.p == null){
			this.root = y;
		}
		else if( x==x.p.right ){
			x.p.right =y;
		}
		else{

			x.p.left = y;
		}
		y.right = x;
		x.p =y;
	}

	public void RB_INSERT_FIXUP(Node z){
		Node y = null;
		if(z.p == null){ 	//
			this.root = z;	//
			z.color = 0;	//
			return;			//
		}
		else if(z.p != null){
			while( z.p != null && z.p.color == 1){
				//following code is for left
				if(z.p.p == null){
					break; //this is the case when z.p is root
				}
				if(z.p == z.p.p.left){
					y = z.p.p.right; //y 는 삼촌
					if(y!= null && y.color == 1){ //CASE 1
						z.p.color = 0;
						y.color = 0;
						z.p.p.color = 1;
						z = z.p.p;
						if(z == this.root){
							z.color = 0;//by the rule of RB, root node must be BLACK which is integer 0
						}
					}
					else
					{
					 if (z==z.p.right){ //CASE 2
						z = z.p;
						Left_Rotate(z);
					}
					z.p.color = 0;//CASE 3
					z.p.p.color = 1;
					Right_Rotate(z.p.p);
					}
					
				}
				////Following code is for right
				else{
					y = z.p.p.left; //y 는 삼촌
					if(y != null && y.color == 1){ //CASE 1
						z.p.color = 0;
						y.color = 0;
						z.p.p.color = 1;
						z = z.p.p;
						if(z == this.root){
							z.color = 0; //by the rule of RB, root node must be BLACK which is integer 0
						}
					}
					else {
					if (z==z.p.left){ //CASE 2
						z = z.p;
						Right_Rotate(z);

					}
					z.p.color = 0; //CASE 3
					z.p.p.color = 1;
					Left_Rotate(z.p.p);
				}
			}

			this.root.color = 0; //To ensure the root node of Tree is black
			}
		}
	}


	public void RB_insert(Node z){
	Node y = null;
	Node x = this.root;

    while (x!= null){
      y = x;
      if (z.val < x.val){
        x = x.left;
      }
      else{
        x = x.right;
      }
    }

    z.p = y;
    if (y == null){
      this.root = z;
    }
    else if (z.val < y.val){
      y.left = z;
    }
    else{
      y.right=z;
    }
	z.left = null;
	z.right = null;
	z.color = 1;

	RB_INSERT_FIXUP(z);

	}

    public void tree_print(Node tree, int level) {

	    if (tree.right != null)
	      tree_print(tree.right, level + 1);

	    for(int i = 0; i < level; i++)
	      System.out.print("        ");

	    System.out.println(tree.val+""+tree.color);
	    if (tree.left != null)
	      tree_print(tree.left, level + 1);
    }

   public void RB_DELETE_FIXUP(Node x){
  	Node w =  null;
  	while(x!=this.root && x.color == 0){
  		if(x==x.p.left){
  			w = x.p.right;
  			if(w.color == 1){
  				w.color = 0;
  				x.p.color = 1;
  				Left_Rotate(x.p);
  				w=x.p.right;
  			}
  			if(w.left.color == 0 && w.right.color ==0){
  				w.color =1;
  				x=x.p;
  			}
  			else{
  				if(w.right.color == 0){
  					w.left.color = 0;
  					w.color = 1;
  					Right_Rotate(w);
  					w = x.p.right;

  				}
  				w.color = x.p.color;
  				x.p.color = 0;
  				w.right.color = 0;
  				Left_Rotate(x.p);
  				x = this.root;
  			}
  		}
  		else{
  			w = x.p.left;
  			if(w.color == 1){
  				w.color = 0;
  				x.p.color = 1;
  				Right_Rotate(x.p);
  				w=x.p.left;
  			}
  			if(w.right.color == 0 && w.left.color ==0){
  				w.color =1;
  				x=x.p;
  			}
  			else{
  				if(w.left.color == 0){
  					w.right.color = 0;
  					w.color = 1;
  					Left_Rotate(w);
  					w = x.p.left;

  					}
  				w.color = x.p.color;
  				x.p.color = 0;
  				w.left.color = 0;
  				Right_Rotate(x.p);
  				x = this.root;
  			}
  		}

  		if(x!=null)
  			x.color = 0;
		}
  	}

	public Node tree_min(Node n){
	    while(n.left != null){
	      n = n.left;
	    }
	    return n;
	  }

	public void RB_Transplant(Node u, Node v){
	  	if(u.p==null){
	  		v.color =0;
	  		this.root = v;
	  	}
	  	else if(u == u.p.left){
	  		u.p.left = v;
	  	}
	  	else{
	  		u.p.right = v;
	  	}
	  	if(u.right ==null && u.left == null){
	  		return;
	  	}
	  	v.p = u.p;
	 }


	public void RB_delete(Node z){
	 	Node y = z;
 	 	int y_org_col = y.color;
 	 	Node x = null;
	 	//checker
	 	// System.out.println(z.left);
	 	if(z.left==null){
	 		x = z.right;
	 		RB_Transplant(z,z.right);
	 	}
	 	else if(z.right == null){
	 		x = z.left;
	 		RB_Transplant(z, z.left);
	 	}
	 	else{
	 		y = tree_min(z.right);
	 		y_org_col = y.color;
	 		x = y.right;
	 		if(y.p == z){
	 			if(x==null){
	 				//do nothing
	 			}
	 			else x.p=y;
	 		}
	 		else{
	 			RB_Transplant(y,y.right);
	 			y.right = z.right;
	 			y.right.p =y;
	 		}
	 		RB_Transplant(z,y);
	 		y.left = z.left;
	 		z.left.p = y;
	 		y.color = z.color;
	 	}

	 	if(y_org_col == 0){
	 		if(x!=null){
	 		RB_DELETE_FIXUP(x);
	 	}
	 	}

	 }

	 public Node tree_search(Node tree, int val) {
	    if (tree == null)
	      return null;
	    else if (val == tree.val)
	      return tree;
	    else if (val < tree.val)
	      return tree_search(tree.left,val);
	    else
	      return tree_search(tree.right,val);
  	}


  	public void inorder(Node tree) {
    	if (tree == null)
      		return;
   		else {
	      inorder(tree.left);
	      System.out.println(tree.val);
	      inorder(tree.right);
   		 }
 	 }

 	 public int NodeCount(Node tree){
 	 	int c = 1;
 	 	if( tree.right!=null) { c+= NodeCount(tree.right); }
 	 	if( tree.left!=null) { c+= NodeCount(tree.left); }
 	 	return c;
  	}

	public int BlackNodeCount(Node tree){	
		int c = 0;
		if(tree.left!=null) c+=BlackNodeCount(tree.left);
		if(tree.right!=null) c+=BlackNodeCount(tree.right);
		if(tree.color == 0) c+=1;
		return c;
	}


	public int BlackHeight(Node tree){
		if(tree==null){
			return 0;
		}
		else if(tree.color==0){
			return BlackHeight(tree.left) + 1;
		}
		else
			return BlackHeight(tree.left);
	}

}


public class HW04 {
    public static void main(String[] args) throws IOException {
    	 RB temp = new RB();

    	 //Getting input from txt file
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        while(true) {
            String line = br.readLine();
            if (line==null) break;
            // int num = Integer.parseInt(line);
            double d = Double.parseDouble(line);
            int num = (int)d;
            if(num > 0){
            	temp.RB_insert(new Node(num));
            }
            else if(num<0){
<<<<<<< HEAD
            	Node del;
            	del = temp.tree_search(temp.root, num *= -1);
<<<<<<< HEAD
=======
            	if(index == 1){
            		index += 1;
           		 }
>>>>>>> origin/master
            	if(del.val != 0){
           			temp.RB_delete(del);
           		}
           		else{
           			num*=-1;
           			temp.none_found[temp.index] = num;
           			temp.index += 1;
           		}
           	}
=======
            	Node del = null;
            	del = temp.tree_search(temp.root,num *= -1);
            	if(del != null){
           		temp.RB_delete(del);
           		 }
           		}
>>>>>>> parent of 5352e3d... changed up the code
            else if(num==0){
            	break;
            }

        }
        br.close();


        //instruction of ds13b.pdf : to show 1) num of node, 2) num of black node )black height 4) inorder traversal
        System.out.println("total = " + temp.NodeCount(temp.root));
        System.out.println("nb = " + temp.BlackNodeCount(temp.root));
        System.out.println("bh = " + temp.BlackHeight(temp.root));

        temp.inorder(temp.root);

    //Generated following code to check if alg works well in randomly status
	// Random random = new Random();
	// RB wh = new RB();
	// for(int i=0 ; i<15; i++){
	// 	wh.RB_insert(new Node(random.nextInt(100)));
	// }
	// wh.tree_print(wh.root,0);
	// System.out.println("------");
	// System.out.println("Node Count : " + wh.NodeCount(wh.root));
	// System.out.println("Black Node Coutn" + wh.BlackNodeCount(wh.root));
	// System.out.println("Black Height" + wh.BlackHeight(wh.root));
    //Generated following code for trivial test
    // RB temp = new RB();
    // temp.RB_insert(new Node(1));
    // temp.RB_insert(new Node(2));
    // temp.RB_insert(new Node(3));
    // temp.tree_print(temp.root, 0);
    // System.out.println("======");
    // Node del = temp.tree_search(temp.root, 2);
    // temp.RB_delete(del);


    }
}
