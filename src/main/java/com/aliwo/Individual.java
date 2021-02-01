package com.aliwo;

/**
 * package_name:com.aliwo
 *
 * @author:xuyy19 Date:2021/2/1 12:08
 * 项目名:genetic-algorithms-by-java
 * Description:个体代表候选解 主要负责存储和操作一个染色体
 * 两个构造方法,一个构造方法接受一个整数(代表染色体的长度),初始化对象时将创建一条随机的染色体,另一个构造方法接受一个整数数组,用它作为染色体
 **/

public class Individual {

    /**
     * 染色体int数组
     */
    private int[] chromosome;

    /**
     * 适应值
     */
    private double fitness = -1;

    /**
     * @param chromosome 使用特定染色体初始化个体
     * @return Individual 返回个体
     */
    private Individual(int[] chromosome) {
        this.chromosome = chromosome;
    }

    /**
     * @param offset
     * @param gene   设置基因的偏移量
     */
    public void setGene(int offset, int gene) {
        this.chromosome[offset] = gene;
    }

    /**
     * @param offset
     * @return gene
     * 返回基因的偏移量
     */
    public int getGene(int offset) {
        return this.chromosome[offset];
    }

    /**
     * @param chromosomeLength
     * @retuen Individual 返回随机的个体
     * 初始化随机的个体,
     */
    public Individual(int chromosomeLength) {
        this.chromosome = new int[chromosomeLength];
        for (int gene = 0; gene < chromosomeLength; gene++) {
            if (0.5 < Math.random()) {
                this.setGene(gene, 1);
            } else {
                this.setGene(gene, 0);
            }
        }
    }

    /**
     * @return 返回组成个体的染色体int数组
     */
    public int[] getChromosome() {
        return this.chromosome;
    }

    /**
     * @return 返回组成个体的染色体长度值
     */
    public int getChromosomeLength() {
        return this.chromosome.length;
    }

    /**
     * @param fitness 设置组成个体的适应值
     */
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    /**
     * @return 返回组成个体的适应值
     */
    public double getFitness() {
        return fitness;
    }

    /**
     * @return 返回染色体字符串的形式输出
     */
    public String toString() {
        String output = "";
        for (int gene = 0; gene < this.chromosome.length; gene++) {
            output += this.chromosome[gene];
        }
        return output;
    }
}
