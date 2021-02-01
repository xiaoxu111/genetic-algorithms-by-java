package com.aliwo;


import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * package_name:com.aliwo
 *
 * @author:xuyy19 Date:2021/2/1 13:42
 * 项目名:genetic-algorithms-by-java
 * Description:种群是由(Individual)个体组成的
 * 主要功能:保存由个体构成的一个数组,也存储种群的总体适应度
 * Version: 1.0
 **/

public class Population {

    /**
     * 组成种群的个体(数组)
     */
    private Individual population[];

    /**
     * 种群的适应值
     */
    private double populationFitness = -1;

    /**
     * @param populationSize
     * @return Population
     * 初始化一个空的种群由个体(数组)组成
     */
    public Population(int populationSize) {
        this.population = new Individual[populationSize];
    }

    /**
     * @param populationSize   组成种群的个体大小
     * @param chromosomeLength 每个个体的染色体长度
     * @return Population
     */
    public Population(int populationSize, int chromosomeLength) {
        // 种群由个体数组组成
        this.population = new Individual[populationSize];
        // 循环的创建个体
        for (int individualCount = 0; individualCount < populationSize; individualCount++) {
            // 创建一个个体将其染色体初始化给定的长度
            Individual individual = new Individual(chromosomeLength);
            // 添加个体到种群中
            this.population[individualCount] = individual;
        }
    }

    /**
     * @return 从种群中获取个体(数组)
     */
    public Individual[] getIndividuals() {
        return this.population;
    }

    /**
     * @param offset
     * @return 根据适应值在种群中找到相应的个体
     */
    public Individual getFittest(int offset) {
        Arrays.sort(this.population, new Comparator<Individual>() {
            @Override
            public int compare(Individual o1, Individual o2) {
                if (o1.getFitness() > o2.getFitness()) {
                    return -1;
                } else if (o1.getFitness() < o2.getFitness()) {
                    return 1;
                }
                return 0;
            }
        });
        // 返回满足适应值的个体
        return this.population[offset];
    }

    /**
     * @param fitness 设置种群的适应值
     */
    public void setPopulationFitness(double fitness) {
        this.populationFitness = fitness;
    }

    /**
     * @return 返回种群的适应值
     */
    public double getPopulationFitness() {
        return populationFitness;
    }

    /**
     * @return 返回种群的大小
     */
    public int size() {
        return this.population.length;
    }

    /**
     * @param offset
     * @param individual
     * @return Individual
     * 设置个体的偏移量
     */
    public Individual setIndividual(int offset, Individual individual) {
        return population[offset] = individual;
    }

    /**
     * @param offset
     * @return Individual
     * 返回个体的偏移量
     */
    public Individual getIndividual(int offset) {
        return population[offset];
    }

    /**
     * 打乱种群
     */
    public void shuffle() {
        Random rdm = new Random();
        for (int i = population.length - 1; i > 0; i--) {
            int index = rdm.nextInt(i + 1);
            Individual individual = population[index];
            population[index] = population[i];
            population[i] = individual;
        }
    }
}
