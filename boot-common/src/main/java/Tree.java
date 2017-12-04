import java.util.ArrayList;
import java.util.List;

/**
 * Created by alan.zheng on 2017/12/4.
 */
public class Tree {
    public static void main(String[] args){
        TreeNode treeNode = new TreeNode(1);
//        TreeNode t2 = new TreeNode(2);
//        TreeNode t3 = new TreeNode(3);
//        TreeNode t4 = new TreeNode(4);
//        TreeNode t5 = new TreeNode(5);
//        TreeNode t6 = new TreeNode(6);
//        TreeNode t7 = new TreeNode(7);
//        TreeNode t8 = new TreeNode(8);
//        TreeNode t9 = new TreeNode(9);
//        t1.setLeft(t2);
//        t1.setRight(t3);
//        t2.setLeft(t4);
//        t2.setRight(t5);
//        t3.setLeft(t6);
//        t3.setRight(t7);
//        t4.setLeft(t8);
//        t4.setRight(t9);

        int[] array =new int[]{1,2,3,4,5,6,7};
        TreeNode treeNode1 = initAllTree(array);
//        for (int i = 2;i<6;i++){
//            buildTree(treeNode,i);
//        }
//        printVal(treeNode);
    }

    public static TreeNode initAllTree(int[] arr) {
        if(arr.length == 1) {
            return new TreeNode(arr[0]);
        }
        List<TreeNode> nodeList = new ArrayList<TreeNode>();

        for(int i = 0; i < arr.length; i++) {
            nodeList.add(new TreeNode(arr[i]));
        }
        int temp = 0;
        while(temp <= (arr.length - 2) / 2) {  //注意这里，数组的下标是从零开始的
            if(2 * temp + 1 < arr.length) {
                nodeList.get(temp).setLeft(nodeList.get(2 * temp + 1));
            }
            if(2 * temp + 2 < arr.length) {
                nodeList.get(temp).setRight(nodeList.get(2 * temp + 2));
            }
            temp++;
        }
        return nodeList.get(0);
    }

    public static void buildTree(TreeNode node,int data){
        if(node == null){
            node = new TreeNode(data);
        }else{
            if(data < node.getVal()){
                if(node.getLeft() == null){
                    node.setLeft(new TreeNode(data));
                }else{
                    buildTree(node.getLeft(),data);
                }
            }else{
                if(node.getRight() == null){
                    node.setRight(new TreeNode(data));
                }else{
                    buildTree(node.getRight(),data);
                }
            }
        }
    }

    private static void printVal(TreeNode root){
        if (root == null){
            return;
        }
        System.out.print(root.getVal()+";");
        printVal(root.getLeft());
        printVal(root.getRight());
    }

}

class TreeNode{
    Integer val;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(Integer _val){
        this.val = _val;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
