// javac MyStack.java && java MyStack
public class MyStack<E> implements DelayedStack<E>{
    public int size;
    public int index;
    public int currDelay;
    public int maxDelay;
    public int maxSize;
    public int newDelay;
    public E[] a;
    public boolean prevPop;
    public boolean delayChange;

    @SuppressWarnings("unchecked") 
    public MyStack(int delayIn){
        // if(delayIn<0){delayIn=0;}
        maxDelay=delayIn;
        currDelay=0;
        size=0;
        index=-1;
        prevPop=false;
        delayChange=false;
        newDelay=0;
        maxSize=50;
        
        a = (E[])new Object[maxSize];
        // a = new E [500]; not allowed generic array
        
    }


    // public static void main(String[] args ){
        // DelayedStack<String> s = new MyStack<String>(2);
        // s.push("1");
        // s.push("2");
        // s.push(null);
        // s.push("3");
        // s.push("4");
        // System.out.println(s.pop());
        // System.out.println(s.clear());
        // System.out.println(s.contains(null));
        // System.out.println(s.pop());

        // s.push(4);
        // s.push(5);
        // System.out.println(s.size());

    // }

	public int size(){
        return size;

    }
    @SuppressWarnings("unchecked") 
    public void resize(){
        E[] old=a;
        maxSize*=2;
        a = (E[])new Object[maxSize];
        for(int i=0;i<maxSize/2;i++){
            a[i]=old[i];
        }
    }

	public void push(E element){
        if(index==maxSize-1){
            this.resize();
        }

        if (prevPop){
            currDelay=0;
            if(delayChange){
                maxDelay=newDelay;
            }
        }
        prevPop=false;
        currDelay++;
        size++;
        index++;
        a[index]=element;

        
    }
    @SuppressWarnings("unchecked")
	public E pop() throws IllegalStateException{
        if (this.size()==0){
            throw new IllegalStateException();
        } 

        if(prevPop || currDelay>=maxDelay){
            index--;
            size--;
            prevPop=true;
            return a[index+1];
        }      
        else {return null;}
    }
    @SuppressWarnings("unchecked")
    public E peek() throws IllegalStateException{
        if (this.size()==0){
            throw new IllegalStateException();
        }       
        else {return a[index];}
    }

    public int getDelay(){
        return Math.max(maxDelay-currDelay,0) ;
    }

    public void setMaximumDelay(int d){
        delayChange=true;
        newDelay=d;
    }

    public int getMaximumDelay(){
        if(delayChange){
            return newDelay;
        }
        return maxDelay;
    }

    public boolean clear(){
        // if (this.size()==0){
        //     return false;
        // }    
        if(prevPop || currDelay>=maxDelay){
            index =-1;
            size =0;
            prevPop=true;
            return true;
        }      
        else {return false;}
    }

    @SuppressWarnings("unchecked")
    public boolean contains(E elem){
        for(int i=0;i<this.size();i++){
            if(elem==null || a[i]==null){
                if(a[i]==elem){return true;}                
            }
            else{
                if(a[i].equals(elem)){return true;}
            }
        }
        return false;
    }

}
