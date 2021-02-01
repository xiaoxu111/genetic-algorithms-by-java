package com.aliwo;

/**
 * package_name:com.aliwo
 *
 * @author:xuyy19 Date:2021/2/1 12:05
 * 项目名:genetic-algorithms-by-java
 * Description:遗传算法启动类
 * Version: 1.0
 **/

public class AllOnesGA {
    public static void main(String[] args) {
        // 种群规模:100, 交叉率:0.001,变异率:0.95,精英主义:0
        GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.001, 0.95, 2);
        // 初始化种群
        Population population = ga.initPopulation(50);
        // 种群评估
        ga.evalPopulation(population);
        // 记录当前的基因
        int generation = 1;
        // 判断终止条件
        while (ga.isTerminationConditionMet(population) == false) {
            // 打印个体的适应值
            System.out.println("Best solution: " + population.getFittest(0).toString());
            // 执行交叉
            population = ga.crossoverPopulation(population);
            // 执行变异
            population = ga.mutatePopulation(population);
            // 评估
            ga.evalPopulation(population);
            // 自增当前的基因
            generation++;
        }
        System.out.println("Found solution in " + generation + " generations");
        System.out.println("Best solution: " + population.getFittest(0).toString());
    }
}
