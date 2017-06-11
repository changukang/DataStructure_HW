import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.lang.Integer;
import java.lang.Double;
import java.io.File;
import java.io.IOException;

class Node {
  public int val;
  public Node left;
  public Node right;
  public Node p;
  public int color;
  /////
  /// color RED is assigned to integer 1
  /// color BLACK is assigned to integer 0
  /////
  public Node(){
  	val = 0;
    left = null;
    right = null;
    p = null;
    color = 0;
  }

  public static Node Nil(){
  	return new Node();
  }	

  public Node(int newval) {
    val = newval;
    left = Nil();
    right = Nil();
    left.p = this;
    right.p = this;
    p = Nil();
    color = 1;
  }
}

class RB{
	public int[] none_found = new int[1000]; //없느 노드를 기록하기 위함
	public int miss = 0;
	public int index = 0;
	public int insert = 0;
	public int delete = 0;

	public Node root;
	public RB(){
		root = Node.Nil();
	}

	public void Left_Rotate(Node x){
		Node y = x.right;
		x.right = y.left;

		if(y.left.val != 0){
			y.left.p = x;
		}
		y.p = x.p;
		if(x.p.val == 0){
			root = y;
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
		if(y.right.val != 0){
			y.right.p = x;
		}
		y.p = x.p;
		if(x.p.val == 0){
			root = y;
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
		Node y;
			while( z.p.color == 1){
				if(z.p == z.p.p.left){
					y = z.p.p.right; //y 는 삼촌
					if(y.color == 1){ //CASE 1
						z.p.color = 0;
						y.color = 0;
						z.p.p.color = 1;
						z = z.p.p;
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
				if(z.p == z.p.p.right){
					y = z.p.p.left; //y 는 삼촌
					if(y.color == 1){ //CASE 1
						z.p.color = 0;
						y.color = 0;
						z.p.p.color = 1;
						z = z.p.p;
					}
					else
					{
					 if (z==z.p.left){ //CASE 2
						z = z.p;
						Right_Rotate(z);
					}
					z.p.color = 0;//CASE 3
					z.p.p.color = 1;
					Left_Rotate(z.p.p);
				}
			}
		}
		}
		root.color = 0; 
	}


	public void RB_insert(Node z){
		Node y = Node.Nil();
		Node x = root;
    while (x.val != 0){
      y = x;
      if (z.val < x.val){
        x = x.left;
      }
      else{
        x = x.right;
      }
    }

    z.p = y;
    if (y.val == 0){
      root = z;
    }
    else if (z.val < y.val){
      y.left = z;
    }
    else{
      y.right=z;
    }
    // z.left = Node.Nil();
    // z.right = Node.Nil();
    // z.color = 1;

	RB_INSERT_FIXUP(z);

	}

    // public void tree_print(Node tree, int level) {

	   //  if (tree.right != null)
	   //    tree_print(tree.right, level + 1);

	   //  for(int i = 0; i < level; i++)
	   //    System.out.print("        ");

	   //  System.out.println(tree.val+""+tree.color);
	   //  if (tree.left != null)
	   //    tree_print(tree.left, level + 1);
    // }

   public void RB_DELETE_FIXUP(Node x){
   	Node w;
  	while(x!=root && x.color == 0){
  		if(x==x.p.left){
  			w = x.p.right;
  			if(w.color == 1){
  				w.color = 0;
  				x.p.color = 1;
  				Left_Rotate(x.p);
  				w=x.p.right;
  			}
  			if(w.left.color == 0 && w.right.color == 0){
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
  				x = root;
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
  				w.color = 1;
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
  				x = root;
  			}
  		}
	}
	x.color = 0;

  	}

	public Node tree_min(Node n){
	    while(n.left.val != 0){
	      n = n.left;
	    }
	    return n;
	  }

	public void RB_Transplant(Node u, Node v){
	  	if(u.p.val==0){
	  		root = v;
	  	}
	  	else if(u == u.p.left){
	  		u.p.left = v;
	  	}
	  	else{
	  		u.p.right = v;
	  	}
	  	v.p = u.p;
	 }


	public void RB_delete(Node z){
		Node y = z;
 	 	int y_org_col = y.color;
 	 	Node x;
	 	//checker
	 	// System.out.println(z.left);
	 	if(z.left.val == 0){
	 		x = z.right;
	 		RB_Transplant(z,z.right);
	 	}
	 	else if(z.right.val == 0){
	 		x = z.left;
	 		RB_Transplant(z, z.left);
	 	}
	 	else{
	 		y = tree_min(z.right);
	 		y_org_col = y.color;
	 		x = y.right;
	 		if(y.p == z){
	 		 x.p=y;
	 		}
	 		else{
	 			RB_Transplant(y,y.right);
	 			y.right = z.right;
	 			y.right.p =y;
	 		}
	 		RB_Transplant(z,y);
	 		y.left = z.left;
	 		y.left.p = y;
	 		y.color = z.color;
	 	}
	 	if(y_org_col == 0){
	 		RB_DELETE_FIXUP(x);
	 	}

	 }

	 public Node tree_search(Node tree, int val) {
	 	if (tree.val == 0)
	      return Node.Nil();
	    else if (val == tree.val)
	      return tree;
	    else if (val < tree.val)
	      return tree_search(tree.left,val);
	    else
	      return tree_search(tree.right,val);
  	}


  	public void inorder(Node tree) {
  		String col = null;
    	if (tree.val == 0)
      		return;
   		else {
	      inorder(tree.left);
	      if(tree.color ==1){
	      	col = "R";
	      }
	      else{
	      	col = "B";
	      }
	      System.out.println(tree.val +" " + col);
	      inorder(tree.right);
   		 }
 	 }

 	 public int NodeCount(Node tree){
 	 	int c = 1;
 	 	if( tree.right.val!=0) { c+= NodeCount(tree.right); }
 	 	if( tree.left.val!=0) { c+= NodeCount(tree.left); }

 	 	return c;
  	}

	public int BlackNodeCount(Node tree){	
		int c = 0;
		if(tree.left.val != 0) c+=BlackNodeCount(tree.left);
		if(tree.right.val!=0) c+=BlackNodeCount(tree.right);
		if(tree.color == 0) c+=1;
		return c;
	}


	public int BlackHeight(Node tree){
		if(tree.val == 0){
			return 0;
		}
		else if(tree.color==0){
			return BlackHeight(tree.left) + 1;
		}
		else
			return BlackHeight(tree.left);
	}

}


public class HW05 {


    public static void main(String[] args) throws IOException {



    	int index = 1;
    	 RB temp = new RB();
    	 //Getting input from txt file
    	 BufferedReader br= null;



    	File dir = new File("./input/"); 
		File[] fileList = dir.listFiles(); 

		for(int i = 0 ; i < fileList.length ; i++){
				File file = fileList[i]; 
				if(file.isFile())

				br = new BufferedReader(new FileReader("./input/" + file.getName()));
				 while(true) {
		            String line = br.readLine();
		            // System.out.println(line);
		            if (line==null) break;
		            // int num = Integer.parseInt(line);
		            double d = Double.parseDouble(line);
		            int num = (int)d;
		            if(num > 0){
		            	temp.RB_insert(new Node(num));
		            	temp.insert +=1;
		            }
		            else if(num<0){
		            	Node del;
		            	del = temp.tree_search(temp.root, num *= -1);
		            	if(del.val != 0){
		           			temp.RB_delete(del);
		           			temp.delete+=1;
		           		}
		           		else{
		           			num*=-1;
		           			temp.none_found[temp.index] = num;
		           			temp.index += 1;
		           			temp.miss+=1;
		           		}
		           	}
		            else if(num==0){
		            	break;
            }


        }
        br.close();
        System.out.println("filename = " + file.getName());
        System.out.println("total = " + temp.NodeCount(temp.root));
        System.out.println("insert = " + temp.insert);
        System.out.println("delete = " + temp.delete);
        System.out.println("delete = " + temp.miss);
        System.out.println("nb = " + temp.BlackNodeCount(temp.root));
        System.out.println("bh = " + temp.BlackHeight(temp.root));
        temp.inorder(temp.root);

		}


       
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
