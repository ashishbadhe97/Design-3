// 341. Flatten Nested List Iterator
// https://leetcode.com/problems/flatten-nested-list-iterator/description/

/**
 * We convert the entire list into iterator
 * and add it to stack
 * hasNext() is always called before next(). Hence, hasNext() will always set the nextEle, which we would return from next()
 * Whenever hasNext() is called, we iterate over stack, and check the next of top element of stack
 * If it's integer, we set nextElement and return
 * else if its list, we convert list to iterator and add list to stack. Now stack's top has nested iterator and its next is set to first element
 * We pop the element when next() of stack top is null
 * We iterate till stack is empty
 */

/**
 * Time Complexity: O(1) for next() and amortized O(1) for hasNext()
 * Space Complexity: O(d) where d is nested depth of iterator
 */

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {

    Stack<Iterator<NestedInteger>> st;
    int nextElement;

    public NestedIterator(List<NestedInteger> nestedList) {
        st = new Stack<>();
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextElement;
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()){
            if(!st.peek().hasNext()){
                st.pop();
            }else{
                NestedInteger ne = st.peek().next();
                if(ne.isInteger()){
                    nextElement = ne.getInteger();
                    return true;
                }else{
                    st.push(ne.getList().iterator());
                }
            }

        }

        return false;
    }
}
