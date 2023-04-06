import java.awt.*;

public class RBNode {
    private RBNode left = null;
    private RBNode right = null;
    private RBNode parent;
    private Color nodeColor = Color.red;
    private int number;
    public RBNode(int number){
        this.number=number;
    }

    public String toString(){
        String color;
        if (this.nodeColor==Color.BLACK) color="Black"; else color="Red";
        return "Color: "+color+"\t Data: "+this.number;
    }
    public RBNode getLeft() {
        return left;
    }

    public void setLeft(RBNode left) {
        this.left = left;
    }

    public RBNode getRight() {
        return right;
    }

    public void setRight(RBNode right) {
        this.right = right;
    }

    public RBNode getParent() {
        return parent;
    }

    public void setParent(RBNode parent) {
        this.parent = parent;
    }

    public Color getNodeColor() {
        return nodeColor;
    }

    public void setNodeColor(Color nodeColor) {
        this.nodeColor = nodeColor;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
