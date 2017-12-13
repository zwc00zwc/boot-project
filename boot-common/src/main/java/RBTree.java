/**
 * Created by alan.zheng on 2017/12/13.
 */
public class RBTree<T extends Comparable<T>> {

    private RBNode<T> root;

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public class RBNode<T extends Comparable<T>>{
        boolean color;
        T key;
        RBNode<T> left;
        RBNode<T> right;
        RBNode<T> parent;

        public RBNode(T _key, boolean _color, RBNode<T> _parent, RBNode<T> _left, RBNode<T> _right){
            this.key = _key;
            this.color = _color;
            this.parent = _parent;
            this.left = _left;
            this.right = _right;
        }
    }

    /**
     * 前序遍历
     * @param tree
     */
    private void preOrder(RBNode<T> tree){
        if (tree!=null){
            System.out.print(tree.key + "");
            preOrder(tree.left);
            preOrder(tree.left);
        }
    }

    public void preOrder(){
        preOrder(root);
    }

    /**
     * 中序遍历
     * @param tree
     */
    private void inOrder(RBNode<T> tree){
        if (tree!=null){
            inOrder(tree.left);
            System.out.print(tree.key+"");
            inOrder(tree.left);
        }
    }

    public void inOrder(){
        inOrder(root);
    }

    /**
     * 后序遍历
     * @param tree
     */
    private void postOrder(RBNode<T> tree){
        if (tree!=null){
            postOrder(tree.left);
            postOrder(tree.right);
            System.out.print(tree.key);
        }
    }

    public void postOrder(){
        postOrder(root);
    }

    /**
     * 查找键值为key的节点(遍历)
     * @param x
     * @param key
     * @return
     */
    private RBNode<T> search(RBNode<T> x,T key){
        if (x==null){
            return x;
        }
        if (key.compareTo(key)<0){
            return search(x.left,key);
        }else if (key.compareTo(key)>0){
            return search(x.right,key);
        }else {
            return x;
        }
    }

    public RBNode<T> search(T key){
        return search(root,key);
    }

    /**
     * 查找键值为key的节点(非遍历)
     * @param x
     * @param key
     * @return
     */
    private RBNode<T> iterativeSearch(RBNode<T> x,T key){
        while (x!=null){
            if (key.compareTo(key)<0){
                x = x.left;
            }else if (key.compareTo(key)>0){
                x = x.right;
            }else {
                return x;
            }
        }
        return x;
    }

    public RBNode iterativeSearch(T key){
        return iterativeSearch(root,key);
    }

    /**
     * 左旋转
     * @param x
     */
    private void leftRotate(RBNode<T> x){
        //设置x的右孩子为y
        RBNode<T> y = x.right;

        //将 y的左孩子 设为x的右孩子
        x.right = y.left;
        //如果y的左孩子非空，将x设为 y的左孩子的父亲
        if (y.left!=null){
            x.left.parent = x;
        }

        //将 x的父亲设为 y的父亲
        y.parent = x.parent;

        //如果 x的父亲 是空节点，则将y设为根节点
        if (x.parent == null){
            this.root = y;
        }else {
            //如果 x 是它父节点的左孩子，则将y设为 x 的父节点的左孩子
            if (x.parent.left == x){
                x.parent.left = y;
            }else {
                x.parent.right = y;
            }
        }
        y.left = x;
        x.parent = y;
    }

    /**
     * 右旋转
     * @param y
     */
    private void rightRotate(RBNode<T> y){
        //设置 y的左孩子为x
        RBNode<T> x = y.left;
        //将x的右孩子设置为y的左孩子
        x.right = y.left;
        //如果x的右孩子非空，将y设为 x的右孩子的父亲
        if (x.right != null){
            x.right.parent = y;
        }
        //将y的父亲设为x的父亲
        x.parent = y.parent;
        //如果y的父亲是空节点，则将x设置为根节点
        if (y.parent == null){
            this.root = x;
        }else {
            //如果y是它父节点的左孩子，则将x设为它父节点的左孩子
            if (y == y.parent.left){
                y.parent.left = x;
            }else {
                y.parent.right = x;
            }
        }

        x.right = y;
        y.parent = x;
    }

