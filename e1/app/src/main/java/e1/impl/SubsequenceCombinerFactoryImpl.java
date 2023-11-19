package e1.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import e1.API.SubsequenceCombiner;
import e1.API.SubsequenceCombinerFactory;

public class SubsequenceCombinerFactoryImpl implements SubsequenceCombinerFactory{

    @Override
    public SubsequenceCombiner<Integer, Integer> tripletsToSum() {
        return new SubsequenceCombiner<Integer,Integer>() {
            @Override
            public List<Integer> combine(List<Integer> list) {
                List<Integer> newList = new ArrayList<>();
                int temp = 0;
                while (temp <= list.size()) {
                    try{
                        newList.add(list.get( temp ) + list.get( temp + 1 ) + list.get( temp + 2 ));
                    }catch(IndexOutOfBoundsException e){
                        try{
                            newList.add(list.get( temp ) + list.get( temp + 1 ));
                        }catch(IndexOutOfBoundsException e2){
                            try{
                                newList.add(list.get( temp ));
                            }catch(IndexOutOfBoundsException e3){
                                System.out.println(e3);
                            }
                        }
                    }
                    
                    temp = temp + 3;
                }
                return newList;
            }
            
        }; 
    }

    @Override
    public <X> SubsequenceCombiner<X, List<X>> tripletsToList() {
        return new SubsequenceCombiner<X,List<X>>() {
            @Override
            public List<List<X>> combine(List<X> list) {
                List<X> newList;
                List<List<X>> finaList = new ArrayList<>();
                int temp = 0;
                while (temp <= list.size()) {
                    try{
                        newList = new ArrayList<>();
                        newList.add(list.get(temp));
                        newList.add(list.get(temp + 1));
                        newList.add(list.get(temp + 2));
                        finaList.add(newList);
                    }catch(IndexOutOfBoundsException e){
                        try{
                            newList = new ArrayList<>();
                            newList.add(list.get(temp));
                            newList.add(list.get(temp + 1));
                            finaList.add(newList);
                        }catch(IndexOutOfBoundsException e1){
                            try{
                                newList = new ArrayList<>();
                                newList.add(list.get(temp));
                                finaList.add(newList);
                            }catch(IndexOutOfBoundsException e2){
                                System.out.println(e2);
                            }
                        }
                    }
                    temp = temp + 3;
                }
                return finaList;
            }
            
        };
    }

    @Override
    public SubsequenceCombiner<Integer, Integer> countUntilZero() {
        return new SubsequenceCombiner<Integer,Integer>() {

            @Override
            public List<Integer> combine(List<Integer> list) {
                List<Integer> newList = new ArrayList<>();
                int temp = 0;
                int countUntilZero = 0;
                while (temp <= list.size()) {
                    try{
                        if(list.get(temp) != 0){
                            countUntilZero++;
                        }
                        if(list.get(temp) == 0 || temp == list.size() - 1){
                            newList.add(countUntilZero);
                            countUntilZero = 0;
                        }
                    }catch(IndexOutOfBoundsException e){
                        System.out.println(e);
                    }
                    temp++;
                }
                return newList;
            }
            
        };
    }

    @Override
    public <X, Y> SubsequenceCombiner<X, Y> singleReplacer(Function<X, Y> function) {
        return new SubsequenceCombiner<X,Y>() {
            @Override
            public List<Y> combine(List<X> list) {
                List<Y> newList = new ArrayList<>();
                for(X i: list){
                    newList.add(function.apply(i));
                }
                return newList;
            }
        };
    }

    private int CalculateSum(List<Integer> list){
        int sum = 0;
        for(Integer i: list){
            sum += i;
        }
        return sum;
    }

    @Override
    public SubsequenceCombiner<Integer, List<Integer>> cumulateToList(int threshold) {
        return new SubsequenceCombiner<Integer,List<Integer>>() {

            @Override
            public List<List<Integer>> combine(List<Integer> list) {
                List<List<Integer>> newList = new ArrayList<>();
                List<Integer> tempList = new ArrayList<>();
                for(Integer i: list){
                    tempList.add(i);
                    if(CalculateSum(tempList) >= threshold || list.indexOf(i) == list.size() - 1){
                        newList.add(tempList);
                        tempList = new ArrayList<>();
                    }
                } 
                return newList;
            }
        };
    }
    
}
