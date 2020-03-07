package Tubes;

/**
 *
 * @author Sam
 */
// TuPro TBA

import java.util.*;
public class main {
    public static void main(String[] args) {
        //definisi variabel
        ArrayList<String> tokenlexic = new ArrayList(); 
        String formula;
        char arr[] = new char[100];
        Scanner sc = new Scanner(System.in);
        boolean validasi = true;
        Stack s = new Stack();

          
        //Tahap 1
        System.out.print("Masukan String: ");
            formula  = sc.nextLine();
            arr = formula.toCharArray();
            for (int i = 0; i < formula.length() ; i++) {
                    if (arr[i] == 'p' || arr[i] == 'q' || arr[i] == 'r' || arr[i] == 's'){
                        tokenlexic.add("1");
                    }else if (arr[i] == 'n' && arr[i+1] == 'o' && arr[i+2] == 't' && (arr[i+3] == ' ' 
                            || arr[i+3] == '(')){
                        tokenlexic.add("2");
                        i = i+2;
                    }else if (arr[i] == 'a' && arr[i+1] == 'n' && arr[i+2] == 'd' && (arr[i+3] == ' ' 
                            || arr[i+3] == '(')){
                        tokenlexic.add("3");
                        i = i+2;
                    }else if (arr[i] == 'o' && arr[i+1] == 'r' && (arr[i+2] == ' ' || arr[i+2] == '(')){
                        tokenlexic.add("4");
                        i = i+1;
                    }else if (arr[i] == 'x' && arr[i+1] == 'o' && arr[i+2] == 'r' && (arr[i+3] == ' ' 
                            || arr[i+3] == '(')){
                        tokenlexic.add("5");
                        i = i+2;
                    }else if (arr[i] == 'i' && arr[i+1] == 'f'){
                        if (arr[i+2] == 'f') {
                            tokenlexic.add("8");
                            i+=2;
                        }else if(arr[i+2] == ' ' || arr[i+2] == '('){
                            tokenlexic.add("6");
                            i++;
                        }else{
                            tokenlexic.add("0");
                            break;
                        }            
                    }else if (arr[i] == 't' && arr[i+1] == 'h' && arr[i+2] == 'e' && arr[i+3] == 'n' 
                              && (arr[i+4] == ' ' || arr[i+4] == '(')){
                        tokenlexic.add("7");
                        i = i+3;
                    }else if (arr[i] == '('){
                        tokenlexic.add("9");
                    }else if (arr[i] == ')'){
                        tokenlexic.add("10");
                    }else if (arr[i] == ' '){
                        
                    }else{
                        tokenlexic.add("0");
                        break;
                    }
            }//tutup for
            
            // output tahap 1
            System.out.print("Token Lexic: ");
            for (String i : tokenlexic) {
                if (i == "0") {
                    System.out.print("error ");
                }else{
                    System.out.print(i+" ");
            
                }
            }
            
            System.out.println("");
            
            
            //Tahap 2
            
            //CFG
            // S -> 1P | 2S | SPS | 9S10 | 2 9S10 | 9S10 P 9S10 | 6S7S | 6 9S10 7 9S10
            // P -> 3S | 4S | 5S | 8S | epsilon
            int i = 0;
            s.push("#");
            s.push("S");
            while (s.peek() != "#"){
                
//                // baris 99-104 untuk mengetahui iterasi stack
//                System.out.println("isi Stack :"+s.toString());
//                if (i < tokenlexic.size()) {
//                    System.out.println("Token Sekarang: "+ tokenlexic.get(i));
//                }
//                System.out.println("Stack Sekarang: "+ s.peek());
//                System.out.println(" ");
//                // baris 99-104 diatas untuk mengetahui iterasi stack 

                if(i < tokenlexic.size() && tokenlexic.get(i) == "0") {
                    validasi = false;
                    System.out.println("Token Lexic Error Sehingga Tidak Valid");
                    break;
                }//kondisi token lexic error
                
                if (s.peek() == "S") {
                    if (tokenlexic.get(i) == "1" || tokenlexic.get(i) == "2"
                        || tokenlexic.get(i) == "6" || tokenlexic.get(i) == "9") {
                        s.pop();
                        if (tokenlexic.get(i) == "1" && i < tokenlexic.size()) {
                            s.push("P");
                            i++;
                        }else if (tokenlexic.get(i) == "2" && i < tokenlexic.size()){
                            s.push("S");
                            i++;
                        }else if(tokenlexic.get(i) == "6" && i < tokenlexic.size()){
                            s.push("S");
                            s.push("7");
                            s.push("S");
                            i++;
                        }else if(tokenlexic.get(i) == "9" && i < tokenlexic.size()){
                            s.push("10");
                            s.push("S");
                            i++;
                        }
                    }else{
                        validasi = false;
                        break;
                    }
                }else if (s.peek() == "P") {
                    s.pop();
                    if (i < tokenlexic.size()){
                        if(tokenlexic.get(i) == "3" || tokenlexic.get(i) == "4"
                           || tokenlexic.get(i) == "5" || tokenlexic.get(i) == "8"){
                            if(tokenlexic.get(i) == "3" && i < tokenlexic.size()){
                                s.push("S");
                                i++;
                            }else if (tokenlexic.get(i) == "4" && i < tokenlexic.size()){
                                s.push("S");
                                i++;
                            }else if(tokenlexic.get(i) == "5" && i < tokenlexic.size()){
                                s.push("S");
                                i++;
                            }else if(tokenlexic.get(i) == "8" && i < tokenlexic.size()){
                                s.push("S");
                                i++;
                            }
                        }else if(tokenlexic.get(i) == "1"){
                            validasi = false;
                            break;
                        }
                    }
                }else if(s.peek() == "7"  && tokenlexic.get(i)=="7"){
                    s.pop();
                    i++;
                }else if(s.peek() == "10"){
                    s.pop();
                    i++;
                    if (i < tokenlexic.size()){
                        if(tokenlexic.get(i) == "1" || tokenlexic.get(i) == "3" 
                           || tokenlexic.get(i) == "4" || tokenlexic.get(i) == "5" 
                           || tokenlexic.get(i) == "8" || tokenlexic.get(i) == "9"){
                            
                            if (tokenlexic.get(i) == "1" && i < tokenlexic.size()) {
                               s.push("S");
                               
                            }else if (tokenlexic.get(i) == "3" && i < tokenlexic.size()) {
                               s.push("P");
                               
                            }else if (tokenlexic.get(i) == "4" && i < tokenlexic.size()) {
                               s.push("P");
                               
                            }else if (tokenlexic.get(i) == "5" && i < tokenlexic.size()) {
                               s.push("P");
                               
                            }else if (tokenlexic.get(i) == "8" && i < tokenlexic.size()) {
                               s.push("P");
                               
                            }else if (tokenlexic.get(i) == "9" && i < tokenlexic.size()) {
                               s.push("10");
                               s.push("S");
                               
                            }
                        }
                    }
                }else{
                    validasi = false;
                    break;
                } 
            }//end while
            
//            System.out.println("Stack Terakhir: " + s.toString()); //cek last stack
            if (s.peek() == "#") {
                s.clear();
            }
            
            // output tahap 2
            System.out.print("Validasi: ");
            if (s.isEmpty() && validasi) {
                System.out.print("Valid");
            }else{
                System.out.print("Tidak Valid");
            }
            System.out.println("");
        }//tutup psvm
    }// tutup program


