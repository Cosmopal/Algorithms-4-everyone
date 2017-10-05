public class FenwickTree {

    int[] BITTree;
    int numValues;


    /*
    Two constructors are given to facilitate multiple implementations.
    For example, the requirement maybe to directly initialise the Tree.
    Here the first constructor can be used
    Or in another case, it maybe that the input is given from a stream, or usr input
    In the second case, initialising an empty tree and then calling update method
    by the user itself would save steps.
     */

    /**
     * Initialise an empty Tree
     * @param size
     */
    public FenwickTree(int size){
        numValues = size+1;
        BITTree = new int[numValues];
    }


    /**
     * Initialise the Fenwick tree for a given input
     * @param arr
     */
    public FenwickTree(int[] arr){
        int size = arr.length;
        numValues = size +1;
        BITTree = new int[numValues];
        for (int i = 0;i<size;i++){
            updateTree(i,arr[i]);
        }
    }

    /**
     * Adds a delta of 'value' to the integer
     * at position 'index' and updates all nodes covered by this node accordingly.
     * @param index - index in original array to be updated
     * @param value - value to add to the position 'index'
     */
    public void updateTree(int index,int value){
        index+=1;
        while(index<numValues){
            //Add the value to the current node
            BITTree[index] += value;

            //Update index to the next node that covers current node.
            index += index & (-index);
        }
    }


    /**
     * Returns the sum of the all elements of the array from 0 to index
     * @param index - the position till which sum is to be calculated
     * @return - the sum of elements of array from 0 to index
     */
    public int getSum(int index){
        int sum = 0;

        //Starting from index, moving back through all the ancestors to get the total sum
        // by adding each node's value
        for (int i = index+1; i>0;i -= i & (-i)){
            sum+= BITTree[i];
        }
        return sum;
    }

    //Example to check implementation
    public static void main(String[] args){
        int[] arr = {1,5,3,7,2,7,13,6};

        FenwickTree myTree = new FenwickTree(arr);

        for (int i = 0 ; i<8;i++){
            System.out.println(myTree.getSum(i));
        }
    }

}
