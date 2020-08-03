/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alphabeta;

/**
 *
 * @author JUI
 */
import java.io.*;
import java.util.*;
public class Alphabeta{
 
 
 static int turns;
 static int branch;
 static int [] sets = {3,12,8,2,4,6,14,5,2};
 static HashMap<Integer,Integer> mapper;
 static int setsCounter = 0;
 static int complexity = 0;
 
 
 
 static void MIN(int own,int child){
  if(!mapper.containsKey(own)){
   mapper.put(own,mapper.get(child));
  }
  else{
   mapper.put(own,Math.min(mapper.get(own),mapper.get(child)));
  }
 }
 
 
 
 
     static void MAX(int own,int child){
  if(!mapper.containsKey(own)){
   mapper.put(own,mapper.get(child));
  }
  else{
   mapper.put(own,Math.max(mapper.get(own),mapper.get(child)));
  }
 }
 
 
 
 
 
 static void alphabeta(int parent,int own,int label){
  if(label == turns*2){
   // leaves nodes...
   mapper.put(own,sets[setsCounter]);
   setsCounter++;
   complexity++;
  }
  else{
   // see first two childs..
   ArrayList<Integer> list = new ArrayList<Integer>();
   int start = own*branch;
   list.add(start);list.add(start+1);
   
   // see all the childs...
   for(int c = 0;c<branch-2;c++)list.add(--start);
   Collections.sort(list);
   
   
   for(int c = 0;c<list.size();c++){
    int child = list.get(c);
    
    alphabeta(own,child,label+1);
    
    //backtrack...
    if(!mapper.containsKey(parent)){
     if(label%2 != 0){
      MIN(own,child);
     }
     else{
      MAX(own,child);
     }
    }
    else if(label%2 != 0){
     // min ber
     MIN(own,child);
     
     //pruning part....
     if(mapper.get(parent)>=mapper.get(own)){
         //alpha max
      // pruned...
      setsCounter += (branch - (c+1));
      break;
     }
    }
    else{
     // max ber
     MAX(own,child);
     if(mapper.get(own)>=mapper.get(parent)){
//own alpha      
// pruned...
      setsCounter += (branch - (c+1));
      break;
     }
    }
   }
  }
 }
 
 
 
 

 
 public static void main(String[] args) throws IOException {
  // TODO Auto-generated method stub
  Scanner k = new Scanner(System.in);
  mapper = new HashMap<Integer,Integer>();
  System.out.println("turns");
  turns = k.nextInt();
  System.out.println("branch");
  branch = k.nextInt();
  
  
  alphabeta(-1,1,0);
  
  
  
  System.out.println("Depth "+(turns*2));
  System.out.println("Riyads Value "+mapper.get(1));
  System.out.println("Before alpha-beta "+(int)Math.pow(branch,(turns*2)));
  System.out.println("After alpha-beta "+complexity);
  
  
  
  
 }
 
 
 

 
 
 
 
 
 

}
