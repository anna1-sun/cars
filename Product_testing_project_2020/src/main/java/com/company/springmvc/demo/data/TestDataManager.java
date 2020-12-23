package com.company.springmvc.demo.data;

import com.company.springmvc.demo.dto.TestResultDto;


import java.util.ArrayList;
import java.util.List;

public class TestDataManager {
    private DataRepository repo;

    public TestDataManager() {
        repo = new DataRepository();
    }

    public Iterable<TestResultDto> getTestResults(int productId) {
        var product = repo.getProductById(productId);

        var categoryId= product.getCategory().getId();

        var limits = repo.getCategoryLimit(categoryId);

        var testResults = repo.getTestResultItems(productId);

        List<TestResultDto> items = new ArrayList<>();
        for(var limit : limits){
           items.add(mapTestResult(limit, testResults));
        }
    return items;
    }

    private TestResultDto mapTestResult(Limit limit, Iterable<TestResultItem> testResults){
        List<TestResultItem> items = new ArrayList<>();
        for(var result: testResults){
            items.add(result);
        }
        var testItem = items.stream().filter(i->i.getBacteria().getId() == limit.getBacteria().getId()).findFirst();
        var bacteriaName = limit.getBacteria().getName();
        var categoryLimit = limit.getLimit();
        var testValue = 0;
        if(testItem.isPresent()){
            var test = testItem.get();
            bacteriaName= test.getBacteriaName();
            categoryLimit = test.getCategoryLimit();
            testValue = test.getTestValue();

        }

        return new TestResultDto(bacteriaName, categoryLimit, testValue);
    }
}



