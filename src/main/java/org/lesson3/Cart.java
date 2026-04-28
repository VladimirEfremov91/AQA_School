package org.lesson3;

import java.util.Arrays;

public class Cart {
    public static void main(String[] args) {
        String[] petya = {"курица", "бананы", "творог"};
        String[] kolya = {"курица", "бананы", "творог"};
        String[] terenty = {"пиво", "пельмени", "ласка магия черного"};

        //Сравнение корзин по количеству товаров:
        int petyaCartSize = petya.length;
        int kolyaCartSize = kolya.length;
        int terentyCartSize = terenty.length;
        boolean petyaKolyaCartSizeComparison = petyaCartSize == kolyaCartSize;
        boolean petyaTerentyCartSizeComparison = petyaCartSize == terentyCartSize;
        System.out.println("Корзина Пети и Коли соответствует по количеству товаров: " + petyaKolyaCartSizeComparison);
        System.out.println("Корзина Пети и Терентия соответствует по количеству товаров: " + petyaTerentyCartSizeComparison);

        //Сравнение корзин по составу:
        boolean petyaKolyaCompositionComparison = Arrays.equals(petya, kolya);
        boolean petyaTerentyCompositionComparison = Arrays.equals(petya, terenty);
        System.out.println("Корзина Пети соответствует по составу корзине Коли: " + petyaKolyaCompositionComparison);
        System.out.println("Корзина Пети соответствует по составу корзине Терентия: " + petyaTerentyCompositionComparison);

        //Ценное исследование
        int[] petyaItemsNamesLength = {petya[0].length(), petya[1].length(), petya[2].length()};
        int[] kolyaItemsNamesLength = {kolya[0].length(), kolya[1].length(), kolya[2].length()};
        int[] terentyItemsNamesLength = {terenty[0].length(), terenty[1].length(), terenty[2].length()};

        String[][] summary = {petya, kolya, terenty};

        String longestProduct = summary[0][0];
        String shortestProduct = summary[0][0];
        int totalLength = 0;
        int itemsQuantity = 0;
        for (int i = 0; i < summary.length; i++) {
            for (int j = 0; j < summary[i].length; j++) {
                String currentProduct = summary[i][j];
                if (currentProduct.length() > longestProduct.length()) {
                    longestProduct = currentProduct;
                }
                if (currentProduct.length() < longestProduct.length()) {
                    shortestProduct = currentProduct;
                }
                totalLength += currentProduct.length();
                itemsQuantity++;
            }
        }
        double averageLength = (double) totalLength / (double) itemsQuantity;
        System.out.println("Самое длинное название: " + longestProduct);
        System.out.println("Самое короткое название: " + shortestProduct);
        System.out.println("Средняя длина названия товараCart: " + averageLength);
    }
}
