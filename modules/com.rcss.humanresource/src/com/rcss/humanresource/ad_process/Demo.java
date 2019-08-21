package com.rcss.humanresource.ad_process;

import java.math.BigDecimal;

class Demo
{
public static void main(String args[])
 {
	String str = "08:15(in),12:14(out),1:15(in),4:45(out)";
        String[] temp;
 
  String delimiter = ",";
 
  temp = str.split(delimiter);
  int lngth=temp.length;
  System.out.println("length is.."+lngth);

  if(lngth==4){
  for(int i =0; i < lngth ; i++){
	  if(temp[i].contains("(in)"))
	   {
  			temp[i]=temp[i].replace("(in)", "");
	   }
else{
         temp[i]=temp[i].replace("(out)", "");
}
System.out.println(i+"th elementis.."+temp[i]);  
  }
          } 

else if(lngth==2)
{
   for(int i =0; i < lngth ; i++)
   {
	   if(temp[i].contains("(in)"))
			   {
		   			temp[i]=temp[i].replace("(in)", "");
			   }
	   else{
		          temp[i]=temp[i].replace("(out)", "");
	   }
       System.out.println(i+"th elementis.."+temp[i]);
   }
}
else if(lngth==3)
{
	for(int i =0; i < lngth ; i++){
		if(temp[i].contains("(in)"))
		   {
	   			temp[i]=temp[i].replace("(in)", "");
		   }
else{
	          temp[i]=temp[i].replace("(out)", "");
}
	if(i==0)
       System.out.println(i+"th elementis.."+temp[0]);
        if(i==lngth-1)
       System.out.println(i+"th elementis.."+temp[lngth-1]);}
       
}
else if(lngth>4)
{     
      for(int i =0; i < lngth ; i++){
    	  if(temp[i].contains("(in)"))
		   {
	   			temp[i]=temp[i].replace("(in)", "");
		   }
  else{
	          temp[i]=temp[i].replace("(out)", "");
  }
       if(i==0)
      System.out.println(i+"th elementis.."+temp[0]);
       if(i==lngth-1)
      System.out.println(i+"th elementis.."+temp[lngth-1]);
    }
}
else
{
	System.out.println("user is absent..");	
}
}
}