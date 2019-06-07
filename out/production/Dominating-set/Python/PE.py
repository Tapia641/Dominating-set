#AUTOR: HERNÁNDEZ TAPIA LUIS ENRIQUE ;)

"""
Los modelos de programación entera son una extensión de los modelos
lineales en los que algunas variables toman valores enteros.
"""

import sys
import numpy as np
from scipy.optimize import linprog
import os
import math
import random
import pulp as plp
import pandas as pd
#VARIABLES GLOBALES

F = [] #Funcion objetivo
#Sujeto A tipo matriz
Lado_Izquierdo = []
Lado_Derecho = []

#VARIABLES GLOBALES
Grafo = []
Pesos = []
NodosDominantes = []


def PE_Ejemplo():
    n = len(F)
    m = len(Lado_Derecho)
    set_I = range(0, n-1)
    set_J = range(0, m-1)

    c = {(i,j): random.normalvariate(0,1) for i in set_I for j in set_J}
    a = {(i,j): random.normalvariate(0,5) for i in set_I for j in set_J}

    l = {(i,j): random.randint(0,10) for i in set_I for j in set_J}#para crear x_i_j
    u = {(i,j): random.randint(10,20) for i in set_I for j in set_J}

    b = {j: random.randint(0,30) for j in set_J}

    opt_model = plp.LpProblem(name="MIP Model")

    # if x is Binary
    x_vars  = {(i,j): plp.LpVariable(cat=plp.LpBinary, name="x_{0}_{1}".format(i,j)) 
                for i in set_I for j in set_J}

    # if x is Integer
    #x_vars  = {(i,j):
     #           plp.LpVariable(cat=plp.LpInteger, 
      #          lowBound=l[i,j], upBound= u[i,j],
       #         name="x_{0}_{1}".format(i,j)) 
       #         for i in set_I for j in set_J
       #         }

    # == constraints
    constraints = {j : 
    plp.LpConstraint(
                e=plp.lpSum(Lado_Izquierdo[i][j] * x_vars[i,j] for i in set_I),
                sense=plp.LpConstraintEQ,
                rhs=Lado_Derecho[j],
                name="constraint_{0}".format(j))
                for j in set_J}


    objective = plp.lpSum(x_vars[i,j] * c[i,j] 
                    for i in set_I 
                    for j in set_J)
    # for maximization
    #opt_model.sense = plp.LpMaximize
    # for minimization
    opt_model.sense = plp.LpMinimize
    opt_model.setObjective(objective)

    # solving with CBC
    opt_model.solve()
    print(opt_model)

    # solving with Glpk
    #opt_model.solve(solver = GLPK_CMD())
    opt_df = pd.DataFrame.from_dict(x_vars, orient="index", 
                                columns = ["variable_object"])
    opt_df.index = pd.MultiIndex.from_tuples(opt_df.index, 
                                names=["column_i", "column_j"])
    opt_df.reset_index(inplace=True)
    # PuLP
    opt_df["solution_value"] = opt_df["variable_object"].apply(lambda item: item.varValue)
    opt_df.drop(columns=["variable_object"], inplace=True)
    opt_df.to_csv("./optimization_solution.csv")
    print(opt_df)

def Leer(): 

    #DAMOS LECTURA A NUESTRO ARCHIVO GENERADO POR JAVA
    #PRUEBAS COn JAVA
    f = open(os.path.abspath("src/Python/PL.txt"),'r')
    #PRUEBAS CON PYTHON
    #f = open(os.path.abspath("PL.txt"),'r')
    data = []
    for line in f.readlines():
        data.append(line.replace('\n','').split(' '))
    f.close()

    #Resultado = linprog(F,Lado_Izquierdo,Lado_Derecho, bounds=(0,None))

    #FILTRAMOS TODOS LOS DATOS
    for i in range(0,len(data)):
        if(i!=0):
            Grafo.append(data[i])
    for i in Grafo:
        j  = len(i)-1
        Pesos.append(i[j])
    NodosDominantes = data[0]

    #print("Pesos: ",Pesos)
    #print("Nodod dominantes: ",NodosDominantes)

    #OBTENEMOS LA FUNCION OBJETIVO
    for i in range(0,len(Grafo)):
        #F.append(1)
        F.append(Pesos[i])

    #print("Funcion objetivo: ",F)

    for x in range(0,len(Grafo)-1):
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

    #print("Lado izquierdo: ", Lado_Izquierdo)
    for i in range(0,len(Grafo)-1):
        Lado_Derecho.append(1)
    #print("Lado Derecho: ", Lado_Derecho)

if __name__ == "__main__":
    Leer()
    PE_Ejemplo()