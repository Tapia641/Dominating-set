from scipy import optimize

res = optimize.linprog(
    c = [1, 1, 1, 1, 1, 1, 1],
    A_ub=[[-1, -1,0,0,0,0,0],[-1,0,-1,-1,0,0,0],[0,-1,-1,0,-1,0,0,],[0,0,0,-1,0,-1,-1],[0,0,0,0,-1,-1,-1],[0,0,0,-1,-1,-1,0]], 
    b_ub=[-1,-1,-1,-1,-1,-1],
    bounds=(0, None),
    method='simplex'
)

print(res.fun, " xi ", res.x)