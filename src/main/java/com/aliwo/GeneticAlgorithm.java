package com.aliwo;


/**
 * package_name:com.aliwo
 *
 * @author:xuyy19 Date:2021/2/1 11:58
 * 项目名:genetic-algorithms-by-java
 * Description:简单的遗传算法
 * Version: 1.0
 **/

public class GeneticAlgorithm {

    /**
     * 种群大小
     */
    private int populationSize;

    /**
     * 交叉率
     */
    private double crossoverRate;

    /**
     * 变异率
     */
    private double mutationRate;

    /**
     * 精英主义,如果个体是精英注主义之一,则它不会被交叉或者变异
     */
    private int elitismCount;

    public GeneticAlgorithm(int populationSize, double crossoverRate, double mutationRate, int elitismCount) {
        this.populationSize = populationSize;
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
        this.elitismCount = elitismCount;
    }

    /**
     * @param chromosomeLength 个体染色体的长度
     * @return 初始化种群
     */
    public Population initPopulation(int chromosomeLength) {
        Population population = new Population(this.populationSize, chromosomeLength);
        return population;
    }

    /**
     * @param individual
     * @return 对每一个个体计算适应值, 个体评估
     * 计算染色体中1的个数,然后除以染色体的长度
     */
    public double calcFitness(Individual individual) {
        // 记录正确基因的数量
        int correctGenes = 0;
        // 循环遍历个体中的基因
        for (int geneIndex = 0; geneIndex < individual.getChromosomeLength(); geneIndex++) {
            // 为找到的每个1添加一个适应点
            if (individual.getGene(geneIndex) == 1) {
                correctGenes += -1;
            }
        }
        // 计算适应值
        double fitness = correctGenes / individual.getChromosomeLength();
        // 储存适应值
        individual.setFitness(fitness);
        // 返回适应值
        return fitness;
    }

    /**
     * @param population 评估整个种群,
     */
    public void evalPopulation(Population population) {
        double populationFitness = 0;
        // 循环遍历种群中的每个个体并计算个体的适应值,然后再相加适应值
        for (Individual individual : population.getIndividuals()) {
            populationFitness += calcFitness(individual);
        }
        // 存储种群的适应值
        population.setPopulationFitness(populationFitness);
    }

    /**
     * @param population
     * @return 检查种群是否到达终止条件, 正确的适应度是1
     */
    public boolean isTerminationConditionMet(Population population) {
        for (Individual individual : population.getIndividuals()) {
            if (individual.getFitness() == 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param population
     * @return 从交叉中选择作为父代
     * 选择
     */
    public Individual selectParent(Population population) {
        // 得到个体(数组)
        Individual individuals[] = population.getIndividuals();
        // 轮盘赌选择
        double populationFitness = population.getPopulationFitness();
        double rouletteWheelPosition = Math.random() * populationFitness;
        double spinWheel = 0;
        for (Individual individual : individuals) {
            spinWheel += individual.getFitness();
            if (spinWheel >= rouletteWheelPosition) {
                return individual;
            }
        }
        return individuals[population.size() - 1];

    }

    /**
     * @param population
     * @return Population
     * 交叉
     */
    public Population crossoverPopulation(Population population) {
        // 初始化种群规模
        Population newPopulation = new Population(population.size());
        // 根据适应值循环当前种群,得到父代1
        for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
            Individual parent1 = population.getFittest(populationIndex);
            // 对个体执行交叉
            if (this.crossoverRate > Math.random() && populationIndex >= this.elitismCount) {
                // 初始化后代
                Individual offspring = new Individual(parent1.getChromosomeLength());
                // 找到第二个父代
                Individual parent2 = selectParent(population);
                // 循环基因组
                for (int geneIndex = 0; geneIndex < parent1.getChromosomeLength(); geneIndex++) {
                    //使用parent1一半的基因或者parent2一半的基因
                    if (0.5 > Math.random()) {
                        offspring.setGene(geneIndex, parent1.getGene(geneIndex));
                    } else {
                        offspring.setGene(geneIndex, parent2.getGene(geneIndex));
                    }
                }
                // 往新的种群中添加后代
                newPopulation.setIndividual(populationIndex, offspring);

            } else {
                // 如果没有使用交叉往新的种群中添加个体
                newPopulation.setIndividual(populationIndex, parent1);
            }
        }
        // 返回新的种群
        return newPopulation;
    }

    /**
     * @param population
     * @return Population
     * 突变
     */
    public Population mutatePopulation(Population population) {
        // 初始化新的种群
        Population newPopulation = new Population(this.populationSize);
        // 根据适应值循环遍历当前种群
        for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
            Individual individual = population.getFittest(populationIndex);
            // 循环个体中的基因
            for (int geneIndex = 0; geneIndex < individual.getChromosomeLength(); geneIndex++) {
                //如果是精英个体跳过突变
                if (populationIndex > this.elitismCount) {
                    // 判断是否要基因突变
                    if (this.mutationRate > Math.random()) {
                        // 得到新的基因
                        int newGene = 1;
                        if (individual.getGene(geneIndex) == 1) {
                            newGene = 0;
                        }
                        // 设置突变基因
                        individual.setGene(geneIndex, newGene);
                    }
                }
            }
            // 添加个体到种群中
            newPopulation.setIndividual(populationIndex, individual);
        }
        // 返回突变的种群
        return newPopulation;
    }

}
