
    /**************************************************
    Copyright (C) 2014  Raptis Dimos <raptis.dimos@yahoo.gr>


    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
	**************************************************/


import static java.lang.System.*;
 
 
public class Heap {
     
    int[] data;
    int heapSize;
    Node[] node;
     
    public Heap(int size) {
    	int i;
    	data = new int[size];
    	node = new Node[size];
        heapSize = 0;
        for(i=0;i<size;i++)	
        	node[i] = null;
    }
         
     
    public int getLeftChildindex(int nodeIndex) {
        return (2 * nodeIndex) + 1;
    }
     
    public int getRightChildindex(int nodeIndex) {
        return (2 * nodeIndex) + 2;
    }
     
    public int getParentindex(int nodeIndex) {
        return (int) (nodeIndex - 1)/2;
    }
     
    public void insert (int value,Node new_node) throws HeapException {
        if(heapSize == data.length){
            //throw new HeapException("Heap Overflow");
            Heap new_heap = new Heap(heapSize+1);
            int[] copied_data =new int [heapSize+1];
            Node[] copied_node =new Node[heapSize+1];
            System.arraycopy(this.data,0,copied_data,0,this.data.length);
            System.arraycopy(this.node,0,copied_node,0,this.node.length);
            this.data = copied_data;
            this.node = copied_node;
        }
        if(new_node == null)
        	throw new HeapException("Heap Overflow");
        heapSize++;
        int currentIndex = heapSize - 1;
        data[currentIndex] = value;
        this.node[currentIndex] = new_node;
        bubbleUP(currentIndex);
    }
     
    public void bubbleUP(int nodeIndex) {
        if(nodeIndex == 0)
            return;
        int indexOfParent = getParentindex(nodeIndex);
        if((data[indexOfParent] > data[nodeIndex]) && indexOfParent >= 0) {
            int tmp = data[indexOfParent];
            data[indexOfParent] = data[nodeIndex];
            data[nodeIndex] = tmp;
            Node tmp2 = node[indexOfParent];
            node[indexOfParent] = node[nodeIndex];
            node[nodeIndex] = tmp2; 
            nodeIndex = indexOfParent;
            bubbleUP(nodeIndex);
        } else
            return;
    }
     
     
    public void insertWithoutRecursion(int value) {
         
        heapSize++;
         
        int currentIndex = heapSize - 1;
        data[currentIndex] = value;
         
        int tmp;
        int indexOfParent = getParentindex(currentIndex);
         
        while ((data[indexOfParent] > data[currentIndex]) && indexOfParent >= 0) {
            tmp = data[indexOfParent];
            data[indexOfParent] = data[currentIndex];
            data[currentIndex] = tmp;
            currentIndex = indexOfParent;
            indexOfParent = getParentindex(currentIndex);
        }
 
    }
     
    public Node extractMin() {
        int min = data[0];
        if(heapSize == 0) 
        	return null;
        Node extracted_node = this.node[0];
        removeMin();
        return extracted_node;
    }
     
    public void removeMin() {
        if(heapSize == 0)
            return;
        data[0] = data[heapSize -1];
        node[0] = node[heapSize-1];
        heapSize--;
        if(heapSize > 0)
            bubbleDOWN(0);
    }
 
    public void bubbleDOWN(int nodeIndex) {
        int leftChildIndex = getLeftChildindex(nodeIndex);
        int rightChildIndex = getRightChildindex(nodeIndex);
        int smallerValueIndex = -1;
        if (leftChildIndex < heapSize && rightChildIndex < heapSize) {
            smallerValueIndex = (data[leftChildIndex] - data[rightChildIndex]) < 0 ? leftChildIndex : rightChildIndex;
        } else if (leftChildIndex < heapSize) {
            smallerValueIndex = leftChildIndex;
        } else if (rightChildIndex < heapSize) {
            smallerValueIndex = rightChildIndex;
        } else {
            return;
        }
        if (smallerValueIndex >= 0 && data[smallerValueIndex] < data[nodeIndex]) {
            int tmp = data[nodeIndex];
            data[nodeIndex] = data[smallerValueIndex];
            data[smallerValueIndex] = tmp;
            Node temp2 = node[nodeIndex];
	    	node[nodeIndex] = node[smallerValueIndex];
	    	node[smallerValueIndex] = temp2;
            nodeIndex = smallerValueIndex;
            bubbleDOWN(nodeIndex);
        }
    }
     
 
    public void makeHeap(int[] array,Node[] map) throws HeapException {
        for (int i = 0; i < array.length; i++) {
            this.insert(array[i],map[i]);
        }
    }
    
    public static void main(String[] args){
		System.out.println("I am working");
	}	
}
 
class HeapException extends Exception {
    public HeapException(String message) {
        super(message);
    }
}


