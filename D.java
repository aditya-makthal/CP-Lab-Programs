class SegmentTree {
    private int[] tree;
    private int[] nums;

    public SegmentTree(int[] nums) {
        this.nums = nums;
        int n = nums.length;
        tree = new int[4 * n];
        buildTree(1, 0, n - 1);
    }

    private void buildTree(int node, int start, int end) {
        if (start == end) {
            tree[node] = nums[start];
        } else {
            int mid = (start + end) / 2;
            int leftNode = 2 * node;
            int rightNode = 2 * node + 1;
            buildTree(leftNode, start, mid);
            buildTree(rightNode, mid + 1, end);
            tree[node] = tree[leftNode] + tree[rightNode];
        }
    }

    public int query(int left, int right) {
        return queryHelper(1, 0, nums.length - 1, left, right);
    }

    private int queryHelper(int node, int start, int end, int left, int right) {
        if (left > end || right < start) {
            return 0;
        } else if (left <= start && right >= end) {
            return tree[node];
        } else {
            int mid = (start + end) / 2;
            int leftNode = 2 * node;
            int rightNode = 2 * node + 1;
            int leftSum = queryHelper(leftNode, start, mid, left, right);
            int rightSum = queryHelper(rightNode, mid + 1, end, left, right);
            return leftSum + rightSum;
        }
    }

    public void update(int index, int value) {
        updateHelper(1, 0, nums.length - 1, index, value);
    }

    private void updateHelper(int node, int start, int end, int index, int value) {
        if (start == end) {
            nums[index] = value;
            tree[node] = value;
        } else {
            int mid = (start + end) / 2;
            int leftNode = 2 * node;
            int rightNode = 2 * node + 1;
            if (index <= mid) {
                updateHelper(leftNode, start, mid, index, value);
            } else {
                updateHelper(rightNode, mid + 1, end, index, value);
            }
            tree[node] = tree[leftNode] + tree[rightNode];
        }
    }
}
public class D {
    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 7, 9, 11};
        SegmentTree tree = new SegmentTree(nums);
        int sum = tree.query(2, 5);
        System.out.println("Sum from index 2 to index 5: " + sum);
        tree.update(3, 8);
        sum = tree.query(1, 4);
        System.out.println("Sum from index 1 to index 4 after update: " + sum);
    }
}