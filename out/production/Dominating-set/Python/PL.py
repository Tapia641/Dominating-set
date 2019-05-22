#AUTOR: HERNÁNDEZ TAPIA LUIS ENRIQUE ;)
import sys
import numpy as np
from scipy.optimize import linprog
import os

#VARIABLES GLOBALES
Grafo = []
Pesos = []
NodosDominantes = []

def PL_Ejemplo():
    #Ejemplo de Minimizar
    F = [430,460,420] #Funcion objetivo: 430Y1 + 460Y2 + 420Y3
    #Sujeto A: <=
    Lado_Izquierdo = [[(-1)*(1),(-1)*(3),(-1)*(-1)],[(-1)*(2),(-1)*(0),(-1)*(4)],[(-1)*(1),(-1)*(2),(-1)*(0)]]
    Lado_Derecho = [-1*(3),-1*(2),-1*(5)]
    Resultado = linprog(F,Lado_Izquierdo,Lado_Derecho, bounds=(0,None))
    print("Valor = ", Resultado.fun, "Yi = ", Resultado.x)

def Leer():

    #DAMOS LECTURA A NUESTRO ARCHIVO GENERADO POR JAVA
    f = open(os.path.abspath("src/Python/PL.txt"),'r')
    data = []
    for line in f.readlines():
        data.append(line.replace('\n','').split(' '))
    f.close()

    F = [] #Funcion objetivo
    #Sujeto A tipo matriz
    Lado_Izquierdo = []
    Lado_Derecho = []

    #Resultado = linprog(F,Lado_Izquierdo,Lado_Derecho, bounds=(0,None))

    #FILTRAMOS TODOS LOS DATOS
    for i in range(0,len(data)):
        if(i!=0):
            Grafo.append(data[i])
    for i in Grafo:
        j  = len(i)-1
        Pesos.append(i[j])
    NodosDominantes = data[0]

    print("Grafo: ",Grafo)
    print("Pesos: ",Pesos)
    print("Nodod dominantes: ",NodosDominantes)

    #OBTENEMOS LA FUNCION OBJETIVO
    for i in range(0,len(Grafo)):
        if Grafo[i][0] in NodosDominantes:
            F.append(Pesos[i])
        else:
            F.append(0)
    
    print("Funcion objetivo: ",F)

    for x in range(0,len(Grafo)):
        AuxLadoI = []
        for i in Grafo:
            Aux = []

            for j in range(1,len(i)-1):
                Aux.append(i[j])

            if Grafo[x][0] in Aux:
                #print(Grafo[x][0], " esta en  ", Aux)
                AuxLadoI.append(1)
            else:
                #print(Grafo[x][0], " no esta en  ", Aux)
                AuxLadoI.append(0)
        Lado_Izquierdo.append(AuxLadoI)

    print(Lado_Izquierdo)
    for i in range(0,len(Grafo)):
        Lado_Derecho.append(1)
    print(Lado_Derecho)
    
    Resultado = linprog(F,Lado_Izquierdo,Lado_Derecho, bounds=(0,None))
    print("Valor = ", Resultado.fun, "Yi = ", Resultado.x)

def Iniciar():
    if len(sys.argv) >= 2:
            print ("La cadena introducida tiene",len(sys.argv[1]),"caracteres");
    else:
            print ("Este programa necesita un parámetro");

if __name__ == "__main__":
    Leer()
    PL_Ejemplo()