class FenwickTree {
    private int[] tree;

    public FenwickTree(int n) {
        tree = new int[n + 1];
    }

    public void update(int i, int delta) {
        while (i < tree.length) {
            tree[i] += delta;
            i += i & -i;
        }
    }

    public int query(int i) {
        int result = 0;
        while (i > 0) {
            result += tree[i];
            i -= i & -i;
        }
        return result;
    }
    public int sumRange(int i, int j){
        return query(j)-query(i-1);
    }
}
class C{
    public static void main(String[] args) {
        FenwickTree ft = new FenwickTree(5);
        ft.update(0,1);
        ft.update(1,2);
        ft.update(2,3);
        ft.update(3,4);
        ft.update(4,5);
        int sum = ft.sumRange(2,4);
        System.out.println(sum);
    }
}