package e1.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import e1.API.SubsequenceCombiner;
import e1.API.SubsequenceCombinerFactory;

public class SubsequenceCombinerFactoryImpl implements SubsequenceCombinerFactory{

    @Override
    public SubsequenceCombiner<Integer, Integer> tripletsToSum() {
        return list -> {
            List<Integer> newList = new ArrayList<>();
                final int stop = list.size(); 
                int temp = 0;
                while ( temp < stop ) {
                    try{
                        if ( temp + 2 < stop ){
                            newList.add(list.get( temp ) + list.get( temp + 1 ) + list.get( temp + 2 ));
                        } else if ( temp + 1 < stop ){
                            newList.add(list.get( temp ) + list.get( temp + 1 ));
                        } else{
                            newList.add(list.get( temp ));
                        }
                    } catch( IndexOutOfBoundsException e ){
                        System.out.println(e);        
                    }
                    temp = temp + 3;
                }    
                return newList;
        };
    }

    @Override
    public <X> SubsequenceCombiner<X, List<X>> tripletsToList() {
        return list -> {
            List<X> newList;
            final List<List<X>> finaList = new ArrayList<>();
            int temp = 0;
            final int stop = list.size();
            while (temp <= list.size()) {
                try{
                    if ( temp + 2 < stop ){
                        newList = new ArrayList<>();
                        newList.add(list.get(temp));
                        newList.add(list.get(temp + 1));
                        newList.add(list.get(temp + 2));
                        finaList.add(newList);
                    } else if ( temp + 1 < stop ){
                        newList = new ArrayList<>();
                        newList.add(list.get(temp));
                        newList.add(list.get(temp + 1));
                        finaList.add(newList);
                    } else{
                        newList = new ArrayList<>();
                        newList.add(list.get(temp));
                        finaList.add(newList);
                    }
                } catch ( IndexOutOfBoundsException e ){
                    System.out.println(e);
                    
                }
                temp = temp + 3;
            }
            return finaList;
        };
    }

    @Override
    public SubsequenceCombiner<Integer, Integer> countUntilZero() {
        return list -> {
            final List<Integer> newList = new ArrayList<>();
            int temp = 0;
            int countUntilZero = 0;
            while ( temp <= list.size() ) {
                try{
                    if ( list.get(temp) != 0 ) {
                        countUntilZero++;
                    }
                    if ( list.get(temp) == 0 || temp == list.size() - 1 ) {
                        newList.add(countUntilZero);
                        countUntilZero = 0;
                    }
                } catch ( IndexOutOfBoundsException e ) {
                    System.out.println(e);
                }
                temp++;
            }
            return newList;
        };
    }

    @Override
    public <X, Y> SubsequenceCombiner<X, Y> singleReplacer(final Function<X, Y> function) {
        return list -> {
            List<Y> newList = new ArrayList<>();
            for ( X i: list ) {
                newList.add(function.apply(i));
            }
            return newList;
        };
    }

    private int CalculateSum(final List<Integer> list){
        int sum = 0;
        for ( Integer i: list ){
            sum += i;
        }
        return sum;
    }

    @Override
    public SubsequenceCombiner<Integer, List<Integer>> cumulateToList(final int threshold) {
        return list -> {
            List<List<Integer>> newList = new ArrayList<>();
            List<Integer> tempList = new ArrayList<>();
            for ( Integer i: list ){
                tempList.add(i);
                if ( CalculateSum(tempList) >= threshold || list.indexOf(i) == list.size() - 1 ) {
                    newList.add(tempList);
                    tempList = new ArrayList<>();
                }
            } 
            return newList;
        };
    }
    
}
