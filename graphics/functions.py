import math

def errorc(analytic_values, given):
    error_cuadratic = 0
    for i in range(0, len(given)):
        error = given[i] - analytic_values[i]
        error = error ** 2
        error_cuadratic += error
    return error_cuadratic / len(given)


def analytic(times):
    m = 70.0
    gamma = 100.0
    k = 10000.0
    return [math.exp(-(gamma*t)/(2*m)) * math.cos(math.sqrt(k/m - gamma ** 2/ (4* (m ** 2))) * t) for t in times]