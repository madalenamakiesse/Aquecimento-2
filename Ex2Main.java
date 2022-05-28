/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercava.Ex2;
import java.util.*;
/**
 *
 * @author Madalena Makiesse
 */
public class Ex2Main {
    static Scanner ler = new Scanner(System.in);
    static Contato[] listTelefonica= new Contato[99];
    static int elem=0;
    static int ano=2021;
    static void preencher(Contato[] list){
        Contato list1= new Contato(); 
        list[elem]=prencherLista(list1);
        elem++;
    }
    static boolean avTelefone(int telefone){
        return telefone/100000000<1 || telefone/100000000>10 || telefone/100000000!=9 || (telefone/10000000<91 || telefone/10000000>94);
    }
    static boolean veriAno(int ano1){
        return ano-ano1<=4 || ano-ano1>=120 ;
    }
    static boolean veriMes(int mes1){
        return (mes1<1 || mes1>12);
    }
    static int veridia(int ano,int mes,int dia){
        int veri;
        if(dia<=0) veri=0;
        else if((mes==2 && ((ano%4==0 ||ano%400==0) && dia>29 ) || dia>28)) veri=0;
        else if((mes==1 || mes==3 || mes==5 || mes==7 || mes==8 || mes==10 || mes==12) && dia>31 ) veri=0;
        else if((mes==4 || mes==6 || mes==9 || mes==11) && dia>30 ) veri=0;
        else veri=1;
        return veri;
    }
    static Contato prencherLista(Contato list){
        list.nome=new String();
        ler.nextLine();
        System.out.print("Nome: ");
        list.nome=ler.nextLine();
        while(avTelefone(list.telefone)){
            System.out.print("Telefone(sem espaços): ");
            list.telefone=ler.nextInt();
        }
        list.diaDNascimento= new Data();
        System.out.println("Data de Nascimento: ");
        while(veriAno(list.diaDNascimento.ano)){
        System.out.print("Ano: ");
        list.diaDNascimento.ano=ler.nextInt();
        }
        while(veriMes(list.diaDNascimento.mes)){
        System.out.print("Mês: ");
        list.diaDNascimento.mes=ler.nextInt();
        }
        while(veridia(list.diaDNascimento.ano,list.diaDNascimento.mes,list.diaDNascimento.dia)==0){
        System.out.print("Dia: ");
        list.diaDNascimento.dia=ler.nextInt();
        }
        return list;
    }
    static Contato[] ordenarPorNome(Contato[] list){
        Contato aux;
        int i,j;
        if(elem>1){
        for(i=1;i<elem;i++){
            aux=list[i];
            for(j=i-1; j>=0 && aux.nome.compareTo(list[j].nome)<0;j--){
                list[j+1]=list[j];
            }
            list[j+1]=aux;
        }}
        return list;
    }
    static int data(Contato ano1,Contato ano2){
        int veri=0;
        if(ano1.diaDNascimento.ano>ano2.diaDNascimento.ano) veri=1;
        if(ano1.diaDNascimento.ano == ano2.diaDNascimento.ano && ano1.diaDNascimento.mes>ano2.diaDNascimento.mes) veri=1;
        if(ano1.diaDNascimento.ano == ano2.diaDNascimento.ano && ano1.diaDNascimento.mes==ano2.diaDNascimento.mes && ano1.diaDNascimento.dia>ano2.diaDNascimento.dia) veri=1;
        return veri;
    }
    static Contato[] ordenarPorData(Contato[] list){
        Contato aux;
        int i,j;
        if(elem>1){
        for(i=1;i<elem;i++){
            aux=list[i];
            for(j=i-1; j>=0 && data(aux,list[j])!=0;j--){
                list[j+1]=list[j];
            }
            list[j+1]=aux;
        }}
        return list;
    }
    static void impressao(Contato[] list){
        int i=1;
        for(var cont :list){
                      if(cont!=null){
                         System.out.println(i+"-    "+cont.nome+" : "+cont.telefone); 
                         i++;
                      }
                   }
    }
    static void menu(){
        int op=1;
        while(op==1 || op==2 || op==3){
            System.out.println("\n  1-Preencher a lista de contactos\n  2-Apresentar a lista de forma ordenada por nome\n  3-Apresentar a lista de forma ordenada pela dada de nascimento\n Prima qualquer número diferente de '1' , '2' e '3' para sair");
            op= ler.nextInt();
            if(op==1){
                if(elem<99){
                    preencher(listTelefonica); 
                }
                else System.out.println("Não existe mais espaço na lista telefónica.");
            }
            if(elem==0){ 
                preencher(listTelefonica);
                menu();
                }
           else{
               if(op==2){
                  impressao(ordenarPorNome(listTelefonica));
                }
                if(op==3){
                  impressao(ordenarPorData(listTelefonica));
                  }
           }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        menu();
    }
    
}
