package second_day_java;
import java.util.ArrayList;
import java.util.HashMap;

public class Heap<T extends Comparable<T>> 
{
	ArrayList<T> dataList = new ArrayList<>();
	HashMap<T, Integer> indexMap = new HashMap<>();

	public void add(T item) 
	{
		dataList.add(item);   
		indexMap.put(item, this.dataList.size() - 1);
		upheapify(dataList.size() - 1);
	}

	private void upheapify(int childIndex) 
	{
		int parentIndex = (childIndex - 1) / 2;
		if (isLarger(dataList.get(childIndex), dataList.get(parentIndex)) > 0) 
		{
			swap(parentIndex, childIndex);
			upheapify(parentIndex);
		}
	}

	private void swap(int i, int j) 
	{
		T ith = dataList.get(i);
		T jth = dataList.get(j);
		
		dataList.set(i, jth);
		dataList.set(j, ith);
		indexMap.put(ith, j);
		indexMap.put(jth, i);
	}

	public void display() 
	{
		System.out.println(dataList);
	}

	public int size() 
	{
		return this.dataList.size();
	}

	public boolean isEmpty() 
	{
		return this.size() == 0;
	}

	public T remove() 
	{
		swap(0, this.dataList.size() - 1);
		T rv = this.dataList.remove(this.dataList.size() - 1);
		downheapify(0);

		indexMap.remove(rv);
		return rv;
	}

	private void downheapify(int parentIndex) 
	{
		int leftChildIndex = 2 * parentIndex + 1;
		int rightChildIndex = 2 * parentIndex + 2;
		int minIndex = parentIndex;

		if (leftChildIndex < this.dataList.size() && isLarger(dataList.get(leftChildIndex), dataList.get(minIndex)) > 0)
		{
			minIndex = leftChildIndex;
		}
		
		if (rightChildIndex < this.dataList.size() && isLarger(dataList.get(rightChildIndex), dataList.get(minIndex)) > 0) 
		{
			minIndex = rightChildIndex;
		}
		
		if (minIndex != parentIndex)
		{
			swap(minIndex, parentIndex);
			downheapify(minIndex);
		}
	}

	public T get() 
	{
		return this.dataList.get(0);
	}

	public int isLarger(T t, T o) 
	{
		return t.compareTo(o);
	}

	public void updatePriority(T item) 
	{
		int index = indexMap.get(item);
		upheapify(index);
	}
}
