import javax.swing.*;
import java.awt.*;

public class RBTree{
    static final Color Red =Color.RED;
    static final Color Black=Color.BLACK;
    public RBNode root=null;

    private void rotateRight(RBNode node){
        RBNode parent = node.getParent();
        RBNode leftChild=node.getLeft();

        node.setLeft(leftChild.getRight());
        if (leftChild.getRight()!=null){
            leftChild.getRight().setParent(node);
        }

        leftChild.setRight(node);
        node.setParent(leftChild);

        replaceParentsChild(parent,node,leftChild);
    }

    private void rotateLeft(RBNode node){
        RBNode parent = node.getParent();
        RBNode rightChild = node.getRight();

        node.setRight(rightChild.getLeft());
        if (rightChild.getLeft()!=null){
            rightChild.getLeft().setParent(node);
        }

        rightChild.setLeft(node);
        node.setParent(rightChild);

        replaceParentsChild(parent,node,rightChild);
    }

    private void replaceParentsChild(RBNode parent, RBNode oldChlid, RBNode newClild){
        if (parent==null){
            root = newClild;
        }else if(parent.getLeft()==oldChlid){
            parent.setLeft(newClild);
        }else if(parent.getRight()==oldChlid){
            parent.setRight(newClild);
        } else {
            throw new IllegalStateException("Node is not a child of its parent");
        }

        if (newClild!=null){
            newClild.setParent(parent);
        }
    }
    public void insertNode (int key){
        RBNode node = root;
        RBNode parent = null;

        while (node!=null){
            parent=node;
            if (key< node.getNumber()){
                node = node.getLeft();
            } else if (key> node.getNumber()) {
                node = node.getRight();
            } else {
                throw new IllegalArgumentException("BST already contains node with key " +key);
            }
        }

        RBNode newNode = new RBNode(key);
        newNode.setNodeColor(Red);
        if (parent==null){
            root = newNode;
        } else if (key < parent.getNumber()){
            parent.setLeft(newNode);
        } else {
            parent.setRight(newNode);
        }
        newNode.setParent(parent);
        fixRBProperitiesAtferInsert(newNode);
    }
    public RBNode searchNode(int key){
        RBNode node =root;
        while (node!=null){
            System.out.println(node);
            if(key== node.getNumber()){
                return node;
            } else if (key< node.getNumber()) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }
        return null;
    }

    public void showTree(){
        JFrame a = new JFrame("Show tree");
        a.setSize(800,600);
        RBNode node = root;

        Point tempPoint = new Point(400,100);


        recPreOrder(a,node, tempPoint);
        a.setLayout(null);
        a.setVisible(false);
    }

    private void recPreOrder(JFrame a,RBNode node, Point xy){
        if (node!=null){
            if (node==root) System.out.print("root:\t");
            System.out.println(node.toString());
            JLabel showNode = new JLabel(String.valueOf(node.getNumber()));
            showNode.setForeground((node.getNodeColor()));
            showNode.setLocation(xy);
            showNode.setSize(50,50);
            a.add(showNode);
            Point temp =xy;
            Point temp2 = xy;
            temp.y+=50;
            temp.x-=50;
            if (node.getLeft()!=null)System.out.print("Left child of "+node.getNumber()+" : \t");
            recPreOrder(a,node.getLeft(),temp);
            temp2.y+=50;
            temp2.x+=50;
            if (node.getRight()!=null)System.out.print("Right child of "+node.getNumber()+" : \t");
            recPreOrder(a,node.getRight(),temp2);
        }
    }
    private RBNode getUncle(RBNode parent){
        RBNode grandParent = parent.getParent();
        if (grandParent.getLeft()==parent){
            return grandParent.getRight();
        } else if (grandParent.getRight()==parent){
            return grandParent.getLeft();
        } else {
            throw new IllegalStateException("Parent is not a child of its grandparent");
        }
    }
    public void fixRBProperitiesAtferInsert(RBNode node){
        RBNode parent = node.getParent();
        if (parent==null){
            node.setNodeColor(Color.BLACK);
            return;
        }
        if (parent.getNodeColor()==Color.BLACK){
            return;
        }
        RBNode grandParent = parent.getParent();

        if (grandParent==null){
            parent.setNodeColor(Black);
            return;
        }
        RBNode uncle = getUncle(parent);
        if (uncle!=null && uncle.getNodeColor()==Red){
            parent.setNodeColor(Black);
            grandParent.setNodeColor(Red);
            uncle.setNodeColor(Black);

            fixRBProperitiesAtferInsert(grandParent);
        } else if (parent==grandParent.getLeft()) {
            if (node==parent.getRight()){
                rotateLeft(parent);
                parent=node;
            }
            rotateRight(grandParent);
            parent.setNodeColor(Black);
            grandParent.setNodeColor(Red);
        }
        else {
            if (node == parent.getLeft()){
                rotateRight(parent);
                parent=node;
            }
            rotateLeft(grandParent);
            parent.setNodeColor(Black);
            grandParent.setNodeColor(Red);
        }

    }
}
