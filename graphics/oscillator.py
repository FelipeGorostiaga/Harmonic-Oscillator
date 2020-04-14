from matplotlib import pyplot as plt
import math
import subprocess

algorithms = ["Beeman", "Verlet", "GP"]
run_results = []
for r in range(0, 3):
    command = 'java -jar ../target/tp3-1.0-SNAPSHOT-jar-with-dependencies.jar -alg {alg}'.format(alg = algorithms[r])
    p = subprocess.Popen(command, shell=True, stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
    lines =  p.stdout.readlines()
    print(lines)
    positions = [float(lines[x].split()[0]) for x in range(0, len(lines))]
    times = [float(lines[x].split()[1]) for x in range(0, len(lines))]
    run = [positions, times]
    run_results.append(run)

analytics = analytic(run_results[0][1])

plt.style.use('seaborn')
plt.plot(analytics, run_results[0][1])
for i in range(0,3):
    plt.plot(run_results[i][0], run_results[i][1],legend=algorithms[i])


plt.xlabel('Time (s)')
plt.ylabel('<Desplazamiento Cuadratico Medio> (m^2/s)')
plt.show()

# Analytic solution
def analytic(times):
    m = 70.0
    gamma = 100.0
    k = 10000.0
    return [math.exp(-(gamma*t)/(2*m)) * math.cos(math.sqrt(k/m - gamma ** 2/ (4* (m ** 2))) * t) for t in times]

