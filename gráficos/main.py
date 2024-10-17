import matplotlib.pyplot as plt


def main():
    execution_time_comparison_with_16_threads()
    execution_time_comparison_with_8_threads()
    execution_time_comparison_with_4_threads()
    execution_time_comparison_with_2_threads()

def execution_time_comparison_with_16_threads():
    runs = ['1', '2', '3', '4', '5', '6', '7']

    fork_join = [1664.05, 1625.6, 1730.7, 2086.8, 1510.7, 1501.1, 1562.8]
    concurrency_api = [1653.45, 1582.8, 1578.15, 1453.15, 1461.5, 1461.2, 1461.95]
    sequential = [2430.35, 2427.4, 2374.75, 2464.1, 2439.25, 2414.5, 2421.65]

    plt.figure(figsize=(10, 6))

    plt.plot(runs, fork_join, label='ForkJoin', marker='o', color='red')
    plt.plot(runs, concurrency_api, label='ConcurrencyAPI', marker='o', color='yellow')
    plt.plot(runs, sequential, label='Sequential', marker='o', color='orange')

    plt.xlabel('Diferentes promedios')
    plt.ylabel('Tiempo de ejecución (ms)')
    plt.title('Tiempo de ejecucion con 16 threads')
    plt.grid(True)

    plt.legend()
    plt.tight_layout()
    plt.show()

def execution_time_comparison_with_8_threads():
    runs = ['1', '2', '3', '4', '5', '6', '7']

    fork_join = [1354.7, 1397.8, 1315.2, 1370.6, 1324.3, 1412.45, 1373.2]
    concurrency_api = [1467.5, 1655.45, 1674.8, 1655.0, 1553.7, 1536.35, 1544.4]
    sequential = [2430.35, 2427.4, 2374.75, 2464.1, 2439.25, 2414.5, 2421.65]

    plt.figure(figsize=(10, 6))

    plt.plot(runs, fork_join, label='ForkJoin', marker='o', color='red')
    plt.plot(runs, concurrency_api, label='ConcurrencyAPI', marker='o', color='yellow')
    plt.plot(runs, sequential, label='Sequential', marker='o', color='orange')

    plt.xlabel('Diferentes promedios')
    plt.ylabel('Tiempo de ejecución (ms)')
    plt.title('Tiempo de ejecucion con 8 threads')
    plt.grid(True)

    plt.legend()
    plt.tight_layout()
    plt.show()

def execution_time_comparison_with_4_threads():
    runs = ['1', '2', '3', '4', '5', '6', '7']

    fork_join = [817.6, 1178.25, 1032.45, 1146.95, 1233.4, 890.85, 931.75]
    concurrency_api = [951.5, 1006.75, 1212.3, 1330.15, 1236.35, 1100.95, 1081.55]
    sequential = [2430.35, 2427.4, 2374.75, 2464.1, 2439.25, 2414.5, 2421.65]

    plt.figure(figsize=(10, 6))

    plt.plot(runs, fork_join, label='ForkJoin', marker='o', color='red')
    plt.plot(runs, concurrency_api, label='ConcurrencyAPI', marker='o', color='yellow')
    plt.plot(runs, sequential, label='Sequential', marker='o', color='orange')

    plt.xlabel('Diferentes promedios')
    plt.ylabel('Tiempo de ejecución (ms)')
    plt.title('Tiempo de ejecucion con 4 threads')
    plt.grid(True)

    plt.legend()
    plt.tight_layout()
    plt.show()

def execution_time_comparison_with_2_threads():
    runs = ['1', '2', '3', '4', '5', '6', '7']

    fork_join = [1323.3, 1421.25, 1485.25, 1793.2, 1564.2, 1427.35,1498.85]
    concurrency_api = [1757.8, 1936.1, 1960.0, 2159.2, 2092.75, 1910.25, 1847.35]
    sequential = [2430.35, 2427.4, 2374.75, 2464.1, 2439.25, 2414.5, 2421.65]

    plt.figure(figsize=(10, 6))

    plt.plot(runs, fork_join, label='ForkJoin', marker='o', color='red')
    plt.plot(runs, concurrency_api, label='ConcurrencyAPI', marker='o', color='yellow')
    plt.plot(runs, sequential, label='Sequential', marker='o', color='orange')

    plt.xlabel('Diferentes promedios')
    plt.ylabel('Tiempo de ejecución (ms)')
    plt.title('Tiempo de ejecucion con 2 threads')
    plt.grid(True)

    plt.legend()
    plt.tight_layout()
    plt.show()


if __name__ == "__main__":
    main()