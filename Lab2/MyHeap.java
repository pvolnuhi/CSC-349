class MyHeap{
	int heapArray[]; //empty heap
	int capacity; //50
	int size;


	public MyHeap(){
		this.capacity = 50;
		this.heapArray = new int[51];
		this.size = 0;
	}

	public MyHeap(int capacity){
		this.capacity = capacity;
		this.size = 0;
		this.heapArray = new int[capacity + 1];
	}

	public boolean buildHeap(int[] arrayofint){
		if(arrayofint.length > capacity){
			return false;
		}else{
			System.arraycopy(arrayofint, 0, heapArray, 1, arrayofint.length);
			size = arrayofint.length;
			for(int i = size/2; i>=1; i--){
				driftDown(i);
			}
			return true;
		}

	}

	public static int[] heapSortDecreasing(int[] arrayofint){
		int[] sorted = new int[arrayofint.length];
		MyHeap h=new MyHeap(arrayofint.length);
		h.buildHeap(arrayofint);
		
		for(int i=0; i < arrayofint.length; i++){
			int deleted=h.deleteMin();
			sorted[sorted.length-1-i]=deleted;
		}
		return sorted;
	}

	public boolean insert(int insertnum){
		if(size == capacity){
			return false; //not large enough to hold 'array of int'
		}else{
			heapArray[++size] = insertnum;
			driftUp(size);
			return true;
		}

	}

	public int findMin(){
		return heapArray[1];
	}

	public int deleteMin(){
		int minimum = heapArray[size];
		size--;
		driftDown(1);
		return minimum;
	}

	public boolean isEmpty(){
		if(size == 0){
			return true;
		}else{
			return false;
		}

	}

	public boolean isFull(){
		if(size == capacity){
			return true;
		}else{
			return false;
		}

	}

	public void driftUp(int idx){
		int temp = heapArray[idx];
		while(idx>1 && temp<heapArray[idx/2]){
			heapArray[idx] = heapArray[idx/2];
			idx = idx / 2;
		}
		heapArray[idx] = temp;

	}

	public void driftDown(int idx){
		int temp = heapArray[idx];
		while((idx * 2) <= size){
			int c = idx*2;
			
			if(c!=size && (heapArray[c+1]<heapArray[c])){
				c++;
			}
			if(heapArray[c]<temp){
				heapArray[idx]=heapArray[c];
				idx=c;
			}else{
				break;
			}
		}
		heapArray[idx]=temp;
	}

	public int getHeapCap(){
		return this.capacity;
	}

	public int getHeapSize(){
		return this.size;
	}

}