    /**
     * 插入节点
     * @param node
     */
    private void insert(RBNode<T> node) {
        int cmp;
        RBNode<T> y = null;
        RBNode<T> x = this.root;

        // 1. 将红黑树当作一颗二叉查找树，将节点添加到二叉查找树中。
        while (x != null) {
            y = x;
            cmp = node.key.compareTo(x.key);
            if (cmp < 0)
                x = x.left;
            else
                x = x.right;
        }

        node.parent = y;
        if (y!=null) {
            cmp = node.key.compareTo(y.key);
            if (cmp < 0)
                y.left = node;
            else
                y.right = node;
        } else {
            this.root = node;
        }

        // 2. 设置节点的颜色为红色
        node.color = RED;

        // 3. 将它重新修正为一颗二叉查找树
        insertFixUp(node);
    }


    public void insert(T key) {
        RBNode<T> node=new RBNode<T>(key,BLACK,null,null,null);

        // 如果新建结点失败，则返回。
        if (node != null)
            insert(node);
    }

    /**
     * 插入修正红黑树
     * @param node
     */
    private void insertFixUp(RBNode<T> node){
        RBNode<T> parent;
        RBNode<T> gparent;
        // 若“父节点存在，并且父节点的颜色是红色”
        while (((parent = parentOf(node))!=null) && isRed(parent)) {
            gparent = parentOf(parent);

            //若“父节点”是“祖父节点的左孩子”
            if (parent == gparent.left) {
                // Case 1条件：叔叔节点是红色
                RBNode<T> uncle = gparent.right;
                if ((uncle!=null) && isRed(uncle)) {
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gparent);
                    node = gparent;
                    continue;
                }

                // Case 2条件：叔叔是黑色，且当前节点是右孩子
                if (parent.right == node) {
                    RBNode<T> tmp;
                    leftRotate(parent);
                    tmp = parent;
                    parent = node;
                    node = tmp;
                }

                // Case 3条件：叔叔是黑色，且当前节点是左孩子。
                setBlack(parent);
                setRed(gparent);
                rightRotate(gparent);
            } else {    //若“z的父节点”是“z的祖父节点的右孩子”
                // Case 1条件：叔叔节点是红色
                RBNode<T> uncle = gparent.left;
                if ((uncle!=null) && isRed(uncle)) {
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gparent);
                    node = gparent;
                    continue;
                }

                // Case 2条件：叔叔是黑色，且当前节点是左孩子
                if (parent.left == node) {
                    RBNode<T> tmp;
                    rightRotate(parent);
                    tmp = parent;
                    parent = node;
                    node = tmp;
                }

                // Case 3条件：叔叔是黑色，且当前节点是右孩子。
                setBlack(parent);
                setRed(gparent);
                leftRotate(gparent);
            }
        }
    }

    /**
     * 删除节点
     * @param node
     */
    private void remove(RBNode<T> node) {
        RBNode<T> child, parent;
        boolean color;

        // 被删除节点的"左右孩子都不为空"的情况。
        if ( (node.left!=null) && (node.right!=null) ) {
            // 被删节点的后继节点。(称为"取代节点")
            // 用它来取代"被删节点"的位置，然后再将"被删节点"去掉。
            RBNode<T> replace = node;

            // 获取后继节点
            replace = replace.right;
            while (replace.left != null)
                replace = replace.left;

            // "node节点"不是根节点(只有根节点不存在父节点)
            if (parentOf(node)!=null) {
                if (parentOf(node).left == node)
                    parentOf(node).left = replace;
                else
                    parentOf(node).right = replace;
            } else {
                // "node节点"是根节点，更新根节点。
                this.root = replace;
            }

            // child是"取代节点"的右孩子，也是需要"调整的节点"。
            // "取代节点"肯定不存在左孩子！因为它是一个后继节点。
            child = replace.right;
            parent = parentOf(replace);
            // 保存"取代节点"的颜色
            color = colorOf(replace);

            // "被删除节点"是"它的后继节点的父节点"
            if (parent == node) {
                parent = replace;
            } else {
                // child不为空
                if (child!=null)
                    setParent(child, parent);
                parent.left = child;

                replace.right = node.right;
                setParent(node.right, replace);
            }

            replace.parent = node.parent;
            replace.color = node.color;
            replace.left = node.left;
            node.left.parent = replace;

            if (color == BLACK)
                removeFixUp(child, parent);

            node = null;
            return ;
        }

        if (node.left !=null) {
            child = node.left;
        } else {
            child = node.right;
        }

        parent = node.parent;
        // 保存"取代节点"的颜色
        color = node.color;

        if (child!=null)
            child.parent = parent;

        // "node节点"不是根节点
        if (parent!=null) {
            if (parent.left == node)
                parent.left = child;
            else
                parent.right = child;
        } else {
            this.root = child;
        }

        if (color == BLACK)
            removeFixUp(child, parent);
        node = null;
    }

    public void remove(T key) {
        RBNode<T> node;

        if ((node = search(root, key)) != null)
            remove(node);
    }

    /**
     * 删除修正红黑树
     * @param node
     * @param parent
     */
    private void removeFixUp(RBNode<T> node, RBNode<T> parent) {
        RBNode<T> other;

        while ((node==null || isBlack(node)) && (node != this.root)) {
            if (parent.left == node) {
                other = parent.right;
                if (isRed(other)) {
                    // Case 1: x的兄弟w是红色的
                    setBlack(other);
                    setRed(parent);
                    leftRotate(parent);
                    other = parent.right;
                }

                if ((other.left==null || isBlack(other.left)) &&
                        (other.right==null || isBlack(other.right))) {
                    // Case 2: x的兄弟w是黑色，且w的俩个孩子也都是黑色的
                    setRed(other);
                    node = parent;
                    parent = parentOf(node);
                } else {

                    if (other.right==null || isBlack(other.right)) {
                        // Case 3: x的兄弟w是黑色的，并且w的左孩子是红色，右孩子为黑色。
                        setBlack(other.left);
                        setRed(other);
                        rightRotate(other);
                        other = parent.right;
                    }
                    // Case 4: x的兄弟w是黑色的；并且w的右孩子是红色的，左孩子任意颜色。
                    setColor(other, colorOf(parent));
                    setBlack(parent);
                    setBlack(other.right);
                    leftRotate(parent);
                    node = this.root;
                    break;
                }
            } else {

                other = parent.left;
                if (isRed(other)) {
                    // Case 1: x的兄弟w是红色的
                    setBlack(other);
                    setRed(parent);
                    rightRotate(parent);
                    other = parent.left;
                }

                if ((other.left==null || isBlack(other.left)) &&
                        (other.right==null || isBlack(other.right))) {
                    // Case 2: x的兄弟w是黑色，且w的俩个孩子也都是黑色的
                    setRed(other);
                    node = parent;
                    parent = parentOf(node);
                } else {

                    if (other.left==null || isBlack(other.left)) {
                        // Case 3: x的兄弟w是黑色的，并且w的左孩子是红色，右孩子为黑色。
                        setBlack(other.right);
                        setRed(other);
                        leftRotate(other);
                        other = parent.left;
                    }

                    // Case 4: x的兄弟w是黑色的；并且w的右孩子是红色的，左孩子任意颜色。
                    setColor(other, colorOf(parent));
                    setBlack(parent);
                    setBlack(other.left);
                    rightRotate(parent);
                    node = this.root;
                    break;
                }
            }
        }

        if (node!=null)
            setBlack(node);
    }

    private RBNode<T> parentOf(RBNode<T> node){
        return node != null ? node.parent : null;
    }

    private boolean colorOf(RBNode<T> node){
        return node != null ? node.color : BLACK;
    }

    private boolean isRed(RBNode<T> node){
        return ((node != null) && (node.color == RED)) ? true:false;
    }

    private boolean isBlack(RBNode<T> node){
        return ((node != null) && (node.color == BLACK)) ? true:false;
    }

    private void setBlack(RBNode<T> node){
        if (node != null){
            node.color = BLACK;
        }
    }

    private void setRed(RBNode<T> node){
        if (node != null){
            node.color = RED;
        }
    }

    private void setParent(RBNode<T> node,RBNode<T> parent){
        if (node != null){
            node.parent = parent;
        }
    }

    private void setColor(RBNode<T> node,boolean color){
        if (node != null){
            node.color = color;
        }
    }
}
