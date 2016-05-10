import java.util.Arrays;

/**
 * 
 * @author susama saha
 * This class is the Java implementation of the ArrayPlay.
 *
 */
public class ArrayPlay {
	private int a[];
	private	int size;
	//private	int last;
	private final int emptyFiller = 999999; // The empty filler value is used to denote an empty space in the array.
	
	public ArrayPlay()
	{
		this.size=15;
		this.a = new int[this.size];
		int i=0;
		
		for(i=0;i<this.size;i++)
			this.a[i]=i; // fill with the value of index.
		
		//this.last=0;
	}
	
	public	ArrayPlay(final int a[], final int size, final int last)
	{
		this.a=a;
		//this.last=last;
		this.size=size;
		sort();
	}
	
	public int capacity()
	{
		return a.length;
	}
	
	public boolean isEmpty()
	{
		if (a.length==0)
			return true;
		else
		{
			for (int i=0;i<a.length;i++)
			{
				if (a[i] != emptyFiller) // free space
				{
					return false;
				}
			}
		}
		return true;
	}
	
	public int noOfItems()
	{
		int i = 0;
		for (i=0;i<a.length;i++)
		{
			if (a[i] == emptyFiller) // free space
			{
				return i+1;
			}
		}
		return i;
	}
	
	public void add(final int newItem)
	{
		for (int i=0;i<a.length;i++)
		{
			if (a[i] == emptyFiller) // free space
			{
				a[i]=newItem;
			//	last++;
				sort();
				break;
			}
		}
	}
	
	public void add(final int anotherArray[] )
	{
		int lastIndex = -1;
		// first find out if there is any free space available in member array.
		for (int i=0;i<a.length;i++)
		{
			if (a[i] == emptyFiller) // free space
			{
				lastIndex = i;
				break;
			}
		}
		
		if (lastIndex != -1) // there are some free space available.
		{
			if (anotherArray.length <= (a.length-lastIndex)) // All elements of anotherArray can be added to member array.
			{
				for (int i=0;i<anotherArray.length;i++)
				{
					a[lastIndex++] = anotherArray[i];
				}
			}
			else // Not all elements from anotherArray can be added to member array
			{
				int j=0;
				for (int i=lastIndex;i<a.length;i++)
				{
					a[i] = anotherArray[j++];
				}				
			}
			sort();
		}
	}
	
	public void printArray()
	{
		System.out.print("\na=[");
		for (int i=0;i<a.length;i++)
		{
			if (a[i] != emptyFiller) // print only the non-empty elements.
			{
				if (i != 0)
					System.out.print(", ");
				System.out.print(a[i]);
			}
		}
		System.out.print("]\n");
	}
	public void sort()
	{
		Arrays.sort(a);
	}
	public int find(final int x)
	{
		/*
		for (int i=0;i<a.length;i++)
		{
			if (x==a[i])
				return i;
		}
		return -1;
		*/
		
		
		// Java Arrays has binarySearch that works perfectly well for a sorted array.
		sort();
		return Arrays.binarySearch(a, x);
	}
	
	public void deleteItem(final int x)
	{
		for (int i=0;i<a.length;i++)
		{
			if (x==a[i]) // If the item is found then fill the position with empty filler and sort the array again.
			{
				a[i] = emptyFiller;
				sort();
				break;
			}
		}
	}		
	
	public static void main(String[] args) 
	{
		System.out.println("Test case #1");
		System.out.println("============");
		System.out.println("Initialize ArrayPlay with default constructor using capacity 15...");
		ArrayPlay arrayPlayBasic = new ArrayPlay();
		arrayPlayBasic.sort();
		System.out.println("Array Capacity (after initialization) = "+arrayPlayBasic.capacity());
		System.out.println("Array empty ? "+arrayPlayBasic.isEmpty());
		System.out.println("Number of elements in the array = "+arrayPlayBasic.noOfItems());
		arrayPlayBasic.printArray();
		System.out.println("Delete Number 10 from the array...");
		arrayPlayBasic.deleteItem(10);
		System.out.println("Print Array after deleting above Number(s) from the array...");
		arrayPlayBasic.printArray();
		System.out.println("Add Number 12 in the array...");
		arrayPlayBasic.add(12);
		System.out.println("Print Array after adding above Number(s) in the array...");
		arrayPlayBasic.printArray();
		System.out.println("Element 4 exist in the array ? "+(arrayPlayBasic.find(4)==-1?"No.":"Yes, in position="+arrayPlayBasic.find(4)+"."));
		
		System.out.println("\nTest case #2");
		System.out.println("============");
		System.out.println("Initialize ArrayPlay with overriden constructor using capacity 20 and an initial specified array...");
		ArrayPlay arrayPlayAdvanced = new ArrayPlay(new int[]{2,4,6,8,1,5,6,3,10,12,15,66,7,32,23,45,43,21,76,33}, 20, 20);
		System.out.println("Sort the array");
		arrayPlayAdvanced.sort();
		System.out.println("Array Capacity = "+arrayPlayAdvanced.capacity());
		System.out.println("Array empty ? "+arrayPlayAdvanced.isEmpty());
		System.out.println("Number of elements in the array = "+arrayPlayAdvanced.noOfItems());
		arrayPlayAdvanced.printArray();
		System.out.println("Delete Number 10, 66, 33, 45, 44 (if found) from the array...");
		arrayPlayAdvanced.deleteItem(10);
		arrayPlayAdvanced.deleteItem(66);
		arrayPlayAdvanced.deleteItem(33);
		arrayPlayAdvanced.deleteItem(45);
		arrayPlayAdvanced.deleteItem(44);
		System.out.println("Print Array after deleting above number(s) from the array...");
		arrayPlayAdvanced.printArray();
		System.out.println("Add Number 22, 34 in the array...");
		arrayPlayAdvanced.add(new int[]{22,34});
		System.out.println("Print Array after adding above Number(s) in the array...");
		arrayPlayAdvanced.printArray();
		System.out.println("Add Number {2,4,5,65,23,34,43,53,56,111,3,9,69,96} in the array (as many as can fit in the array) ...");
		arrayPlayAdvanced.add(new int[]{2,4,5,65,23,34,43,53,56,111,3,9,69,96});
		System.out.println("Print Array after adding above Number(s) in the array....");
		arrayPlayAdvanced.printArray();
	}
}
