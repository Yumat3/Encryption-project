class Main {
  public static void main(String[] args) {
    (new Main()).init();
  }
  void print(Object o){ System.out.println(o);}
  void printt(Object o){ System.out.print(o);}

  void init(){

    // This example we are substituting all lower case 
    // letters to another lower case letter.
    char[] sub = {'a','e','i','o','u','k','s','t','n','h','m','y','r','w'};

	char[] sub2 = {'\u3042', '\u3048', '\u3044', '\u304A', '\u3046', '\u304B', '\u3055', '\u305F', '\u306A', '\u306F', '\u307E', '\u3084', '\u3089', '\u308F'};

    // Encoding message
    String file = Input.readFile("test.txt");

    //substituion
    String encodedMsg1 = subEncryption(file,sub,sub2);
    Input.writeFile("Encode1.txt",encodedMsg1);

	print(level5("1234567",5));
	print("________________");
	print(level6("123456678911",5));
	print("________________");
    // caesar cipher
	print("Enter your min");
    int min = Input.readInt();
	print("Enter your max");
    int max = Input.readInt();
	int ade = randInt(min,max);
    String encodedMsg2 = encode(encodedMsg1,ade);
    Input.writeFile("Encode2.txt",encodedMsg2);

    // reverse
    String encodedMsg3 = reverse(encodedMsg2);
    Input.writeFile("Encode3.txt",encodedMsg3);
	
	String encodedMsg4 = level5(encodedMsg3,ade);
    Input.writeFile("Encode4.txt",encodedMsg4);
	
	String encodedMsg5 = level7(encodedMsg4);
    Input.writeFile("Encode5.txt",encodedMsg5);
    
    // decoding message
    String file2 = Input.readFile("Encode5.txt");
    
	String decodedMsg1 = level8(file2);
    Input.writeFile("Decode1.txt", decodedMsg1);
	
	String decodedMsg2 = level6(decodedMsg1, ade);
    Input.writeFile("Decode2.txt", decodedMsg2);
	
    String decodedMsg3 = reverse(decodedMsg2);
    Input.writeFile("Decode3.txt", decodedMsg3);
    
    String decodedMsg4 = decode(decodedMsg3,ade);
    Input.writeFile("Decode4.txt", decodedMsg4);
    
    String decodedMsg5 = subEncryption(decodedMsg4, sub2, sub);
    Input.writeFile("Decode5.txt", decodedMsg5);
    
    
  }
  // reverse string
  String reverse(String s){
    String bld ="";
    for(int x=0; x<= s.length()-1; x++){
      bld = s.charAt(x) + bld;
    }
    return bld;
  }
  
  String level7(String s){
	String bld="";
    int len=s.length();
    String fHalf = s.substring(0,len/2);
    String sHalf = s.substring(len/2);
    sHalf = reverse(sHalf);

    for(int x=0; x<=fHalf.length()-1;x++){
      bld+=fHalf.substring(x,x+1);
      bld+=sHalf.substring(x,x+1);
    }
    if(len%2==1)
      bld+=sHalf.substring(sHalf.length()-1);
    return bld;  
  }
  String level8(String s){
	String bld="";
    int len=s.length();
    String fHalf = "";
    String sHalf = "";
    for(int x=0; x<=s.length()-4;x++){
      fHalf+=s.charAt(x * 2);
      sHalf+=s.charAt(x * 2 + 1);
	}
    if (len % 2 == 1) {
        fHalf+=s.charAt(len - 1);
    }
	String srev=reverse(sHalf);
	return fHalf.toString()+srev; 
  }
  //Cipher encoding with no wrapping
  String encode(String s, int ade){
    String bld="";
    int ascii;
    char ch='\0';
    for(int x=0; x<=s.length()-1;x++){
      ch=s.charAt(x);
      ascii=(int)ch;
      ascii+=ade;
      bld+= (char)ascii;
    }
     
    return bld;
  }

  
  String decode(String s, int sde){
    String bld="";
    int ascii;
    char ch='\0';
    for(int x=0; x<=s.length()-1;x++){
      ch=s.charAt(x);
      ascii=(int)ch;
      ascii-=sde;
      bld+= (char)ascii;
    }
    return bld;
  }

  // Substituion encoding
  String subEncryption(String s, char[] sub, char[] sub2){
    String bld="";
    char ch ='\0';
    int index=0;
    for(int x=0; x<=s.length()-1; x++){
      ch=s.charAt(x);
      index=indexOf(ch,sub);
      if(index!=-1){
        bld+=sub2[index];
      }
      else{
        bld+=ch;
      }
    }
    return bld;
  }
  String level5(String s, int ade){
    String bld="";
    char ch ='\0';
    int ascii;
    for(int x=0; x<=s.length()-5; x+=4){
	  ch=s.charAt(x);
	  ascii=(int)ch;
      ascii+=ade;
	  bld+=s.substring(x,x+5);
	  bld+= (char)ascii;	  
	  int rem = s.length()%5;
	  if (rem > 0) {
        bld+=(s.substring(s.length() - rem)); 
	  }
	}
	return bld;
  }
  String level6(String s, int ade){
    String bld="";
    char ch ='\0';
	int ascii;
    for(int x=0; x<=s.length()-6; x+=6){
	  bld+=s.substring(x,x+5);
      ch = s.charAt(x + 5); 
      ascii = (int) ch;
      ascii -= ade;
	  int rem = s.length()%6;
	  if (rem > 0) {
        bld+=(s.substring(s.length() - rem)); 
	  }
	}
	return bld;
  }
//  }
  
  int indexOf(char ch, char[] arry){
    for(int x=0; x<=arry.length-1; x++){
      if(arry[x]==ch){
        return x;
      }
    }
    return -1;
  }
  int randInt(int lower, int upper){
    int range = upper - lower;
    return (int)(Math.random()*range+lower);
  }

}