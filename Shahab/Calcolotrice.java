package com.Shahab.practice.Shah;

import java.awt.*;
import java.applet.Applet;

public class Calcolotrice extends Applet {

Label display;      // Il display
Button bottoni [];  // L'array dei bottoni
/* Costanti per gli indici dei tasti */
static final int VIRGOLA =10;
static final int PIU = 11;
static final int MENO = 12;
static final int PER = 13;
static final int DIVISO = 14;
static final int UGUALE = 15;
static final int C = 16;
static final int CE = 17;

double dispVal;  
double acc;       
/* Gestione input */
boolean decs;     
double curDec;    
int oldOper = 0;  
  /* Inizializzazione: crea l'interfaccia */
  public void init () {
  int i;
  GridBagLayout gridbag = new GridBagLayout ();    
  GridBagConstraints c = new GridBagConstraints(); 

    /* Inizializzazione */
    dispVal = 0.0;
    acc = 0.0;
    decs = false;

    setLayout (gridbag);             
    display = new Label ("0.0");    
    display.setBackground (Color.orange);
    bottoni = new Button [18];      
    /* Crea i dieci bottoni numerici */
    for (i = 0; i < 10; i++)
      bottoni [i] = new Button (String.valueOf (i));
    /* Bottoni con i simboli */
    bottoni [VIRGOLA] = new Button (",");
    bottoni [PIU] = new Button ("+");
    bottoni [MENO] = new Button ("-");
    bottoni [PER] = new Button ("x");
    bottoni [DIVISO] = new Button ("/");
    bottoni [UGUALE] = new Button ("=");
    bottoni [C] = new Button ("C");
    bottoni [CE] = new Button ("CE");

    /* Inizializza i 'constraints' delle celle */
    c.fill = GridBagConstraints.BOTH;          
    c.weightx = 1.0;                          
    c.weighty = 1.0;                           
    c.gridheight = 1;                      

    /* Prima riga - il solo display */
    c.gridwidth = GridBagConstraints.REMAINDER;
    gridbag.setConstraints(display, c);       
    add(display);                              

    /* Seconda riga: ' 1  2  3  CE C ' */
    c.gridwidth = 1;                          
    gridbag.setConstraints(bottoni [1], c);   
    add(bottoni [1]);                         
    gridbag.setConstraints(bottoni [2], c);   
    add(bottoni [2]);                         
    gridbag.setConstraints(bottoni [3], c);    
    add(bottoni [3]);                          
    gridbag.setConstraints(bottoni [CE], c);   
    add(bottoni [CE]);                         
    c.gridwidth = GridBagConstraints.REMAINDER;
    gridbag.setConstraints(bottoni [C], c);    
    add(bottoni [C]);                         

    /* Terza riga: ' 4  5  6    =    ' */
    c.gridwidth = 1;                          
    gridbag.setConstraints(bottoni [4], c);    
    add(bottoni [4]);                          
    gridbag.setConstraints(bottoni [5], c);    
    add(bottoni [5]);                          
    gridbag.setConstraints(bottoni [6], c);    
    add(bottoni [6]);                          
    c.gridwidth = GridBagConstraints.REMAINDER;
    gridbag.setConstraints(bottoni [UGUALE], c);
    add(bottoni [UGUALE]);                     

    /* Quarta riga: ' 7  8  9  +  - ' */
    c.gridwidth = 1;                           
    gridbag.setConstraints(bottoni [7], c);   
    add(bottoni [7]);                         
    gridbag.setConstraints(bottoni [8], c);   
    add(bottoni [8]);                         
    gridbag.setConstraints(bottoni [9], c);    
    add(bottoni [9]);                          
    gridbag.setConstraints(bottoni [PIU], c); 
    add(bottoni [PIU]);                        
    c.gridwidth = GridBagConstraints.REMAINDER;
    gridbag.setConstraints(bottoni [MENO], c); 
    add(bottoni [MENO]);                       

    /* Quinta ed ultima riga: ' ,    0    *  / ' */
    c.gridwidth = 1;                             
    gridbag.setConstraints(bottoni [VIRGOLA], c); 
    c.gridwidth = 2;                              
    gridbag.setConstraints(bottoni [0], c);       
    add(bottoni [0]);                            
    c.gridwidth = 1;                              
    gridbag.setConstraints(bottoni [PER], c);     
    add(bottoni [PER]);                           
    c.gridwidth = GridBagConstraints.REMAINDER;   
    gridbag.setConstraints(bottoni [DIVISO], c);  
    add(bottoni [DIVISO]);                        
  }

  /* Metodo che gestisce la costruzione del numero */
  private void addCifra (int cifra) {
    if (cifra == VIRGOLA) {        
      if (!decs) {                  
        decs=true;                  
        curDec = 1.0;               
      }
    } else {                       
      if (decs) {                   
        curDec /= 10.0;            
        dispVal += curDec * cifra;  
      } else {                   
        dispVal *= 10.0;            
        dispVal += cifra;           
      }
    }

    display.setText (String.valueOf (dispVal)); 
  }

  /* Merodo che esegue le operazioni */
  private void oper (int op) {
    switch (oldOper) {         
                               
      case PIU :              
        acc += dispVal;
        break;
      case MENO :              
        acc -= dispVal;
        break;
      case PER :               
        acc *= dispVal;
        break;
      case DIVISO :            
        if (dispVal != 0.0) {  
          acc /= dispVal;
        }
        break;
      case UGUALE :            
        if (dispVal != 0.0) { 
          acc = dispVal;       
        }
        break;
      default :               
        acc = dispVal;         
    }
    dispVal = 0.0;           
    decs = false;              
    display.setText (String.valueOf (acc)); 
    oldOper = op;             
  }

  /* metodo che cancella (tasti C e CE) */
  private void canc (boolean isAll) {
    dispVal = 0.0;      decs = false;   
    if (isAll) {    
      acc = 0.0;    
      oldOper = 0;  
    }
    display.setText (String.valueOf (dispVal)); 
  }

  /* Gestisce tutti i tasti */
  public boolean action(Event event, Object arg) {
  int i;
    for (i = 0; i <= VIRGOLA; i++)       
      if (event.target == bottoni [i]) { 
        addCifra (i);                    
        return true;
      }
    for (i = PIU; i <= UGUALE; i++)      
      if (event.target == bottoni [i]) { 
        oper (i);                        
        return true;
      }
    /* Tasti 'C' e 'CE' */
    if (event.target == bottoni [C] || event.target == bottoni [CE]) {
      canc (event.target == bottoni [C]);
      return true;
    }
    return true;
  }

}