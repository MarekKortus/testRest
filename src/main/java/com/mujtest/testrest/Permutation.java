/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mujtest.testrest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Marek
 */
public class Permutation extends Thread {
    private String inputJsonStr = "";
    private List<String> listValues = new ArrayList<String>();
    private String outputJsonStr = "";
    private double numberOfElementsSuma = 0;
    private double doneOfElements = 0;
    private int maxElements = 19; //20 or 21 give error in factorial fot int. 
    //We can use Long for longer list, but in this example we realy need it?

    private final Object monitor = new Object();

    public Permutation() { }

    public Permutation(String input) {
        createListFromArray(input);
    }

    /**
     * thread for creating permutation. For this we can get generated values when they are generating and progress
     */
    final Runnable threadBody = new Runnable() {
        @Override
        public void run() {
            outputJsonStr = "";
            if (listValues.size()<=maxElements) {
                List<String> top = new ArrayList<String>();
                permutation(top, listValues);
            }
        }
    };
    
    /**
     * default value in case, that valeu isn't set 
     */
    public void setValue() {
        this.setValue("[1, 2, 3]");
    }

    /**
     * set data from JSON source
     * this chars "[","]","'" are removed, and data are splitting by ","
     * @param input 
     */
    public void setValue(String input) {
        setClear();
        createListFromArray(input);
    }

    /**
     * clear data, set default value, create list and set number of elements insede list
     */
    public void setData() {
        setClear();
        String input = "[1, 2, 3]";
        createListFromArray(input);
    }

    /**
     * clear important data
     */
    public void setClear() {
        listValues.clear();
        numberOfElementsSuma = 0;
        doneOfElements = 0;        
    }
    
    /**
     * run thread for creating permutation
     */
    public void createData() {
        Thread thread = new Thread(threadBody, "createData");
        thread.start();
    }

    /**
     * method for creating permutation
     * @param prefix
     * @param list 
     */
    private void permutation(List<String> prefix, List<String> list) {
        Boolean notEmpty = false;
        /*
        // this is for testing of progress for small input JSONs
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("error: " + e.getMessage());
        }
        */
        for (int i=0; i<list.size(); i++) {
            if (!list.get(i).isEmpty()) { notEmpty = true; }
        }
        if (!notEmpty) {
            String prefixStr = "";
            for (int i=0; i<prefix.size(); i++) { prefixStr += ", " + prefix.get(i); }
            if (!prefixStr.isEmpty()) { prefixStr = prefixStr.substring(2); }
            synchronized (this.monitor) {
                if (!outputJsonStr.isEmpty()) { outputJsonStr += ", "; }
                outputJsonStr += "[" + prefixStr + "]";
                doneOfElements++;
            }
        } else {
            for (int i = 0; i < list.size(); i++) {
                List<String> prefixLocal = new ArrayList<>(prefix);
                List<String> listLocal = new ArrayList<>(list);
                if (!listLocal.get(i).isEmpty()) {
                    prefixLocal.add(listLocal.get(i));
                    listLocal.set(i, "");
                    permutation(prefixLocal, listLocal);
                }
            }
        }
    }

    /**
     * gettin progres of creation of permutation
     * @return 
     */
    public String getProgress() {
        if (numberOfElementsSuma==0) { return "0%"; }
        return "" + ((int) ((doneOfElements/numberOfElementsSuma)*100)) + "%";
    }

    /**
     * geting result od creating permutation, also durin creation - but always whole element
     * @return 
     */
    public String getValue() {
        synchronized (this.monitor) {
            if (numberOfElementsSuma==1) {
               return outputJsonStr;
            } else {
               return "[" + outputJsonStr + "]";
            }
        }
    }

    /**
     * count factorial for counting number of result elementsa - for progress
     * @param value
     * @return 
     */
    private int getFactorial(int value) {
        if (value<20) {
            for (int i=value-1; i>1; i--) { value = value*i; }
            return value;
        } else {
            return 0;
        }
    }
    
    /**
     * return number of input elements
     * @return 
     */
    public int getNumberOfElementsJson() {
        return listValues.size();
    }

    /**
     * return max for source elements for creating permutation
     * @return 
     */
    public int getMaxElements() {
        return this.maxElements;
    }

    /**
     * splitting String to array
     * @param input
     * @return 
     */
    private String[] splitString(String input) {
        return this.splitString(input, ",");
    }

    /**
     * Splitting String to array with defined splitter
     * also replace characters, that could be problematic in creation of permutation
     * //it could by problem for data with comma inside element
     * @param input
     * @param splitCharacter
     * @return 
     */
    private String[] splitString(String input, String splitCharacter) {
        return input.replace("[", "").replace("]", "").replace("'", "").split(splitCharacter);
    }
    
    /**
     * create list from array
     * @param input 
     */
    private void createListFromArray(String input) {
        synchronized (this.monitor) {
            String[] inputString = splitString(input);
            for (String s : inputString) { listValues.add(s.trim()); }
            numberOfElementsSuma = getFactorial(listValues.size());
        }
    }
    
}

