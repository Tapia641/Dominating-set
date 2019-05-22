#AUTOR: HERNÁNDEZ TAPIA LUIS ENRIQUE ;)

import sys
from scipy.optimize import linprog


def PL():
    #Ejemplo de Minimizar
    F = [430,460,420] #Funcion objetivo: 430Y1 + 460Y2 + 420Y3
    #Sujeto A:
    Lado_Izquierdo = [[(-1)*(1),(-1)*(3),(-1)*(-1)],[(-1)*(2),(-1)*(0),(-1)*(4)],[(-1)*(1),(-1)*(2),(-1)*(0)]]
    Lado_Derecho = [-1*(3),-1*(2),-1*(5)]
    Resultado = linprog(F,Lado_Izquierdo,Lado_Derecho, bounds=(0,None))
    print("Valor = ", Resultado.fun, "Yi = ", Resultado.x)


def Iniciar():
    if len(sys.argv) >= 2:
            print ("La cadena introducida tiene",len(sys.argv[1]),"caracteres");
    else:
            print ("Este programa necesita un parámetro");

if __name__ == "__main__":
    PL()